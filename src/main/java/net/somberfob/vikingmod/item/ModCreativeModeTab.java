package net.somberfob.vikingmod.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.somberfob.vikingmod.block.ModBlocks;

public class ModCreativeModeTab {
    public static final CreativeModeTab CRAFTING_MATERIALS = new CreativeModeTab("crafting_materials") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.SILVER.get());
        }
    };
    public static final CreativeModeTab CONSUMABLES = new CreativeModeTab("consumables") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.GOLDEN_RASPBERRIES.get());
        }
    };
    public static final CreativeModeTab WEAPONS = new CreativeModeTab("weapons") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.SILVER_SWORD.get());
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
    public static final CreativeModeTab EQUIPMENT = new CreativeModeTab("equipment") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.SILVER_CHESTPLATE.get());
        }
    };
    public static final CreativeModeTab RUNES_AND_UPGRADES = new CreativeModeTab("runes_and_upgrades") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.RUNE_OF_FURY.get());
        }
    };

}
