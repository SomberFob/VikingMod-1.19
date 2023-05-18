package net.somberfob.vikingmod.event;

import net.somberfob.vikingmod.VikingMod;
import net.somberfob.vikingmod.entity.client.armor.SaxonHelmetRenderer;
import net.somberfob.vikingmod.item.custom.SaxonHelmetItem;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;
import software.bernie.example.GeckoLibMod;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = VikingMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventClientBusEvents {
    @SubscribeEvent
    public static void registerArmorRenderers(final EntityRenderersEvent.AddLayers event) {
        GeoArmorRenderer.registerArmorRenderer(SaxonHelmetItem.class, new SaxonHelmetRenderer());
    }
}