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
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.phys.Vec3;

/**
 * 幻影实体幻觉。
 *
 * 玩家处于致幻状态（黑暗 + 反胃）时，偶尔在正前方 3~4.5 格生成一个「黑影子」：
 * 一个纯黑、高瘦的末影人，无 AI、静音、无敌，几秒后消失。
 * （实体类型通过注册表获取，因为 26.3 的 EntityType 不再提供静态常量。）
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
			if (!isHallucinating(player) || player.getRandom().nextInt(3) != 0) {
				continue;
			}
			spawnPhantom(player);
		}
	}

	private static void spawnPhantom(ServerPlayer player) {
		if (!(player.level() instanceof ServerLevel level)) {
			return;
		}
		EntityType<?> type = BuiltInRegistries.ENTITY_TYPE.getValue(Identifier.parse("minecraft:enderman"));
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
		PHANTOMS.put(phantom, 80); // 约 4 秒
	}

	private static boolean isHallucinating(ServerPlayer player) {
		return player.hasEffect(MobEffects.DARKNESS) && player.hasEffect(MobEffects.NAUSEA);
	}
}
