package net.somberfob.vikingmod.item.custom.thrown;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.somberfob.vikingmod.entities.ModEntityType;
import net.somberfob.vikingmod.item.ModItems;
import net.somberfob.vikingmod.item.custom.thrown.AbstractThrowableWeapon;
import net.somberfob.vikingmod.sounds.ModSounds;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ThrownVikingAxe extends AbstractThrowableWeapon {
    public float rotationYP = -45;

    public ThrownVikingAxe(EntityType<? extends AbstractThrowableWeapon> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.VIKING_AXE.get();
    }

    public ThrownVikingAxe(LivingEntity pShooter, Level pLevel, ItemStack weapon) {
        super(ModEntityType.VIKING_AXE.get(), pShooter, pLevel, weapon);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.isStuckInBlock) {
            return;
        }
        this.rotationYP -= 15;
    }

    @Override
    protected @Nullable SoundEvent getDefaultPickupSound() {
        return ModSounds.AXE_PICKUP.get();
    }

    @Override
    protected @Nullable SoundEvent getDefaultThrowingSound() {
        return ModSounds.AXE_THROW.get();
    }
}
