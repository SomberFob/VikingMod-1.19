package net.somberfob.vikingmod.screen;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.somberfob.vikingmod.VikingMod;
import net.somberfob.vikingmod.screen.crate.CrateMenu;
import net.somberfob.vikingmod.screen.fishingtrap.FishingTrapMenu;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, VikingMod.MOD_ID);

    public static final RegistryObject<MenuType<FishingTrapMenu>> FISHING_TRAP_MENU =
            registerMenuType(FishingTrapMenu::new, "fishing_trap_menu");

    public static final RegistryObject<MenuType<CrateMenu>> CRATE_MENU =
            registerMenuType(CrateMenu::new, "crate_menu");

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory,
                                                                                                  String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
