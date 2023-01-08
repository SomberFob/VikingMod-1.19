package net.somberfob.vikingmod.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

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
            return new ItemStack(ModItems.CHICKEN_LEG.get());
        }
    };
    public static final CreativeModeTab TOOLS_TAB = new CreativeModeTab("toolstab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.SILVER_SWORD.get());
        }
    };
    public static final CreativeModeTab BLOCKS_TAB = new CreativeModeTab("blockstab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.SLATE.get());
        }
    };
    public static final CreativeModeTab ARMOR_TAB = new CreativeModeTab("armortab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.SILVER_CHESTPLATE.get());
        }
    };
    public static final CreativeModeTab MISCELLANEOUS_TAB = new CreativeModeTab("miscellaneoustab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.BEAR_SPAWN_EGG.get());
        }
    };
}
