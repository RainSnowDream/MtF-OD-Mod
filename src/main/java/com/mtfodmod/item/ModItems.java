package com.mtfodmod.item;

import java.util.List;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;

import com.mtfodmod.MtFodMod;

/**
 * 药品类物品：按真实药理学模拟国内被滥用的处方药的作用与危害。
 * 用「不同持续时间的效果叠加」近似药效分期：初始兴奋 → 高峰解离/幻觉 → 后遗乏力。
 * 内容仅供公益警示，不构成用药指导。
 */
public class ModItems {
	// --- 普瑞巴林胶囊：镇静、眩晕、共济失调、宿醉样后遗 ---
	public static final Consumable PREGABALIN_CONSUMABLE = Consumables.defaultFood()
			// 初始：短暂放松/欣快
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.REGENERATION, 30 * 20, 0), 1.0f))
			// 高峰：镇静、共济失调、眩晕
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.SLOWNESS, 180 * 20, 1), 1.0f))
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.NAUSEA, 120 * 20, 0), 1.0f))
			// 后遗：乏力、嗜睡、食欲下降
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.WEAKNESS, 240 * 20, 1), 1.0f))
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HUNGER, 240 * 20, 0), 1.0f))
			.build();
	public static final FoodProperties PREGABALIN_FOOD = new FoodProperties.Builder()
			.nutrition(0).saturationModifier(0f).alwaysEdible().build();

	// --- 右美沙芬：解离、幻觉、心率加快、血清素综合征风险 ---
	public static final Consumable DEXTROMETHORPHAN_CONSUMABLE = Consumables.defaultFood()
			// 初始：心率加快 / 兴奋
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.SPEED, 20 * 20, 1), 1.0f))
			// 高峰：解离、幻觉、视觉扭曲
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.DARKNESS, 45 * 20, 0), 1.0f))
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.BLINDNESS, 30 * 20, 0), 1.0f))
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.NAUSEA, 120 * 20, 1), 1.0f))
			// 共济失调
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.SLOWNESS, 150 * 20, 1), 1.0f))
			// 血清素综合征（毒性）
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.POISON, 20 * 20, 0), 1.0f))
			// 后遗
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.WEAKNESS, 180 * 20, 0), 1.0f))
			.build();
	public static final FoodProperties DEXTROMETHORPHAN_FOOD = new FoodProperties.Builder()
			.nutrition(0).saturationModifier(0f).alwaysEdible().build();

	// --- 盐酸金刚烷胺：强兴奋、幻觉、精神病、心脏毒性 ---
	public static final Consumable AMANTADINE_CONSUMABLE = Consumables.defaultFood()
			// 强兴奋
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.SPEED, 30 * 20, 2), 1.0f))
			// 幻觉 / 精神病
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.DARKNESS, 60 * 20, 1), 1.0f))
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.BLINDNESS, 40 * 20, 1), 1.0f))
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.NAUSEA, 150 * 20, 1), 1.0f))
			// 心脏毒性
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.POISON, 30 * 20, 1), 1.0f))
			// 后遗
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.SLOWNESS, 180 * 20, 0), 1.0f))
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.WEAKNESS, 180 * 20, 0), 1.0f))
			.build();
	public static final FoodProperties AMANTADINE_FOOD = new FoodProperties.Builder()
			.nutrition(0).saturationModifier(0f).alwaysEdible().build();

	// --- 加巴喷丁胶囊：镇静、眩晕、共济失调、宿醉样后遗 ---
	public static final Consumable GABAPENTIN_CONSUMABLE = Consumables.defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.REGENERATION, 20 * 20, 0), 1.0f))
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.SLOWNESS, 150 * 20, 1), 1.0f))
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.NAUSEA, 90 * 20, 0), 1.0f))
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.WEAKNESS, 180 * 20, 1), 1.0f))
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HUNGER, 180 * 20, 0), 1.0f))
			.build();
	public static final FoodProperties GABAPENTIN_FOOD = new FoodProperties.Builder()
			.nutrition(0).saturationModifier(0f).alwaysEdible().build();

	// --- 戊酸雌二醇片：雌激素（HRT），正面作用 ---
	public static final Consumable ESTRADIOL_CONSUMABLE = Consumables.defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.REGENERATION, 60 * 20, 0), 1.0f))
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.ABSORPTION, 120 * 20, 1), 1.0f))
			.build();
	public static final FoodProperties ESTRADIOL_FOOD = new FoodProperties.Builder()
			.nutrition(1).saturationModifier(0.2f).alwaysEdible().build();

	// --- 愈美片：含右美沙芬的止咳药；高剂量解离、幻觉 ---
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

	// --- 色谱龙 50mg：抗雄激素（Androcur / 醋酸环丙孕酮），正面作用 ---
	public static final Consumable ANDROCUR_CONSUMABLE = Consumables.defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.RESISTANCE, 60 * 20, 0), 1.0f))
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.REGENERATION, 40 * 20, 0), 1.0f))
			.build();
	public static final FoodProperties ANDROCUR_FOOD = new FoodProperties.Builder()
			.nutrition(1).saturationModifier(0.2f).alwaysEdible().build();

	public static final Item PREGABALIN_CAPSULE = register(
			key("pregabalin_capsule"),
			warn(new Item.Properties(), "pregabalin_capsule").food(PREGABALIN_FOOD, PREGABALIN_CONSUMABLE));

	public static final Item DEXTROMETHORPHAN = register(
			key("dextromethorphan"),
			warn(new Item.Properties(), "dextromethorphan").food(DEXTROMETHORPHAN_FOOD, DEXTROMETHORPHAN_CONSUMABLE));

	public static final Item AMANTADINE = register(
			key("amantadine"),
			warn(new Item.Properties(), "amantadine").food(AMANTADINE_FOOD, AMANTADINE_CONSUMABLE));

	public static final Item GABAPENTIN_CAPSULE = register(
			key("gabapentin_capsule"),
			warn(new Item.Properties(), "gabapentin_capsule").food(GABAPENTIN_FOOD, GABAPENTIN_CONSUMABLE));

	public static final Item ESTRADIOL_VALERATE = register(
			key("estradiol_valerate"),
			warn(new Item.Properties(), "estradiol_valerate").food(ESTRADIOL_FOOD, ESTRADIOL_CONSUMABLE));

	public static final Item YUMEI_TABLET = register(
			key("yumei_tablet"),
			warn(new Item.Properties(), "yumei_tablet").food(YUMEI_FOOD, YUMEI_CONSUMABLE));

	public static final Item ANDROCUR_50MG = register(
			key("androcur_50mg"),
			warn(new Item.Properties(), "androcur_50mg").food(ANDROCUR_FOOD, ANDROCUR_CONSUMABLE));

	private static ResourceKey<Item> key(String name) {
		return ResourceKey.create(Registries.ITEM, MtFodMod.id(name));
	}

	private static Item register(ResourceKey<Item> key, Item.Properties props) {
		Item item = new Item(props.setId(key));
		return Registry.register(BuiltInRegistries.ITEM, key, item);
	}

	private static Item.Properties warn(Item.Properties props, String id) {
		return props.component(DataComponents.LORE, new ItemLore(List.of(
				Component.translatable("item.mtfodmod." + id + ".lore1"),
				Component.translatable("item.mtfodmod." + id + ".lore2"))));
	}

	public static void initialize() {
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(tab -> {
			tab.accept(PREGABALIN_CAPSULE);
			tab.accept(DEXTROMETHORPHAN);
			tab.accept(AMANTADINE);
			tab.accept(GABAPENTIN_CAPSULE);
			tab.accept(ESTRADIOL_VALERATE);
			tab.accept(YUMEI_TABLET);
			tab.accept(ANDROCUR_50MG);
		});
	}
}
