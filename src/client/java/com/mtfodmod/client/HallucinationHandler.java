package com.mtfodmod.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

/**
 * 客户端「幻听」。
 *
 * 仅对致幻类药品生效（同时有 DARKNESS + NAUSEA）。
 * 普通致幻：2~6 秒一次的洞穴环境音；
 * 金刚烷胺（黑暗 II，最高强度）：1.25~3.5 秒一次、音量更大，更接近恐怖游戏。
 */
public final class HallucinationHandler {
	private static final int MIN_INTERVAL_TICKS = 40; // 2 秒
	private static final int MAX_INTERVAL_TICKS = 120; // 6 秒
	private static final int INTENSE_MIN_INTERVAL_TICKS = 25; // 1.25 秒
	private static final int INTENSE_MAX_INTERVAL_TICKS = 70; // 3.5 秒

	private static int tickCounter = 0;
	private static int nextInterval = MIN_INTERVAL_TICKS;

	private HallucinationHandler() {
	}

	public static void register() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (client.player == null || client.level == null) {
				return;
			}
			boolean hallucinating = client.player.hasEffect(MobEffects.DARKNESS)
					&& client.player.hasEffect(MobEffects.NAUSEA);
			if (!hallucinating) {
				tickCounter = 0;
				nextInterval = MIN_INTERVAL_TICKS;
				return;
			}

			boolean intense = isIntense(client.player.getEffect(MobEffects.DARKNESS));
			int min = intense ? INTENSE_MIN_INTERVAL_TICKS : MIN_INTERVAL_TICKS;
			int max = intense ? INTENSE_MAX_INTERVAL_TICKS : MAX_INTERVAL_TICKS;
			float volume = intense ? 2.5F : 2.0F;

			if (++tickCounter < nextInterval) {
				return;
			}
			tickCounter = 0;
			nextInterval = min + client.player.getRandom().nextInt(max - min + 1);

			// 近身 12 格内随机方位
			double x = client.player.getX() + (client.player.getRandom().nextDouble() - 0.5D) * 12.0D;
			double y = client.player.getY() + 1.0D;
			double z = client.player.getZ() + (client.player.getRandom().nextDouble() - 0.5D) * 12.0D;
			client.level.playLocalSound(x, y, z, SoundEvents.AMBIENT_CAVE.value(), SoundSource.MASTER, volume,
					0.9F + client.player.getRandom().nextFloat() * 0.2F, false);
		});
	}

	private static boolean isIntense(MobEffectInstance darkness) {
		return darkness != null && darkness.getAmplifier() >= 1;
	}
}
