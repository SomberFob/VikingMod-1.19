package net.somberfob.vikingmod.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.somberfob.vikingmod.block.ModBlocks;

public class ModCreativeModeTab {
    public static final CreativeModeTab ORES_TAB = new CreativeModeTab("orestab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.SILVER.get());
        }
    };
    public static final CreativeModeTab Food_Tab = new CreativeModeTab("foodtab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.TROUT.get());
        }
    };
    public static final CreativeModeTab TOOLS_TAB = new CreativeModeTab("toolstab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.SILVER_SWORD.get());
        }
    };
    public static final CreativeModeTab BUILDING_BLOCKS = new CreativeModeTab("buildingblocks") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.SLATE_BLOCK.get());
        }
    };
    public static final CreativeModeTab PROPS = new CreativeModeTab("props") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.CRATE.get());
        }
    };
    public static final CreativeModeTab ARMOR_TAB = new CreativeModeTab("armortab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.SILVER_CHESTPLATE.get());
        }
    };

}
