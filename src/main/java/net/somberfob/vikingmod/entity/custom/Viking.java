package net.somberfob.vikingmod.entity.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

// Replace
public class Viking extends Pillager {
    public Viking(EntityType<? extends Pillager> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        //this.goalSelector.addGoal(0, new FloatGoal(this));
        //this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, Raider.class)).setAlertOthers());
        //this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        //this.goalSelector.addGoal(3, new Raider.HoldGroundAttackGoal(this, 10.0F));
        //this.goalSelector.addGoal(4, new RangedCrossbowAttackGoal<>(this, 1.0D, 8.0F));
        //this.goalSelector.addGoal(5, new RandomStrollGoal(this, 0.6D));
        //this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 15.0F, 1.0F));
        //this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Mob.class, 15.0F));
    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource randomSource, DifficultyInstance difficultyInstance) {
        super.populateDefaultEquipmentSlots(randomSource, difficultyInstance);
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_33282_, DifficultyInstance p_33283_, MobSpawnType p_33284_, @Nullable SpawnGroupData p_33285_, @Nullable CompoundTag p_33286_) {
        return super.finalizeSpawn(p_33282_, p_33283_, p_33284_, p_33285_, p_33286_);
    }

    public static AttributeSupplier.Builder getVikingAttributes() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.7F);
    }
}
