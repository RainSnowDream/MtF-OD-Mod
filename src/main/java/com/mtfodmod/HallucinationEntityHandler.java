package com.mtfodmod;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.phys.Vec3;

/**
 * 幻影实体幻觉。
 *
 * 致幻状态下在玩家正前方 3~4.5 格生成「黑影子」：
 * - 普通致幻（黑暗 I）：末影人，较温和；
 * - 金刚烷胺（黑暗 II，最高强度）：监守者（Warden），更黑、更大、更吓人，出现更频繁。
 * 均为无 AI、静音、无敌，几秒后消失。
 */
public final class HallucinationEntityHandler {
	private static final Map<Entity, Integer> PHANTOMS = new HashMap<>();
	private static int tickCounter = 0;

	private HallucinationEntityHandler() {
	}

	public static void register() {
		ServerTickEvents.END_SERVER_TICK.register(HallucinationEntityHandler::onTick);
	}

	private static void onTick(MinecraftServer server) {
		// 清理过期的幻影
		Iterator<Map.Entry<Entity, Integer>> iterator = PHANTOMS.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Entity, Integer> entry = iterator.next();
			Entity entity = entry.getKey();
			int remaining = entry.getValue() - 1;
			if (entity.isRemoved() || remaining <= 0) {
				entity.discard();
				iterator.remove();
			} else {
				entry.setValue(remaining);
			}
		}

		// 每 2 秒尝试生成一次
		tickCounter++;
		if (tickCounter % 40 != 0) {
			return;
		}
		for (ServerPlayer player : server.getPlayerList().getPlayers()) {
			if (!isHallucinating(player)) {
				continue;
			}
			boolean intense = isIntense(player);
			int chance = intense ? 2 : 3; // 金刚烷胺 1/2，其余 1/3
			if (player.getRandom().nextInt(chance) != 0) {
				continue;
			}
			spawnPhantom(player, intense);
		}
	}

	private static void spawnPhantom(ServerPlayer player, boolean intense) {
		if (!(player.level() instanceof ServerLevel level)) {
			return;
		}
		String entityId = intense ? "minecraft:warden" : "minecraft:enderman";
		EntityType<?> type = BuiltInRegistries.ENTITY_TYPE.getValue(Identifier.parse(entityId));
		if (type == null) {
			return;
		}
		Entity phantom = type.create(level, EntitySpawnReason.MOB_SUMMONED);
		if (phantom == null) {
			return;
		}

		// 生成在玩家正前方 3~4.5 格，近到能看清
		Vec3 look = player.getLookAngle();
		double distance = 3.0D + player.getRandom().nextDouble() * 1.5D;
		double x = player.getX() + look.x * distance;
		double z = player.getZ() + look.z * distance;
		double y = player.getY();

		phantom.setPos(x, y, z);
		phantom.setSilent(true);
		phantom.setPermanentlyInvulnerable(true);
		if (phantom instanceof Mob mob) {
			mob.setNoAi(true);
			mob.setPersistenceRequired();
		}
		level.addFreshEntity(phantom);
		PHANTOMS.put(phantom, intense ? 60 : 80);
	}

	/** 是否处于致幻状态（黑暗 + 反胃）。 */
	private static boolean isHallucinating(ServerPlayer player) {
		return player.hasEffect(MobEffects.DARKNESS) && player.hasEffect(MobEffects.NAUSEA);
	}

	/** 金刚烷胺的黑暗为 II 级，作为「最高强度幻觉」标记。 */
	private static boolean isIntense(ServerPlayer player) {
		MobEffectInstance darkness = player.getEffect(MobEffects.DARKNESS);
		return darkness != null && darkness.getAmplifier() >= 1;
	}
}
