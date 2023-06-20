package net.somberfob.vikingmod.network.packet;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;
import net.somberfob.vikingmod.client.gui.JoinGui;
import net.somberfob.vikingmod.util.Util;

import java.util.function.Supplier;

public class JoinGuiS2CPacket {
    public JoinGuiS2CPacket() {
    }

    public JoinGuiS2CPacket(FriendlyByteBuf buf) {
    }

    public void toBytes(FriendlyByteBuf buf) {
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(JoinGui::open);
            return true;
    }
}
