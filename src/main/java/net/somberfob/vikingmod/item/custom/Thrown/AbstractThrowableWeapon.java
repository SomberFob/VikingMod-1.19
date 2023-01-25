package net.somberfob.vikingmod.item.custom.Thrown;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.somberfob.vikingmod.item.ModItems;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractThrowableWeapon extends AbstractArrow {
    protected ItemStack weapon = new ItemStack(this.getDefaultItem());
    protected boolean isStuckInBlock;

    public AbstractThrowableWeapon(EntityType<? extends AbstractThrowableWeapon> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public AbstractThrowableWeapon(EntityType<? extends AbstractThrowableWeapon> pEntityType, LivingEntity pShooter, Level pLevel, ItemStack weapon) {
        super(pEntityType, pShooter, pLevel);
        this.weapon = weapon;
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        pResult.getEntity().hurt(DamageSource.thrown(this, pResult.getEntity()), getAttackDamage(pResult));
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

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
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
        if (!this.level.isClientSide && pPlayer.getBoundingBox().intersects(this.getBoundingBox())) {
            pPlayer.getInventory().add(this.getPickupItem());
            this.discard();
        }
    }

    @Override
    public @NotNull ItemStack getPickupItem() {
        return this.weapon;
    }

    protected abstract Item getDefaultItem();

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
