package net.somberfob.vikingmod.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.somberfob.vikingmod.VikingMod;
import net.somberfob.vikingmod.entity.custom.BearEntity;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, VikingMod.MOD_ID);

    public static final RegistryObject<EntityType<BearEntity>> BEAR =
            ENTITY_TYPES.register("bear",
                                  () -> EntityType.Builder.of(BearEntity::new, MobCategory.CREATURE)
                                          .sized(1f, 1f)
                                          .build(new ResourceLocation(VikingMod.MOD_ID, "bear").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
