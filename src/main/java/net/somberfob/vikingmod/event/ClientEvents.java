package net.somberfob.vikingmod.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.*;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.somberfob.vikingmod.VikingMod;
import net.somberfob.vikingmod.client.gui.HealthBarGui;
import net.somberfob.vikingmod.client.gui.InformationGUI.InformationDisplayGui;
import net.somberfob.vikingmod.entities.ModEntityType;
import net.somberfob.vikingmod.world.feature.item.custom.renderer.ThrownVikingAxeRenderer;
import net.somberfob.vikingmod.world.feature.item.custom.renderer.ThrownVikingSpearRenderer;
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

        @SubscribeEvent
        public static void onRenderLiving(RenderLivingEvent.Post<? extends LivingEntity, ? extends EntityModel<?>> event) {
            HealthBarGui.render(event);
        }
    }

    @Mod.EventBusSubscriber(modid = VikingMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBinding.SHOUTING_KEY);
        }

        @SubscribeEvent
        public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(ModEntityType.VIKING_AXE.get(), ThrownVikingAxeRenderer::new);
            event.registerEntityRenderer(ModEntityType.VIKING_SPEAR.get(), ThrownVikingSpearRenderer::new);
            event.registerEntityRenderer(ModEntityType.OIL_JAR.get(), ThrownItemRenderer::new);
        }
    }
}