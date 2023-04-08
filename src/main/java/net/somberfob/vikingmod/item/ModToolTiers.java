package net.somberfob.vikingmod.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import net.somberfob.vikingmod.VikingMod;
import net.somberfob.vikingmod.util.ModTags;

import java.util.List;

public class ModToolTiers {
    public static Tier SILVER;

    static {
        SILVER = TierSortingRegistry.registerTier(
                new ForgeTier(3,250, 7f, 3f, 24,
                        ModTags.Blocks.NEEDS_SILVER_TOOL, () -> Ingredient.of(ModItems.SILVER.get())),
                new ResourceLocation(VikingMod.MOD_ID, "silver"), List.of(Tiers.IRON), List.of());

    }
}
