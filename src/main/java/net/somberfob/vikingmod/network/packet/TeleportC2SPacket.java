package net.somberfob.vikingmod.network.packet;

import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraftforge.network.NetworkEvent;
import net.somberfob.vikingmod.util.Util;

import java.util.function.Supplier;

public class TeleportC2SPacket {
    private ResourceKey<Level> dimension;
    private double range;
    private double minY;
    private double maxY;

    public TeleportC2SPacket(ResourceKey<Level> dimension, double range, double minY, double maxY) {
        this.dimension = dimension;
        this.range = range;
        this.minY = minY;
        this.maxY = maxY;
    }

    public TeleportC2SPacket(FriendlyByteBuf buf) {
        this.dimension = buf.readResourceKey(Registry.DIMENSION_REGISTRY);
        this.range = buf.readDouble();
        this.minY = buf.readDouble();
        this.maxY = buf.readDouble();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeResourceKey(this.dimension);
        buf.writeDouble(this.range);
        buf.writeDouble(this.minY);
        buf.writeDouble(this.maxY);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            Util.teleportToDimension(player, this.dimension, this.range, this.minY, this.maxY);
        });
        return true;
    }
}