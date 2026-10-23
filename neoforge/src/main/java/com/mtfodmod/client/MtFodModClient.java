package com.mtfodmod.client;

import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

public final class MtFodModClient {
    private MtFodModClient() {
    }

    public static void register(RegisterGuiLayersEvent event) {
        event.registerAbove(VanillaGuiLayers.EXPERIENCE_LEVEL,
                Identifier.parse("mtfodmod:drug_load_hud"),
                new DrugHudOverlay());
    }
}
