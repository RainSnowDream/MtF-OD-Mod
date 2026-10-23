package com.mtfodmod;

import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mtfodmod.item.ModItems;

public class MtFodMod implements ModInitializer {
	public static final String MOD_ID = "mtfodmod";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.initialize();
		DrugOverdoseHandler.register();

		LOGGER.info("[MtFOD] Loaded. 珍爱生命，远离药物滥用。");
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
