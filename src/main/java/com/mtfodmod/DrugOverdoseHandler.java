package com.mtfodmod;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

/**
 * 药物负荷 / 过量(OD) / 戒断系统。
 *
 * 玩家在药物作用下会累积「药物负荷」：
 * - 负荷达到上限 → 药物过量 (OD)：中毒 + 凋零，可能致死；
 * - 负荷较高时药效结束 → 戒断反应：乏力、恶心、食欲下降。
 *
 * 连续用药越多、越久，负荷越高；只吃一两片通常不会 OD。
 */
public final class DrugOverdoseHandler {
	private static final int INTERVAL_TICKS = 20; // 每 1 秒检查一次
	private static final int OD_THRESHOLD = 360; // 约 6 分钟持续作用 -> OD
	private static final int WITHDRAWAL_THRESHOLD = 200; // 约 3-4 分钟 -> 戒断

	private static final Map<UUID, State> STATES = new HashMap<>();
	private static int tickCounter = 0;

	private DrugOverdoseHandler() {
	}

	private static final class State {
		int load = 0;
		boolean wasOnDrugs = false;
	}

	public static void register() {
		ServerTickEvents.END_SERVER_TICK.register(DrugOverdoseHandler::onTick);
	}

	private static void onTick(MinecraftServer server) {
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

			// 药物过量 (OD)
			if (state.load >= OD_THRESHOLD) {
				state.load = 0;
				player.addEffect(new MobEffectInstance(MobEffects.POISON, 20 * 12, 2));
				player.addEffect(new MobEffectInstance(MobEffects.WITHER, 20 * 12, 1));
				player.sendSystemMessage(Component.literal("§c⚠ 药物过量 (OD)！身体正在崩溃"));
			}

			// 戒断反应（药效刚结束且负荷较高）
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

	/** 是否处于药物作用下（反胃 + 至少一种药物特征效果）。 */
	private static boolean isOnDrugs(ServerPlayer player) {
		return player.hasEffect(MobEffects.NAUSEA)
				&& (player.hasEffect(MobEffects.DARKNESS)
					|| player.hasEffect(MobEffects.BLINDNESS)
					|| player.hasEffect(MobEffects.POISON)
					|| player.hasEffect(MobEffects.SLOWNESS));
	}
}
