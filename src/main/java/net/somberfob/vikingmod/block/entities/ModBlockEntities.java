package net.somberfob.vikingmod.block.entities;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.somberfob.vikingmod.VikingMod;
import net.somberfob.vikingmod.block.ModBlocks;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, VikingMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<FishingTrapBlockEntity>> FISHING_TRAP =
            BLOCK_ENTITIES.register("fishing_trap", () ->
                    BlockEntityType.Builder.of(FishingTrapBlockEntity::new,
                                               ModBlocks.FISHING_TRAP.get()).build(null));

    public static final RegistryObject<BlockEntityType<CrateBlockEntity>> CRATE =
            BLOCK_ENTITIES.register("crate", () ->
                    BlockEntityType.Builder.of(CrateBlockEntity::new,
                            ModBlocks.CRATE.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
