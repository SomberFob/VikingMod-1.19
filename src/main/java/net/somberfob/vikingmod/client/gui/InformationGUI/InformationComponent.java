package net.somberfob.vikingmod.client.gui.InformationGUI;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.somberfob.vikingmod.VikingMod;

public class InformationComponent extends InformationTask {
    private final ResourceLocation backgroundIMG;
    private final MutableComponent title;
    private final int titleFontSize;
    private final MutableComponent description;
    private final int descriptionFontSize;
    private int taskID;

    public InformationComponent(TaskTypes taskType, Item taskItem, String backgroundImg, MutableComponent title, MutableComponent description) {
        super(taskType, taskItem);
        this.backgroundIMG = new ResourceLocation(VikingMod.MOD_ID, "textures/gui/information_display/information_component/" + backgroundImg);
        this.title = title;
        this.description = description;
        this.titleFontSize = 12;
        this.descriptionFontSize = 6;
        this.taskID = InformationTask.id;
    }

    public InformationComponent(TaskTypes taskType, Item taskItem, String backgroundImg, MutableComponent title, MutableComponent description, int titleFontSize, int descriptionFontSize) {
        super(taskType, taskItem);
        this.backgroundIMG = new ResourceLocation(VikingMod.MOD_ID, "textures/gui/information_display/information_component/" + backgroundImg);
        this.title = title;
        this.description = description;
        this.titleFontSize = titleFontSize;
        this.descriptionFontSize = descriptionFontSize;
        this.taskID = InformationTask.id;
    }

    @SubscribeEvent
    public void onRender(RenderGuiOverlayEvent event) {
        this.openScreen();
    }

    private boolean canOpenScreen() {
        if (Minecraft.getInstance().player == null) {
            return false;
        }

        return this.taskCompleted &&
                !this.displayShown &&
                Minecraft.getInstance().player.tickCount % 30 == 0 &&
                !this.player.getCombatTracker().isInCombat();
    }

    private void openScreen() {
        if (canOpenScreen()) {
            Minecraft.getInstance().setScreen(new InformationDisplayGui(this.backgroundIMG, this.title, this.description, this.titleFontSize, this.descriptionFontSize));
            this.displayShown = true;
        }
    }
}
