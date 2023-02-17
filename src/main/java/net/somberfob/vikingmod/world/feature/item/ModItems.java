package net.somberfob.vikingmod.world.feature.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.somberfob.vikingmod.VikingMod;
import net.somberfob.vikingmod.world.feature.item.custom.*;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, VikingMod.MOD_ID);

    public static final RegistryObject<Item> SILVER = ITEMS.register("silver",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ORES_TAB)));
    public static final RegistryObject<Item> RAW_SILVER = ITEMS.register("raw_silver",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ORES_TAB)));

    public static final RegistryObject<Item> OLD_PILLOW = ITEMS.register("old_pillow",
            () -> new OldPillowItem(new Item.Properties().tab(ModCreativeModeTab.BARTER_AND_CRAFTING_ITEMS).stacksTo(3)));
    public static final RegistryObject<Item> SILVER_COINS = ITEMS.register("silver_coins",
            () -> new SilverCoinsItem(new Item.Properties().tab(ModCreativeModeTab.BARTER_AND_CRAFTING_ITEMS).stacksTo(64)));
    public static final RegistryObject<Item> NECKLACE = ITEMS.register("necklace",
            () -> new NecklaceItem(new Item.Properties().tab(ModCreativeModeTab.BARTER_AND_CRAFTING_ITEMS).stacksTo(4)));
    public static final RegistryObject<Item> ROPE = ITEMS.register("rope",
            () -> new RopeItem(new Item.Properties().tab(ModCreativeModeTab.BARTER_AND_CRAFTING_ITEMS).stacksTo(5)));
    public static final RegistryObject<Item> SCROLL = ITEMS.register("scroll",
            () -> new ScrollItem(new Item.Properties().tab(ModCreativeModeTab.BARTER_AND_CRAFTING_ITEMS).stacksTo(1)));
    public static final RegistryObject<Item> BROCHE = ITEMS.register("broche",
            () -> new BrocheItem(new Item.Properties().tab(ModCreativeModeTab.BARTER_AND_CRAFTING_ITEMS).stacksTo(1)));
    public static final RegistryObject<Item> LUTE = ITEMS.register("lute",
            () -> new LuteItem(new Item.Properties().tab(ModCreativeModeTab.BARTER_AND_CRAFTING_ITEMS).stacksTo(1)));

    public static final RegistryObject<Item> TROUT = ITEMS.register("trout",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.FOOD_TAB).food(ModFoods.TROUT)));
    public static final RegistryObject<Item> ROCK_BASS = ITEMS.register("rock_bass",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.FOOD_TAB).food(ModFoods.ROCK_BASS)));
    public static final RegistryObject<Item> RASPBERRYS = ITEMS.register("raspberrys",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.FOOD_TAB).food(ModFoods.RASPBERRYS)));

    public static final RegistryObject<Item> OIL_JAR = ITEMS.register("oil_jar",
            () -> new OilJarItem(new Item.Properties().tab(ModCreativeModeTab.BARTER_AND_CRAFTING_ITEMS)));

    public static final RegistryObject<Item> SILVER_SWORD = ITEMS.register("silver_sword",
            () -> new SwordItem(ModToolTiers.SILVER, 3, 3f,
                    new Item.Properties().tab(ModCreativeModeTab.TOOLS_TAB)));
    public static final RegistryObject<Item> SILVER_PICKAXE = ITEMS.register("silver_pickaxe",
            () -> new PickaxeItem(ModToolTiers.SILVER, 1, 1f,
                    new Item.Properties().tab(ModCreativeModeTab.TOOLS_TAB)));
    public static final RegistryObject<Item> SILVER_SHOVEL = ITEMS.register("silver_shovel",
            () -> new ShovelItem(ModToolTiers.SILVER, 0, 1f,
                    new Item.Properties().tab(ModCreativeModeTab.TOOLS_TAB)));
    public static final RegistryObject<Item> SILVER_AXE = ITEMS.register("silver_axe",
            () -> new AxeItem(ModToolTiers.SILVER, 4, 0f,
                    new Item.Properties().tab(ModCreativeModeTab.TOOLS_TAB)));
    public static final RegistryObject<Item> SILVER_HOE = ITEMS.register("silver_hoe",
            () -> new HoeItem(ModToolTiers.SILVER, 0, 0f,
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


    public static final RegistryObject<Item> SWORD = ITEMS.register("viking_sword",
            () -> new SwordItem(Tiers.GOLD, 5, 4f,
                    new Item.Properties().tab(ModCreativeModeTab.TOOLS_TAB).stacksTo(1)));

    public static final RegistryObject<Item> VIKING_SPEAR = ITEMS.register("viking_spear",
                                                                           () -> new VikingSpearItem(Tiers.GOLD, 6, 2f,
                                                                                                     new Item.Properties().tab(ModCreativeModeTab.TOOLS_TAB).stacksTo(1)));

    public static final RegistryObject<Item> VIKING_AXE = ITEMS.register("viking_axe",
                                                                         () -> new VikingAxeItem(Tiers.GOLD, 6, 4f,
                                                                                                 new Item.Properties().tab(ModCreativeModeTab.TOOLS_TAB).stacksTo(1)));

    public static final RegistryObject<Item> VIKING_HAMMER = ITEMS.register("viking_hammer",
            () -> new SwordItem(Tiers.GOLD, 7, 5f,
                    new Item.Properties().tab(ModCreativeModeTab.TOOLS_TAB).stacksTo(1)));

    public static final RegistryObject<Item> VIKING_SHIELD = ITEMS.register("viking_shield",
            () -> new ShieldItem(new Item.Properties().tab(ModCreativeModeTab.ARMOR_TAB)));
    public static final RegistryObject<Item> SAXON_SHIELD = ITEMS.register("saxon_shield",
            () -> new ShieldItem(new Item.Properties().tab(ModCreativeModeTab.ARMOR_TAB)));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
