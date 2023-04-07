package net.somberfob.vikingmod.client.gui.InformationGUI;

import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MerchantMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public abstract class InformationTask {
    protected static List<InformationTask> informationTasks = new ArrayList<>();
    protected static int id;
    protected boolean taskCompleted;
    protected boolean displayShown;
    private AbstractVillager tradingVillager;
    private int[] initialTradeUses;
    protected Player player;
    protected final Item taskItem;
    private final TaskTypes taskType;

    public enum TaskTypes {
        CRAFT,
        TRADE,
        PICKUP,
        SMELT
    }

    public InformationTask(TaskTypes taskType, Item taskItem) {
        InformationTask.id++;
        this.taskType = taskType;
        this.taskItem = taskItem;
        InformationTask.informationTasks.add(this);
    }

    private void executeTask(TaskTypes taskType, ItemStack itemResult, Player player) {
        if (this.canNotStartTask(taskType)) {
            return;
        }

        this.player  = player;
        completeTask(itemResult);
    }

    private boolean canNotStartTask(TaskTypes taskType) {
        if (taskCompleted) {
            return true;
        }
        return this.taskType != taskType;
    }

    private void completeTask(ItemStack itemStack) {
        if (!itemStack.getItem().equals(this.taskItem)) {
            return;
        }
        this.taskCompleted = true;
    }

    @SubscribeEvent
    public void onPickUp(PlayerEvent.ItemPickupEvent event) {
        executeTask(TaskTypes.PICKUP, event.getStack(), event.getEntity());
    }

    @SubscribeEvent
    public void onSmelt(PlayerEvent.ItemSmeltedEvent event) {
        executeTask(TaskTypes.SMELT, event.getSmelting(), event.getEntity());
    }

    @SubscribeEvent
    public void onCraft(PlayerEvent.ItemCraftedEvent event) {
        executeTask(TaskTypes.CRAFT, event.getCrafting(), event.getEntity());
    }

    @SubscribeEvent
    public void onTrading(TickEvent.PlayerTickEvent event) {
        executeTradingTask(this.tradingVillager, event.player);
    }

    @SubscribeEvent
    public void onPlayerOpensVillagerGui(PlayerContainerEvent.Open event) {
        if (event.getContainer() instanceof MerchantMenu) {
            this.initialTradeUses = null;
        }
    }

    private void executeTradingTask(AbstractVillager villager, Player player) {
        if (this.tradingVillager == null || !this.tradingVillager.isTrading()) {
            return;
        }

        int numOffers = villager.getOffers().size();

        if (this.initialTradeUses == null) {
            this.initialTradeUses = new int[numOffers];
            for (int i = 0; i < numOffers; i++) {
                this.initialTradeUses[i] = villager.getOffers().get(i).getUses();
            }
        }
        else {
            for (int i = 0; i < numOffers; i++) {
                MerchantOffer offer = villager.getOffers().get(i);
                int previousUses = this.initialTradeUses[i];
                int currentUses = offer.getUses();

                if (previousUses != currentUses) {
                    this.initialTradeUses[i] = currentUses;
                    this.executeTask(TaskTypes.TRADE, offer.getResult(), player);
                }
            }
        }
    }

    @SubscribeEvent
    public void getTradingVillager(PlayerInteractEvent.EntityInteract event) {
        if (event.getTarget() instanceof AbstractVillager) {
            this.tradingVillager = (AbstractVillager) event.getTarget();
        }
    }
}

