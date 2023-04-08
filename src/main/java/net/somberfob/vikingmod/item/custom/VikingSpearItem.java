package net.somberfob.vikingmod.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.somberfob.vikingmod.item.custom.thrown.ThrownVikingSpear;
import org.jetbrains.annotations.NotNull;

public class VikingSpearItem extends SwordItem {
    public VikingSpearItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, Player pPlayer, @NotNull InteractionHand pUsedHand) {
        pPlayer.startUsingItem(pUsedHand);
        return InteractionResultHolder.consume(pPlayer.getItemInHand(pUsedHand));
    }

    @Override
    public void releaseUsing(@NotNull ItemStack pStack, @NotNull Level pLevel, @NotNull LivingEntity pLivingEntity, int pTimeCharged) {
        float remainingTime = this.getUseDuration(pStack) - pTimeCharged;

        if (!(pLivingEntity instanceof Player player)) {
            return;
        }

        if (remainingTime >= 10 && !pLevel.isClientSide) {
            ThrownVikingSpear thrownVikingAxe = new ThrownVikingSpear(player, pLevel, pStack);
            //thrownVikingAxe.setItem(pStack);
            thrownVikingAxe.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            pLevel.addFreshEntity(thrownVikingAxe);
            player.getInventory().removeItem(pStack);
        }
    }



    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack pStack) {
        return UseAnim.SPEAR;
    }

    @Override
    public int getUseDuration(@NotNull ItemStack pStack) {
        return 72000;
    }
}
