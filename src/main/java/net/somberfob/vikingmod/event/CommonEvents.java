package net.somberfob.vikingmod.event;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import net.somberfob.vikingmod.VikingMod;
import net.somberfob.vikingmod.sounds.ModSounds;


public class CommonEvents {
    @Mod.EventBusSubscriber(modid = VikingMod.MOD_ID)
    public static class ForgeEvents {
        static boolean enteredBiome = true;

        @SubscribeEvent
        public static void onPlayerTickEvent(TickEvent.PlayerTickEvent event) {
            playerEnteredBiome(event, Biomes.DEEP_DARK, ModSounds.CAVE_DRUMS.get(),
                               SoundSource.AMBIENT,
                               3f,
                               1.1f);
        }

        public static void playerEnteredBiome(TickEvent.PlayerTickEvent event,
                                              ResourceKey <Biome> biome,
                                              SoundEvent sound,
                                              SoundSource category,
                                              float volume,
                                              float pitch) {
            Player player = event.player;
            Holder<Biome> currentBiome = player.getLevel().getBiome(player.blockPosition());

            if (currentBiome.is(biome) && enteredBiome) {
                event.player.getLevel().playLocalSound(player.getX(), player.getY(), player.getZ(),
                                                       sound, category,
                                                       3f, 1.1f, true);
                enteredBiome = false;
            }
            else if (!currentBiome.is(biome) && !enteredBiome) {
                enteredBiome = true;
            }
        }
    }

    @Mod.EventBusSubscriber(modid = VikingMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents {

    }
}
