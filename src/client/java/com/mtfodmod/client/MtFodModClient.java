package com.mtfodmod.client;

import net.fabricmc.api.ClientModInitializer;

public class MtFodModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		HallucinationHandler.register();
	}
}
