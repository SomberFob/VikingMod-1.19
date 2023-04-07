package net.somberfob.vikingmod.event;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.somberfob.vikingmod.VikingMod;
import net.somberfob.vikingmod.client.gui.HealthBarGui;
import net.somberfob.vikingmod.client.gui.InformationGUI.InformationDisplayGui;
import net.somberfob.vikingmod.entities.ModEntityType;
import net.somberfob.vikingmod.util.KeyBinding;
import net.somberfob.vikingmod.world.feature.item.custom.renderer.ThrownVikingAxeRenderer;
import net.somberfob.vikingmod.world.feature.item.custom.renderer.ThrownVikingSpearRenderer;

@Mod.EventBusSubscriber(modid = VikingMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {
    @Mod.EventBusSubscriber(modid = VikingMod.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if (KeyBinding.SHOUTING_KEY.consumeClick()) {
                //Minecraft.getInstance().player.playSound(SoundEvents.PILLAGER_CELEBRATE);

                // Testing
                Minecraft.getInstance().setScreen(new InformationDisplayGui(new ResourceLocation(VikingMod.MOD_ID,"textures/gui/information_display/information_component/background1.png"),
                                                                            Component.literal("Works"), Component.literal("Nice").withStyle(ChatFormatting.DARK_PURPLE),
                        21, 12));
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