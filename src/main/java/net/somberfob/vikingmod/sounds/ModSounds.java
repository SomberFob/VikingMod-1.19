package net.somberfob.vikingmod.sounds;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.somberfob.vikingmod.VikingMod;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, VikingMod.MOD_ID);

    public static final RegistryObject<SoundEvent> VIKING_CHARGE = registerSoundEvent("viking_charge");
    public static final RegistryObject<SoundEvent> CAVE_DRUMS = registerSoundEvent("cave_drums");

    public static final RegistryObject<SoundEvent> AXE_PICKUP = registerSoundEvent("axe_pickup");
    public static final RegistryObject<SoundEvent> AXE_SELECT = registerSoundEvent("axe_select");
    public static final RegistryObject<SoundEvent> AXE_THROW = registerSoundEvent("axe_throw");
    public static final RegistryObject<SoundEvent> NULL = registerSoundEvent("null");

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(VikingMod.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}

