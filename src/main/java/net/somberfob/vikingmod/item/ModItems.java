package net.somberfob.vikingmod.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.somberfob.vikingmod.VikingMod;
import net.somberfob.vikingmod.entity.ModEntityTypes;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, VikingMod.MOD_ID);

    public static final RegistryObject<Item> SILVER = ITEMS.register("silver",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ORES_TAB)));
    public static final RegistryObject<Item> RAW_SILVER = ITEMS.register("raw_silver",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ORES_TAB)));

    public static final RegistryObject<Item> SLATE = ITEMS.register("slate",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ORES_TAB)));
    public static final RegistryObject<Item> RAW_SLATE = ITEMS.register("raw_slate",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ORES_TAB)));


    public static final RegistryObject<Item> TROUT = ITEMS.register("trout",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.Food_Tab).food(ModFoods.TROUT)));
    public static final RegistryObject<Item> ROCK_BASS = ITEMS.register("rock_bass",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.Food_Tab).food(ModFoods.ROCK_BASS)));

    public static final RegistryObject<Item> SILVER_SWORD = ITEMS.register("silver_sword",
            () -> new SwordItem(ModTiers.SILVER, 3, 3f,
                    new Item.Properties().tab(ModCreativeModeTab.TOOLS_TAB)));
    public static final RegistryObject<Item> SILVER_PICKAXE = ITEMS.register("silver_pickaxe",
            () -> new PickaxeItem(ModTiers.SILVER, 1, 1f,
                    new Item.Properties().tab(ModCreativeModeTab.TOOLS_TAB)));
    public static final RegistryObject<Item> SILVER_SHOVEL = ITEMS.register("silver_shovel",
            () -> new ShovelItem(ModTiers.SILVER, 0, 1f,
                    new Item.Properties().tab(ModCreativeModeTab.TOOLS_TAB)));
    public static final RegistryObject<Item> SILVER_AXE = ITEMS.register("silver_axe",
            () -> new AxeItem(ModTiers.SILVER, 4, 0f,
                    new Item.Properties().tab(ModCreativeModeTab.TOOLS_TAB)));
    public static final RegistryObject<Item> SILVER_HOE = ITEMS.register("silver_hoe",
            () -> new HoeItem(ModTiers.SILVER, 0, 0f,
                    new Item.Properties().tab(ModCreativeModeTab.TOOLS_TAB)));

    public static final RegistryObject<Item> SILVER_HELMET = ITEMS.register("silver_helmet",
            () -> new ArmorItem(ModArmorMaterials.SILVER, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModCreativeModeTab.ARMOR_TAB)));
    public static final RegistryObject<Item> SILVER_CHESTPLATE = ITEMS.register("silver_chestplate",
            () -> new ArmorItem(ModArmorMaterials.SILVER, EquipmentSlot.CHEST,
                    new Item.Properties().tab(ModCreativeModeTab.ARMOR_TAB)));
    public static final RegistryObject<Item> SILVER_LEGGING = ITEMS.register("silver_leggings",
            () -> new ArmorItem(ModArmorMaterials.SILVER, EquipmentSlot.LEGS,
                    new Item.Properties().tab(ModCreativeModeTab.ARMOR_TAB)));
    public static final RegistryObject<Item> SILVER_BOOTS = ITEMS.register("silver_boots",
            () -> new ArmorItem(ModArmorMaterials.SILVER, EquipmentSlot.FEET,
                    new Item.Properties().tab(ModCreativeModeTab.ARMOR_TAB)));

    public static final RegistryObject<Item> DAGGER = ITEMS.register("dagger",
            () -> new SwordItem(Tiers.IRON, 1, 5f,
                    new Item.Properties().tab(ModCreativeModeTab.TOOLS_TAB).stacksTo(1)));

    public static final RegistryObject<Item> VIKING_SHIELD = ITEMS.register("viking_shield",
            () -> new ShieldItem(new Item.Properties().tab(ModCreativeModeTab.ARMOR_TAB)));


    public static final RegistryObject<BowItem> LONGBOW = ITEMS.register("longbow",
            () -> new BowItem(new Item.Properties().tab(ModCreativeModeTab.TOOLS_TAB).durability(500)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
