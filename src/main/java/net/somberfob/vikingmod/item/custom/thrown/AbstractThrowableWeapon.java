package net.somberfob.vikingmod.item.custom.thrown;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.somberfob.vikingmod.sounds.ModSounds;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractThrowableWeapon extends AbstractArrow {
    protected ItemStack weapon = new ItemStack(this.getDefaultItem());
    protected LivingEntity shooter;
    protected boolean isStuckInBlock;
    protected SoundEvent pickUpSoundEvent;

    public AbstractThrowableWeapon(EntityType<? extends AbstractThrowableWeapon> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public AbstractThrowableWeapon(EntityType<? extends AbstractThrowableWeapon> pEntityType, LivingEntity pShooter, Level pLevel, ItemStack weapon) {
        super(pEntityType, pShooter, pLevel);
        this.weapon = weapon;
        this.shooter = pShooter;
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        pResult.getEntity().hurt(DamageSource.thrown(this, pResult.getEntity()), getAttackDamage(pResult));
    }

    @Override
    protected boolean tryPickup(@NotNull Player pPlayer) {
        return false;
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult pResult) {
        isStuckInBlock = true;
        super.onHitBlock(pResult);
        if (isGlassBlock(pResult) && canDestroyGlass()) {
            destroyBlock(pResult);
            return;
        }
        stuckInBlock(pResult);
    }

    protected float getAttackDamage(EntityHitResult pResult) {
        float attackDamage = ((SwordItem) this.weapon.getItem()).getDamage();
        attackDamage += EnchantmentHelper.getDamageBonus(this.weapon, ((LivingEntity) pResult.getEntity()).getMobType());
        return attackDamage;
    }

    protected boolean canDestroyGlass() {
        return true;
    }

    protected boolean isGlassBlock(BlockHitResult pResult) {
        return this.level.getBlockState(pResult.getBlockPos()).getMaterial() == Material.GLASS;
    }

    protected void destroyBlock(BlockHitResult pResult) {
        this.level.destroyBlock(pResult.getBlockPos(), true);
    }

    protected void stuckInBlock (HitResult pResult) {
        this.setNoGravity(true);
        Vec3 vec3 = pResult.getLocation().subtract(this.getX(), this.getY(), this.getZ());
        this.setDeltaMovement(vec3);
        Vec3 vec31 = vec3.normalize().scale(0.07F);
        this.setPos(this.getX() - vec31.x, this.getY() - vec31.y, this.getZ() - vec31.z);
    }

    @Override
    public void playerTouch(@NotNull Player pPlayer) {
        super.playerTouch(pPlayer);
        pickUp(pPlayer);
    }

    protected void pickUp(Player pPlayer) {
        if (!pPlayer.getBoundingBox().intersects(this.getBoundingBox())) {
            return;
        }

        this.customPlaySound(this.getDefaultPickupSound());

        if (!this.level.isClientSide) {
            pPlayer.getInventory().add(this.getPickupItem());
            this.discard();
        }
    }

    @Override
    public void shoot(double pX, double pY, double pZ, float pVelocity, float pInaccuracy) {
        super.shoot(pX, pY, pZ, pVelocity, pInaccuracy);
        this.customPlaySound(this.getDefaultThrowingSound());
    }

    @Override
    public @NotNull ItemStack getPickupItem() {
        return this.weapon;
    }

    protected abstract Item getDefaultItem();

    protected void customPlaySound(SoundEvent soundEvent) {
        if (soundEvent != null) {
            playSound(soundEvent, 1.0F, 1.0F);
        }
    }

    @Nullable
    protected SoundEvent getDefaultPickupSound() {
        return null;
    }

    @Nullable
    protected SoundEvent getDefaultThrowingSound() {
        return null;
    }

    @Override
    protected @NotNull SoundEvent getDefaultHitGroundSoundEvent() {
        return ModSounds.NULL.get();
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        if (pCompound.contains("Weapon", 10)) {
            this.weapon = ItemStack.of(pCompound.getCompound("Weapon"));
        }

        this.isStuckInBlock = pCompound.getBoolean("ObjectGotHit");
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.put("Weapon", this.weapon.save(new CompoundTag()));
        pCompound.putBoolean("ObjectGotHit", this.isStuckInBlock);
    }
}
