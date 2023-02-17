package net.somberfob.vikingmod.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.somberfob.vikingmod.VikingMod;
import net.somberfob.vikingmod.world.feature.item.custom.thrown.ThrownOilJar;
import net.somberfob.vikingmod.world.feature.item.custom.thrown.ThrownVikingAxe;
import net.somberfob.vikingmod.world.feature.item.custom.thrown.ThrownVikingSpear;

public class ModEntityType {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, VikingMod.MOD_ID);

    public static final RegistryObject<EntityType<ThrownOilJar>> OIL_JAR =
            ENTITY_TYPES.register("oil_jar",
                                  () -> EntityType.Builder.of((EntityType.EntityFactory<ThrownOilJar>) ThrownOilJar::new, MobCategory.MISC)
                                          .build("oil_jar"));

    public static final RegistryObject<EntityType<ThrownVikingAxe>> VIKING_AXE =
            ENTITY_TYPES.register("viking_axe",
                                  () -> EntityType.Builder.of((EntityType.EntityFactory<ThrownVikingAxe>) ThrownVikingAxe::new, MobCategory.MISC)
                                          .sized(0.5F, 0.5F)
                                          .build("viking_axe"));

    public static final RegistryObject<EntityType<ThrownVikingSpear>> VIKING_SPEAR =
            ENTITY_TYPES.register("viking_spear",
                                  () -> EntityType.Builder.of((EntityType.EntityFactory<ThrownVikingSpear>) ThrownVikingSpear::new, MobCategory.MISC)
                                          .sized(0.5F, 0.5F)
                                          .clientTrackingRange(4)
                                          .updateInterval(20)
                                          .build("viking_spear"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
