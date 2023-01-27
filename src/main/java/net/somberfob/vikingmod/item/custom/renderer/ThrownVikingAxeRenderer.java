package net.somberfob.vikingmod.item.custom.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.model.TridentModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.somberfob.vikingmod.VikingMod;
import net.somberfob.vikingmod.item.custom.thrown.ThrownVikingAxe;
import org.jetbrains.annotations.NotNull;

public class ThrownVikingAxeRenderer extends EntityRenderer<ThrownVikingAxe> {
    private final ResourceLocation VIKING_AXE = new ResourceLocation(VikingMod.MOD_ID, "textures/item/viking_axe_texture.png");
    private final ItemRenderer itemRenderer;

    public ThrownVikingAxeRenderer(EntityRendererProvider.Context pContext, float pScale, boolean pFullBright) {
        super(pContext);
        this.itemRenderer = pContext.getItemRenderer();
    }

    public ThrownVikingAxeRenderer(EntityRendererProvider.Context pContext) {
        this(pContext, 1.0F, false);
    }

    @Override
    public void render(ThrownVikingAxe pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, @NotNull MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();

        pPoseStack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
        pPoseStack.mulPose(Vector3f.ZP.rotationDegrees(270.0F));
        pPoseStack.mulPose(Vector3f.YP.rotationDegrees(pEntity.rotationYP));

        this.itemRenderer.renderStatic(pEntity.getPickupItem(), ItemTransforms.TransformType.GROUND, pPackedLight, OverlayTexture.NO_OVERLAY, pPoseStack, pBuffer, pEntity.getId());
        pPoseStack.popPose();

        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull ThrownVikingAxe pEntity) {
        return VIKING_AXE;
    }
}
