package com.mtfodmod.client;

import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;

/**
 * 客户端「幻听」（NeoForge 版）：普通 2~6 秒一次；金刚烷胺（黑暗 II）1.25~3.5 秒一次且更响。
 */
@EventBusSubscriber(modid = "mtfodmod", value = Dist.CLIENT)
public final class HallucinationHandler {
    private static final int MIN_INTERVAL_TICKS = 40;
    private static final int MAX_INTERVAL_TICKS = 120;
    private static final int INTENSE_MIN_INTERVAL_TICKS = 25;
    private static final int INTENSE_MAX_INTERVAL_TICKS = 70;

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

        boolean intense = isIntense(client.player.getEffect(MobEffects.DARKNESS));
        int min = intense ? INTENSE_MIN_INTERVAL_TICKS : MIN_INTERVAL_TICKS;
        int max = intense ? INTENSE_MAX_INTERVAL_TICKS : MAX_INTERVAL_TICKS;
        float volume = intense ? 2.5F : 2.0F;

        if (++tickCounter < nextInterval) {
            return;
        }
        tickCounter = 0;
        nextInterval = min + client.player.getRandom().nextInt(max - min + 1);

        double x = client.player.getX() + (client.player.getRandom().nextDouble() - 0.5D) * 12.0D;
        double y = client.player.getY() + 1.0D;
        double z = client.player.getZ() + (client.player.getRandom().nextDouble() - 0.5D) * 12.0D;
        client.level.playLocalSound(x, y, z, SoundEvents.AMBIENT_CAVE.value(), SoundSource.MASTER, volume,
                0.9F + client.player.getRandom().nextFloat() * 0.2F, false);
    }

    private static boolean isIntense(MobEffectInstance darkness) {
        return darkness != null && darkness.getAmplifier() >= 1;
    }
}
