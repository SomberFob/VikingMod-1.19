package net.somberfob.vikingmod.item;

import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties TROUT = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.1F).build();
    public static final FoodProperties ROCK_BASS = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).build();

    public static final FoodProperties RATIONS = (new FoodProperties.Builder()).nutrition(5).saturationMod(1.2F).build();
}