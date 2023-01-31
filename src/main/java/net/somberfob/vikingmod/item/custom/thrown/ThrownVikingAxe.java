package net.somberfob.vikingmod.item.custom.thrown;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.somberfob.vikingmod.entities.ModEntityType;
import net.somberfob.vikingmod.item.ModItems;
import net.somberfob.vikingmod.sounds.ModSounds;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class ThrownVikingAxe extends AbstractThrowableWeapon {
    private float rotationYP = -45;

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
    protected boolean canDestroyGlass() {
        return true;
    }

    public float getRotationYP() {
        return this.rotationYP;
    }

    private static final EntityDataAccessor<Boolean> OBJECT_GOT_HIT = SynchedEntityData.defineId(ThrownVikingAxe.class, EntityDataSerializers.BOOLEAN);

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(OBJECT_GOT_HIT, this.isStuckInBlock);
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
