package net.somberfob.vikingmod.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.somberfob.vikingmod.VikingMod;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_SILVER_TOOL
                = tag("needs_silver_tool");


        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(VikingMod.MOD_ID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }
}