package net.somberfob.vikingmod.event;


import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.somberfob.vikingmod.VikingMod;
import net.somberfob.vikingmod.entity.ModEntityTypes;
import net.somberfob.vikingmod.renderer.VikingRenderer;
import net.somberfob.vikingmod.renderer.models.VikingModel;
import net.somberfob.vikingmod.util.KeyBinding;

@Mod.EventBusSubscriber(modid = VikingMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {
    @Mod.EventBusSubscriber(modid = VikingMod.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if (KeyBinding.SHOUTING_KEY.consumeClick()) {
                Minecraft.getInstance().player.playSound(SoundEvents.PILLAGER_CELEBRATE);
            }
        }
    }

    @Mod.EventBusSubscriber(modid = VikingMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBinding.SHOUTING_KEY);
        }
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(VikingModel.LAYER_LOCATION, VikingModel::createBodyLayer);
    }
}