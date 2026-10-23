package com.mtfodmod.item;

import java.util.List;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * 药品类物品：按真实药理学模拟国内被滥用的处方药的作用与危害。
 * 用「不同持续时间的效果叠加」近似药效分期：初始兴奋 → 高峰解离/幻觉 → 后遗乏力。
 * 内容仅供公益警示，不构成用药指导。
 */
public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems("mtfodmod");

    // --- 普瑞巴林胶囊 ---
    public static final Consumable PREGABALIN_CONSUMABLE = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.REGENERATION, 30 * 20, 0), 1.0f))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.SLOWNESS, 180 * 20, 1), 1.0f))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.NAUSEA, 120 * 20, 0), 1.0f))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.WEAKNESS, 240 * 20, 1), 1.0f))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HUNGER, 240 * 20, 0), 1.0f))
            .build();
    public static final FoodProperties PREGABALIN_FOOD = new FoodProperties.Builder()
            .nutrition(0).saturationModifier(0f).alwaysEdible().build();

    // --- 右美沙芬 ---
    public static final Consumable DEXTROMETHORPHAN_CONSUMABLE = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.SPEED, 20 * 20, 1), 1.0f))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.DARKNESS, 45 * 20, 0), 1.0f))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.BLINDNESS, 30 * 20, 0), 1.0f))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.NAUSEA, 120 * 20, 1), 1.0f))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.SLOWNESS, 150 * 20, 1), 1.0f))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.POISON, 20 * 20, 0), 1.0f))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.WEAKNESS, 180 * 20, 0), 1.0f))
            .build();
    public static final FoodProperties DEXTROMETHORPHAN_FOOD = new FoodProperties.Builder()
            .nutrition(0).saturationModifier(0f).alwaysEdible().build();

    // --- 盐酸金刚烷胺 ---
    public static final Consumable AMANTADINE_CONSUMABLE = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.SPEED, 30 * 20, 2), 1.0f))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.DARKNESS, 60 * 20, 1), 1.0f))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.BLINDNESS, 40 * 20, 1), 1.0f))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.NAUSEA, 150 * 20, 1), 1.0f))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.POISON, 30 * 20, 1), 1.0f))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.SLOWNESS, 180 * 20, 0), 1.0f))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.WEAKNESS, 180 * 20, 0), 1.0f))
            .build();
    public static final FoodProperties AMANTADINE_FOOD = new FoodProperties.Builder()
            .nutrition(0).saturationModifier(0f).alwaysEdible().build();

    // --- 加巴喷丁胶囊 ---
    public static final Consumable GABAPENTIN_CONSUMABLE = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.REGENERATION, 20 * 20, 0), 1.0f))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.SLOWNESS, 150 * 20, 1), 1.0f))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.NAUSEA, 90 * 20, 0), 1.0f))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.WEAKNESS, 180 * 20, 1), 1.0f))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HUNGER, 180 * 20, 0), 1.0f))
            .build();
    public static final FoodProperties GABAPENTIN_FOOD = new FoodProperties.Builder()
            .nutrition(0).saturationModifier(0f).alwaysEdible().build();

    // --- 戊酸雌二醇片 ---
    public static final Consumable ESTRADIOL_CONSUMABLE = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.REGENERATION, 60 * 20, 0), 1.0f))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.ABSORPTION, 120 * 20, 1), 1.0f))
            .build();
    public static final FoodProperties ESTRADIOL_FOOD = new FoodProperties.Builder()
            .nutrition(1).saturationModifier(0.2f).alwaysEdible().build();

    // --- 愈美片 ---
    public static final Consumable YUMEI_CONSUMABLE = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.SPEED, 15 * 20, 0), 1.0f))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.DARKNESS, 30 * 20, 0), 1.0f))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.BLINDNESS, 20 * 20, 0), 1.0f))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.NAUSEA, 90 * 20, 0), 1.0f))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.POISON, 15 * 20, 0), 1.0f))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.WEAKNESS, 150 * 20, 0), 1.0f))
            .build();
    public static final FoodProperties YUMEI_FOOD = new FoodProperties.Builder()
            .nutrition(0).saturationModifier(0f).alwaysEdible().build();

    // --- 色谱龙 50mg ---
    public static final Consumable ANDROCUR_CONSUMABLE = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.RESISTANCE, 60 * 20, 0), 1.0f))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.REGENERATION, 40 * 20, 0), 1.0f))
            .build();
    public static final FoodProperties ANDROCUR_FOOD = new FoodProperties.Builder()
            .nutrition(1).saturationModifier(0.2f).alwaysEdible().build();

    public static final DeferredItem<Item> PREGABALIN_CAPSULE = ITEMS.registerSimpleItem(
            "pregabalin_capsule",
            p -> warn(p, "pregabalin_capsule").food(PREGABALIN_FOOD, PREGABALIN_CONSUMABLE));

    public static final DeferredItem<Item> DEXTROMETHORPHAN = ITEMS.registerSimpleItem(
            "dextromethorphan",
            p -> warn(p, "dextromethorphan").food(DEXTROMETHORPHAN_FOOD, DEXTROMETHORPHAN_CONSUMABLE));

    public static final DeferredItem<Item> AMANTADINE = ITEMS.registerSimpleItem(
            "amantadine",
            p -> warn(p, "amantadine").food(AMANTADINE_FOOD, AMANTADINE_CONSUMABLE));

    public static final DeferredItem<Item> GABAPENTIN_CAPSULE = ITEMS.registerSimpleItem(
            "gabapentin_capsule",
            p -> warn(p, "gabapentin_capsule").food(GABAPENTIN_FOOD, GABAPENTIN_CONSUMABLE));

    public static final DeferredItem<Item> ESTRADIOL_VALERATE = ITEMS.registerSimpleItem(
            "estradiol_valerate",
            p -> warn(p, "estradiol_valerate").food(ESTRADIOL_FOOD, ESTRADIOL_CONSUMABLE));

    public static final DeferredItem<Item> YUMEI_TABLET = ITEMS.registerSimpleItem(
            "yumei_tablet",
            p -> warn(p, "yumei_tablet").food(YUMEI_FOOD, YUMEI_CONSUMABLE));

    public static final DeferredItem<Item> ANDROCUR_50MG = ITEMS.registerSimpleItem(
            "androcur_50mg",
            p -> warn(p, "androcur_50mg").food(ANDROCUR_FOOD, ANDROCUR_CONSUMABLE));

    private static Item.Properties warn(Item.Properties props, String id) {
        return props.component(DataComponents.LORE, new ItemLore(List.of(
                Component.translatable("item.mtfodmod." + id + ".lore1"),
                Component.translatable("item.mtfodmod." + id + ".lore2"))));
    }
}
