package com.mtfodmod.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

import net.minecraft.world.effect.MobEffects;

/**
 * 客户端药物负荷追踪（与服务器逻辑一致，用于 HUD 显示）。
 */
public final class DrugHudTracker {
	private static final int OD_THRESHOLD = 360;
	private static int load = 0;
	private static int tickCounter = 0;

	private DrugHudTracker() {
	}

	public static void register() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (client.player == null) {
				load = 0;
				return;
			}
			if (++tickCounter < 20) {
				return;
			}
			tickCounter = 0;
			boolean onDrugs = client.player.hasEffect(MobEffects.NAUSEA)
					&& (client.player.hasEffect(MobEffects.DARKNESS)
						|| client.player.hasEffect(MobEffects.BLINDNESS)
						|| client.player.hasEffect(MobEffects.POISON)
						|| client.player.hasEffect(MobEffects.SLOWNESS));
			if (onDrugs) {
				load = Math.min(2000, load + 1);
			} else {
				load = Math.max(0, load - 1);
			}
			if (load >= OD_THRESHOLD) {
				load = 0;
			}
		});
	}

	public static int getLoad() {
		return load;
	}

	public static int getThreshold() {
		return OD_THRESHOLD;
	}
}
