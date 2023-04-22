package net.somberfob.vikingmod.item.custom;

import com.mojang.authlib.minecraft.client.MinecraftClient;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec2;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.somberfob.vikingmod.VikingMod;
import net.somberfob.vikingmod.sounds.ModSounds;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Array;

public class BannerWeapon extends SwordItem {
    private boolean soundPlayed = false;

    public BannerWeapon(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public void inventoryTick(@NotNull ItemStack pStack, @NotNull Level pLevel, @NotNull Entity pEntity, int pSlotId, boolean pIsSelected) {
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);

        if (!pIsSelected) return;

        CompoundTag tag = pStack.getTag();
        soundPlayed = tag.getBoolean("soundPlayed");

        if (!soundPlayed) {
            for (SoundEvent sound : selectSound()) pEntity.playSound(sound, 1, 1);
            tag.putBoolean("soundPlayed", true);
            pStack.setTag(tag);
        }
    }



    protected SoundEvent[] selectSound() {
        return new SoundEvent[]{
                ModSounds.WAR_HORN.get(),
                SoundEvents.PLAYER_LEVELUP
        };
    }
}