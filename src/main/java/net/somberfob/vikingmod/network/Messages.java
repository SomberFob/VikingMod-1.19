package net.somberfob.vikingmod.network;

import ca.weblite.objc.annotations.Msg;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.somberfob.vikingmod.VikingMod;
import net.somberfob.vikingmod.client.gui.JoinGui;
import net.somberfob.vikingmod.network.packet.JoinGuiS2CPacket;
import net.somberfob.vikingmod.network.packet.TeleportC2SPacket;

public class Messages {
    private static SimpleChannel INSTANCE;

    private static int packetID = 0;
    private static int id () {
        return packetID++;
    }

    public static void register() {
        INSTANCE = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(VikingMod.MOD_ID, "messages"),
                () -> "1.0",
                s -> true,
                s -> true
        );

        INSTANCE.registerMessage(
                packetID++,
                TeleportC2SPacket.class,
                TeleportC2SPacket::toBytes,
                TeleportC2SPacket::new,
                TeleportC2SPacket::handle
        );

        INSTANCE.registerMessage(
                packetID++,
                JoinGuiS2CPacket.class,
                JoinGuiS2CPacket::toBytes,
                JoinGuiS2CPacket::new,
                JoinGuiS2CPacket::handle
        );
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

}
