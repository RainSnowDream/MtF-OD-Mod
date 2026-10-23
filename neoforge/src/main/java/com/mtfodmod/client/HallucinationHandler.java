package com.mtfodmod.client;

import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;

/**
 * 客户端「幻听」：仅对致幻类药品生效（同时有 DARKNESS + NAUSEA）。
 * 随机播放原版恐怖洞穴环境音（ambient.cave）。
 */
@EventBusSubscriber(modid = "mtfodmod", value = Dist.CLIENT)
public final class HallucinationHandler {
    private static final int MIN_INTERVAL_TICKS = 40;
    private static final int MAX_INTERVAL_TICKS = 120;

    private static int tickCounter = 0;
    private static int nextInterval = MIN_INTERVAL_TICKS;

    private HallucinationHandler() {
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft client = Minecraft.getInstance();
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
        double x = client.player.getX() + (client.player.getRandom().nextDouble() - 0.5D) * 12.0D;
        double y = client.player.getY() + 1.0D;
        double z = client.player.getZ() + (client.player.getRandom().nextDouble() - 0.5D) * 12.0D;
        client.level.playLocalSound(x, y, z, SoundEvents.AMBIENT_CAVE.value(), SoundSource.MASTER, 2.0F,
                0.9F + client.player.getRandom().nextFloat() * 0.2F, false);
    }
}
