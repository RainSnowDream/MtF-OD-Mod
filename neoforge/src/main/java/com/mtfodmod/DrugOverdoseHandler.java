package com.mtfodmod;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

/**
 * 药物负荷 / 过量(OD) / 戒断系统（NeoForge 版）。
 */
@EventBusSubscriber(modid = MtFodMod.MOD_ID)
public final class DrugOverdoseHandler {
    private static final int INTERVAL_TICKS = 20;
    private static final int OD_THRESHOLD = 360;
    private static final int WITHDRAWAL_THRESHOLD = 200;

    private static final Map<UUID, State> STATES = new HashMap<>();
    private static int tickCounter = 0;

    private DrugOverdoseHandler() {
    }

    private static final class State {
        int load = 0;
        boolean wasOnDrugs = false;
    }

    @SubscribeEvent
    public static void onServerTick(ServerTickEvent.Post event) {
        MinecraftServer server = event.getServer();
        if (++tickCounter < INTERVAL_TICKS) {
            return;
        }
        tickCounter = 0;

        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
            UUID id = player.getUUID();
            State state = STATES.computeIfAbsent(id, key -> new State());

            boolean onDrugs = isOnDrugs(player);
            if (onDrugs) {
                state.load = Math.min(2000, state.load + 1);
            } else {
                state.load = Math.max(0, state.load - 1);
            }

            if (state.load >= OD_THRESHOLD) {
                state.load = 0;
                player.addEffect(new MobEffectInstance(MobEffects.POISON, 20 * 12, 2));
                player.addEffect(new MobEffectInstance(MobEffects.WITHER, 20 * 12, 1));
                player.sendSystemMessage(Component.literal("§c⚠ 药物过量 (OD)！身体正在崩溃"));
            }

            if (state.wasOnDrugs && !onDrugs && state.load >= WITHDRAWAL_THRESHOLD) {
                state.load = 0;
                player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 20 * 20, 1));
                player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 20 * 15, 0));
                player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 20 * 20, 1));
                player.sendSystemMessage(Component.literal("§e戒断反应：乏力、恶心、食欲下降"));
            }

            state.wasOnDrugs = onDrugs;
            if (state.load == 0 && !onDrugs) {
                STATES.remove(id);
            }
        }
    }

    private static boolean isOnDrugs(ServerPlayer player) {
        return player.hasEffect(MobEffects.NAUSEA)
                && (player.hasEffect(MobEffects.DARKNESS)
                    || player.hasEffect(MobEffects.BLINDNESS)
                    || player.hasEffect(MobEffects.POISON)
                    || player.hasEffect(MobEffects.SLOWNESS));
    }
}
