package net.somberfob.vikingmod.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.somberfob.vikingmod.VikingMod;
import net.somberfob.vikingmod.block.custom.BlackSlateSpireBlock;
import net.somberfob.vikingmod.block.custom.CrateBlock;
import net.somberfob.vikingmod.block.custom.FishingTrapBlock;
import net.somberfob.vikingmod.world.feature.item.ModCreativeModeTab;
import net.somberfob.vikingmod.world.feature.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, VikingMod.MOD_ID);

    public static final RegistryObject<Block> SILVER_BLOCK = registerBlock("silver_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.ORES_TAB);


    public static final RegistryObject<Block> STONE_BRICKS_FANCY_0 = registerBlock("stone_bricks_fancy_0",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(3f).requiresCorrectToolForDrops()), ModCreativeModeTab.BUILDING_BLOCKS);

    public static final RegistryObject<Block> STONE_BRICKS_FANCY_1 = registerBlock("stone_bricks_fancy_1",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(3f).requiresCorrectToolForDrops()), ModCreativeModeTab.BUILDING_BLOCKS);

    public static final RegistryObject<Block> STONE_FANCY_0 = registerBlock("stone_fancy_0",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(3f).requiresCorrectToolForDrops()), ModCreativeModeTab.BUILDING_BLOCKS);
    public static final RegistryObject<Block> STONE_FANCY_1 = registerBlock("stone_fancy_1",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(3f).requiresCorrectToolForDrops()), ModCreativeModeTab.BUILDING_BLOCKS);
    public static final RegistryObject<Block> STONE_FANCY_2 = registerBlock("stone_fancy_2",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(3f).requiresCorrectToolForDrops()), ModCreativeModeTab.BUILDING_BLOCKS);


    public static final RegistryObject<Block> BLACK_SLATE = registerBlock("black_slate",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.BUILDING_BLOCKS);
    public static final RegistryObject<Block> BLACK_SLATE_STAIRS = registerBlock("black_slate_stairs",
            () -> new StairBlock(() -> ModBlocks.BLACK_SLATE.get().defaultBlockState(),
                    BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()), ModCreativeModeTab.BUILDING_BLOCKS);
    public static final RegistryObject<Block> BLACK_SLATE_SLAB = registerBlock("black_slate_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(5f).requiresCorrectToolForDrops()), ModCreativeModeTab.BUILDING_BLOCKS);
    public static final RegistryObject<Block> BLACK_SLATE_FENCE = registerBlock("black_slate_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(5f).requiresCorrectToolForDrops()), ModCreativeModeTab.BUILDING_BLOCKS);
    public static final RegistryObject<Block> BLACK_SLATE_WALL = registerBlock("black_slate_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(5f).requiresCorrectToolForDrops()), ModCreativeModeTab.BUILDING_BLOCKS);

    public static final RegistryObject<Block> LEATHER_BLOCK = registerBlock("leather_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.BUILDING_BLOCKS);
    public static final RegistryObject<Block> LEATHER_SLAB = registerBlock("leather_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(5f).requiresCorrectToolForDrops()), ModCreativeModeTab.BUILDING_BLOCKS);
    public static final RegistryObject<Block> LEATHER_CARPET = registerBlock("leather_carpet",
            () -> new CarpetBlock(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(5f).requiresCorrectToolForDrops()), ModCreativeModeTab.BUILDING_BLOCKS);

    public static final RegistryObject<Block> BLACK_SLATE_SPIRE = registerBlock("black_slate_spire",
            () -> new BlackSlateSpireBlock(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(6f).requiresCorrectToolForDrops().noOcclusion()), ModCreativeModeTab.BUILDING_BLOCKS);


    public static final RegistryObject<Block> SILVER_ORE = registerBlock("silver_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(6f).requiresCorrectToolForDrops(),
                    UniformInt.of(3, 7)), ModCreativeModeTab.ORES_TAB);
    public static final RegistryObject<Block> DEEPSLATE_SILVER_ORE = registerBlock("deepslate_silver_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(6f).requiresCorrectToolForDrops(),
                    UniformInt.of(3, 7)), ModCreativeModeTab.ORES_TAB);

    public static final RegistryObject<Block> FISHING_TRAP = registerBlock("fishing_trap",
            () -> new FishingTrapBlock(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(2f).noOcclusion()), ModCreativeModeTab.DECORATIVE);

    public static final RegistryObject<Block> CRATE = registerBlock("crate",
            () -> new CrateBlock(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(2f)
                    .noOcclusion()),
            new Item.Properties()
                    .stacksTo(1)
                    .tab(ModCreativeModeTab.DECORATIVE));



    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, Item.Properties properties) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, properties);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            Item.Properties properties) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), properties));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
