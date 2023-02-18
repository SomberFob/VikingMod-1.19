package net.somberfob.vikingmod.client.gui.InformationGUI;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.somberfob.vikingmod.VikingMod;

public class ModInformationComponent {
    public static void register(InformationComponent informationComponent) {;
        MinecraftForge.EVENT_BUS.register(informationComponent);
    }

    public static void register() {
        register(new InformationComponent(InformationTask.TaskTypes.PICKUP, Items.COOKED_CHICKEN,
                                          "background1.png",
                                          Component.literal("Works"), Component.literal("Nice").withStyle(ChatFormatting.DARK_PURPLE),
                                          8, 12));

        register(new InformationComponent(InformationTask.TaskTypes.CRAFT, Items.CRAFTING_TABLE,
                                          "background2.png",
                                          Component.literal("Works").withStyle(ChatFormatting.RED), Component.literal("Nice").withStyle(ChatFormatting.YELLOW)));
    }
}
