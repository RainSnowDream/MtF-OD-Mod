package com.mtfodmod.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffects;

/**
 * 客户端「幻听」。
 *
 * 仅对致幻类药品生效：只有右美沙芬 / 愈美片 / 盐酸金刚烷胺会同时施加
 * DARKNESS（致幻标记）与 NAUSEA；普瑞巴林、加巴喷丁等镇静类虽然也有 NAUSEA，
 * 但不带 DARKNESS，因此不会触发。
 *
 * 声音使用原版恐怖的洞穴环境音（ambient.cave），以较高音量、较短间隔、
 * 近身随机方位播放，营造"附近有东西"的幻听感。
 * 纯客户端，不改变服务端世界。
 */
public final class HallucinationHandler {
	// 间隔：2~6 秒
	private static final int MIN_INTERVAL_TICKS = 40;
	private static final int MAX_INTERVAL_TICKS = 120;

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
			if (++tickCounter < nextInterval) {
				return;
			}
			tickCounter = 0;
			nextInterval = MIN_INTERVAL_TICKS
					+ client.player.getRandom().nextInt(MAX_INTERVAL_TICKS - MIN_INTERVAL_TICKS + 1);
			// 近身 6 格内随机方位，避免距离衰减导致音量过小
			double x = client.player.getX() + (client.player.getRandom().nextDouble() - 0.5D) * 12.0D;
			double y = client.player.getY() + 1.0D;
			double z = client.player.getZ() + (client.player.getRandom().nextDouble() - 0.5D) * 12.0D;
			client.level.playLocalSound(x, y, z, SoundEvents.AMBIENT_CAVE.value(), SoundSource.MASTER, 2.0F,
					0.9F + client.player.getRandom().nextFloat() * 0.2F, false);
		});
	}
}
