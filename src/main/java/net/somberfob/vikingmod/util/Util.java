package net.somberfob.vikingmod.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.ChatType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.AABB;

import java.util.Random;

public class Util {
    public static void teleportToDimension(ServerPlayer player, ResourceKey<Level> world, double range, double minY, double maxY) {
        boolean isEmpty = false;
        Random rand = new Random();
        ServerLevel dimension = player.getServer().getLevel(world);

        while (!isEmpty) {
            player.teleportTo(dimension, randomDouble(range), randomDouble(minY, maxY), randomDouble(range), 0, 0);
            Level level = player.level;
            if (level.noCollision(player)
                    && !level.containsAnyLiquid(player.getBoundingBox())) isEmpty = true;
        }
    }

    public static double randomDouble(double max) {
        return randomDouble(0, max);
    }

    public static double randomDouble(double min, double max) {
        Random random = new Random();
        return min + (max - min) * random.nextDouble();
    }
}
