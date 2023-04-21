package net.somberfob.vikingmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {

    public static final FoodProperties GOLDEN_RASPBERRIES = (new FoodProperties.Builder()).nutrition(3).saturationMod(1.5F).effect(new MobEffectInstance(MobEffects.REGENERATION, 90, 1), 1.0F).alwaysEat().build();


}
