package com.mtfodmod;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

/**
 * 幻影实体幻觉（NeoForge 版）：正前方生成纯黑末影人「黑影子」。
 */
@EventBusSubscriber(modid = MtFodMod.MOD_ID)
public final class HallucinationEntityHandler {
    private static final Map<Entity, Integer> PHANTOMS = new HashMap<>();
    private static int tickCounter = 0;

    private HallucinationEntityHandler() {
    }

    @SubscribeEvent
    public static void onServerTick(ServerTickEvent.Post event) {
        MinecraftServer server = event.getServer();

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
        PHANTOMS.put(phantom, 80);
    }

    private static boolean isHallucinating(ServerPlayer player) {
        return player.hasEffect(MobEffects.DARKNESS) && player.hasEffect(MobEffects.NAUSEA);
    }
}
