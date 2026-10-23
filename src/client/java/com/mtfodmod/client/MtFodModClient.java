package com.mtfodmod.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;

import net.minecraft.resources.Identifier;

public class MtFodModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		HallucinationHandler.register();
		DrugHudTracker.register();
		HudElementRegistry.addLast(
				Identifier.fromNamespaceAndPath("mtfodmod", "drug_load_hud"),
				new DrugHudOverlay());
	}
}
