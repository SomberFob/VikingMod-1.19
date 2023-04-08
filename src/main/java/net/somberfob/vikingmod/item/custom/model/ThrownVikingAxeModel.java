package net.somberfob.vikingmod.item.custom.model;

import net.minecraft.client.model.ColorableAgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.somberfob.vikingmod.item.custom.thrown.ThrownVikingAxe;
import org.jetbrains.annotations.NotNull;

public class ThrownVikingAxeModel<T extends ThrownVikingAxe> extends ColorableAgeableListModel<T> {
    @Override
    protected @NotNull Iterable<ModelPart> headParts() {
        return null;
    }

    @Override
    protected @NotNull Iterable<ModelPart> bodyParts() {
        return null;
    }

    @Override
    public void setupAnim(@NotNull T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
    }
}
