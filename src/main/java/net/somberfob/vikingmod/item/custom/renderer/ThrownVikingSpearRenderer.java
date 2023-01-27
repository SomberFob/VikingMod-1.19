package net.somberfob.vikingmod.item.custom.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.somberfob.vikingmod.VikingMod;
import net.somberfob.vikingmod.item.custom.thrown.ThrownVikingSpear;
import org.jetbrains.annotations.NotNull;

public class ThrownVikingSpearRenderer extends EntityRenderer<ThrownVikingSpear> {
    private static final ResourceLocation VIKING_SPEAR = new ResourceLocation(VikingMod.MOD_ID, "textures/item/viking_spear.png");
    private final ItemRenderer itemRenderer;
    
    public ThrownVikingSpearRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.itemRenderer = pContext.getItemRenderer();
    }

    @Override
    public void render(ThrownVikingSpear pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, @NotNull MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();
        pPoseStack.mulPose(Vector3f.YP.rotationDegrees(Mth.lerp(pPartialTick, pEntity.yRotO, pEntity.getYRot()) - 0.0F));
        pPoseStack.mulPose(Vector3f.ZP.rotationDegrees(Mth.lerp(pPartialTick, pEntity.xRotO, pEntity.getXRot()) + 90.0F));

        this.itemRenderer.renderStatic(pEntity.getPickupItem(), ItemTransforms.TransformType.GROUND, pPackedLight, OverlayTexture.NO_OVERLAY, pPoseStack, pBuffer, pEntity.getId());
        pPoseStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull ThrownVikingSpear pEntity) {
        return VIKING_SPEAR;
    }
}
