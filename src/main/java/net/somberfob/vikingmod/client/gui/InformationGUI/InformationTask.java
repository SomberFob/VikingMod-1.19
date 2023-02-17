package net.somberfob.vikingmod.client.gui.InformationGUI;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkHooks;
import net.somberfob.vikingmod.event.custom.ItemTradedEvent;

public abstract class InformationTask {
    protected boolean taskCompleted;
    protected boolean displayShown;
    private final Item taskItem;
    private final TaskTypes taskType;
    public enum TaskTypes {
        CRAFT,
        TRADE,
        PICKUP,
        SMELT
    }

    public InformationTask(TaskTypes taskType, Item taskItem) {
        this.taskType = taskType;
        this.taskItem = taskItem;
    }

    private boolean canNotStartTask(TaskTypes taskType) {
        if (taskCompleted) {
            return true;
        }

        else return this.taskType != taskType;
    }

    private void completeTask (Item item) {
        if (item.equals(this.taskItem))
        {
            this.taskCompleted = true;
        }
    }

    @SubscribeEvent
    public void onPickUp(PlayerEvent.ItemPickupEvent event) {
        if (canNotStartTask(TaskTypes.PICKUP)) {
            return;
        };

        this.completeTask(event.getStack().getItem());
    }

    // Not implemented
    @SubscribeEvent
    public void onTrade(ItemTradedEvent event) {
    }

    @SubscribeEvent
    public void onSmelt(PlayerEvent.ItemSmeltedEvent event) {
        if (canNotStartTask(TaskTypes.SMELT)) {
            return;
        };

        this.completeTask(event.getSmelting().getItem());
    }

    @SubscribeEvent
    public void onCraft(PlayerEvent.ItemCraftedEvent event) {
        if (canNotStartTask(TaskTypes.CRAFT)) {
            return;
        };

        this.completeTask(event.getCrafting().getItem());
    }

    @SubscribeEvent
    public void GameOver(RenderGuiOverlayEvent event) {
        this.openScreen();
    }

    protected abstract void openScreen();
}
