package net.somberfob.vikingmod.item.custom.Thrown;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.somberfob.vikingmod.entities.ModEntityType;
import net.somberfob.vikingmod.item.ModItems;
import org.jetbrains.annotations.NotNull;

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
}
