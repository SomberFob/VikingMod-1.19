package net.somberfob.vikingmod.world.dimension;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.somberfob.vikingmod.VikingMod;

public class ModDimensions {
    public static final ResourceKey<Level> VALHALLA_KEY = ResourceKey.create(Registry.DIMENSION_REGISTRY,
            new ResourceLocation(VikingMod.MOD_ID, "valhalla"));
    public static final  ResourceKey<DimensionType> VALHALLA_TYPE =
            ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, VALHALLA_KEY.registry());

    public static void register() {
        System.out.println("Registering ModDimensions for " + VikingMod.MOD_ID);
    }
    }

