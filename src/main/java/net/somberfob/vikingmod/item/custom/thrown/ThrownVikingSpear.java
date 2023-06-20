package net.somberfob.vikingmod.item.custom.thrown;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.somberfob.vikingmod.entities.ModEntityType;
import net.somberfob.vikingmod.item.ModItems;
import org.jetbrains.annotations.NotNull;

public class ThrownVikingSpear extends AbstractThrowableWeapon{
    public ThrownVikingSpear(EntityType<? extends AbstractThrowableWeapon> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ThrownVikingSpear(LivingEntity pShooter, Level pLevel, ItemStack weapon) {
        super(ModEntityType.VIKING_SPEAR.get(), pShooter, pLevel, weapon);
    }

    @Override
    protected @NotNull Item getDefaultItem() {
        return ModItems.VIKING_SPEAR.get();
    }
}
