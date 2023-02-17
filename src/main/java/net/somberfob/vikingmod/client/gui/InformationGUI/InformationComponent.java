package net.somberfob.vikingmod.client.gui.InformationGUI;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.somberfob.vikingmod.VikingMod;
import net.somberfob.vikingmod.sounds.ModSounds;

public class InformationComponent extends InformationTask {
    private final ResourceLocation backgroundIMG;
    private final MutableComponent title;
    private final int titleFontSize;
    private final MutableComponent description;
    private final int descriptionFontSize;

    public InformationComponent(TaskTypes taskType, Item taskItem, String backgroundImg, MutableComponent title, MutableComponent description) {
        super(taskType, taskItem);
        this.backgroundIMG = new ResourceLocation(VikingMod.MOD_ID, "textures/gui/information_display/information_component/" + backgroundImg);
        this.title = title;
        this.description = description;
        this.titleFontSize = 12;
        this.descriptionFontSize = 6;
    }

    public InformationComponent(TaskTypes taskType, Item taskItem, String backgroundImg, MutableComponent title, MutableComponent description, int titleFontSize, int descriptionFontSize) {
        super(taskType, taskItem);
        this.backgroundIMG = new ResourceLocation(VikingMod.MOD_ID, "textures/gui/information_display/information_component/" + backgroundImg);
        this.title = title;
        this.description = description;
        this.titleFontSize = titleFontSize;
        this.descriptionFontSize = descriptionFontSize;
    }

    @Override
    protected void openScreen() {
        if (this.taskCompleted && !displayShown) {
            Minecraft.getInstance().setScreen(new InformationDisplayGui(this.backgroundIMG, this.title, this.description, this.titleFontSize, this.descriptionFontSize));
            this.displayShown = true;
        }
    }
}
