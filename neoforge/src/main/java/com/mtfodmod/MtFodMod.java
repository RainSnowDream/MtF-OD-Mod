package com.mtfodmod;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import com.mtfodmod.item.ModItems;

@Mod(MtFodMod.MOD_ID)
public class MtFodMod {
    public static final String MOD_ID = "mtfodmod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public MtFodMod(IEventBus modEventBus, ModContainer modContainer) {
        ModItems.ITEMS.register(modEventBus);
        modEventBus.addListener(this::addCreative);
        LOGGER.info("[MtFOD] Loaded. 珍爱生命，远离药物滥用。");
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(ModItems.PREGABALIN_CAPSULE);
            event.accept(ModItems.DEXTROMETHORPHAN);
            event.accept(ModItems.AMANTADINE);
            event.accept(ModItems.GABAPENTIN_CAPSULE);
            event.accept(ModItems.ESTRADIOL_VALERATE);
            event.accept(ModItems.YUMEI_TABLET);
            event.accept(ModItems.ANDROCUR_50MG);
        }
    }
}
