package net.somberfob.vikingmod.event;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.somberfob.vikingmod.VikingMod;
import net.somberfob.vikingmod.client.gui.HealthBarGui;
import net.somberfob.vikingmod.entities.ModEntityType;
import net.somberfob.vikingmod.entity.client.armor.SaxonHelmetRenderer;
import net.somberfob.vikingmod.item.custom.SaxonHelmetItem;
import net.somberfob.vikingmod.util.KeyBinding;
import net.somberfob.vikingmod.item.custom.renderer.ThrownVikingAxeRenderer;
import net.somberfob.vikingmod.item.custom.renderer.ThrownVikingSpearRenderer;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

@Mod.EventBusSubscriber(modid = VikingMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {
    @Mod.EventBusSubscriber(modid = VikingMod.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if (KeyBinding.SHOUTING_KEY.consumeClick()) {
                //Minecraft.getInstance().player.playSound(SoundEvents.PILLAGER_CELEBRATE);
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
    @SubscribeEvent
    public static void registerArmorRenderers(final EntityRenderersEvent.AddLayers event) {
        GeoArmorRenderer.registerArmorRenderer(SaxonHelmetItem.class, new SaxonHelmetRenderer());
    }
}