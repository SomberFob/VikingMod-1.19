package net.somberfob.vikingmod;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.somberfob.vikingmod.block.ModBlocks;
import net.somberfob.vikingmod.block.entities.ModBlockEntities;
import net.somberfob.vikingmod.entity.ModEntityTypes;
import net.somberfob.vikingmod.item.ModItems;
import net.somberfob.vikingmod.painting.ModPaintings;
import net.somberfob.vikingmod.screen.ModMenuTypes;
import net.somberfob.vikingmod.screen.crate.CrateScreen;
import net.somberfob.vikingmod.screen.fishingtrap.FishingTrapScreen;
import net.somberfob.vikingmod.sounds.ModSounds;
import net.somberfob.vikingmod.world.feature.ModConfiguredFeatures;
import net.somberfob.vikingmod.world.feature.ModPlacedFeatures;
import org.slf4j.Logger;
import software.bernie.geckolib3.GeckoLib;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(VikingMod.MOD_ID)
public class VikingMod {
    public static final String MOD_ID = "vikingmod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public VikingMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEntityTypes.register(modEventBus);
        ModSounds.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        ModConfiguredFeatures.register(modEventBus);
        ModPlacedFeatures.register(modEventBus);
        ModPaintings.register(modEventBus);

        GeckoLib.initialize();

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }


    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MenuScreens.register(ModMenuTypes.FISHING_TRAP_MENU.get(), FishingTrapScreen::new);
            MenuScreens.register(ModMenuTypes.CRATE_MENU.get(), CrateScreen::new);

        }
    }
}
