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
    public static final CreativeModeTab FOOD_TAB = new CreativeModeTab("foodtab") {
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
            return new ItemStack(ModBlocks.BLACK_SLATE.get());
        }
    };
    public static final CreativeModeTab DECORATIVE = new CreativeModeTab("decorative") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.CRATE.get());
        }
    };
    public static final CreativeModeTab BARTER_AND_CRAFTING_ITEMS = new CreativeModeTab("barterandcraftingitems") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.SILVER_COINS.get());
        }
    };
    public static final CreativeModeTab ARMOR_TAB = new CreativeModeTab("armortab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.SILVER_CHESTPLATE.get());
        }
    };
    public static final CreativeModeTab PLANTS = new CreativeModeTab("plants") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.NETTLE.get());
        }
    };

}
