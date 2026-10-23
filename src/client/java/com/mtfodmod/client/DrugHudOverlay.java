package com.mtfodmod.client;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;

/**
 * 底部血条/经验条样式的药物负荷 HUD。
 */
public class DrugHudOverlay implements HudElement {
	@Override
	public void extractRenderState(GuiGraphicsExtractor gui, DeltaTracker delta) {
		Minecraft mc = Minecraft.getInstance();
		if (mc.player == null) {
			return;
		}
		int load = DrugHudTracker.getLoad();
		if (load <= 0) {
			return;
		}

		int threshold = DrugHudTracker.getThreshold();
		int screenWidth = mc.getWindow().getGuiScaledWidth();
		int screenHeight = mc.getWindow().getGuiScaledHeight();

		int barWidth = 182;
		int barHeight = 5;
		int x = (screenWidth - barWidth) / 2;
		int y = screenHeight - 29; // 紧贴经验条上方

		float ratio = Math.min(1.0F, (float) load / threshold);
		int filled = (int) (barWidth * ratio);

		// 背景与填充
		gui.fill(x, y, x + barWidth, y + barHeight, 0x90000000);
		int color = ratio >= 0.8F ? 0xFFFF3333 : ratio >= 0.5F ? 0xFFFFCC00 : 0xFF33CC33;
		gui.fill(x, y, x + filled, y + barHeight, color);

		// 文字
		gui.text(mc.font, Component.literal("药物负荷 " + (int) (ratio * 100) + "%"), x, y - 10, 0xFFFFFFFF);
	}
}
