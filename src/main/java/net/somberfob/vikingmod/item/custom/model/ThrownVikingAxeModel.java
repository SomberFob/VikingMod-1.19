package net.somberfob.vikingmod.item.custom.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.ColorableAgeableListModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Wolf;
import net.somberfob.vikingmod.item.custom.thrown.ThrownVikingAxe;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

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
