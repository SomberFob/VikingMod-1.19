package net.somberfob.vikingmod.item.custom.thrown;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.somberfob.vikingmod.entities.ModEntityType;
import net.somberfob.vikingmod.item.ModItems;
import org.jetbrains.annotations.NotNull;

public class ThrownOilJar extends ThrowableItemProjectile {
    private float explosionRadius = 2;

    public ThrownOilJar(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ThrownOilJar(LivingEntity pShooter, Level pLevel) {
        super(ModEntityType.OIL_JAR.get(), pShooter, pLevel);
    }

    @Override
    protected void onHit(@NotNull HitResult pResult) {
        super.onHit(pResult);
        this.level.explode((Entity) null, this.getX(), this.getY(), this.getZ(), explosionRadius, true, Explosion.BlockInteraction.NONE);
        this.discard();
    }

    @Override
    protected @NotNull Item getDefaultItem() {
        return ModItems.OIL_JAR.get();
    }

    public void setExplosionRadius(float explosionRadius) {
        this.explosionRadius = explosionRadius;
    }
}
