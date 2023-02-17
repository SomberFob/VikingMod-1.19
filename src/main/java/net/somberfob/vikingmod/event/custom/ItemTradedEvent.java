package net.somberfob.vikingmod.event.custom;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.EventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

@Mod.EventBusSubscriber
public class ItemTradedEvent extends TickEvent.PlayerTickEvent {
    public ItemTradedEvent(Phase phase, Player player) {
        super(phase, player);
    }

    @NotNull
    public String getTradedItemStack()
    {
        return "Fucked";
    }


}
