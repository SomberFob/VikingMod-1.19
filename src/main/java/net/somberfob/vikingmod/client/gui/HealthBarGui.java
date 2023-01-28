package net.somberfob.vikingmod.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.Tags;
import net.somberfob.vikingmod.VikingMod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HealthBarGui {
    private static final Minecraft CLIENT = Minecraft.getInstance();
    private static final Player PLAYER = CLIENT.player;
    private static final ResourceLocation HEALTH = new ResourceLocation("textures/gui/health.png");
    private static final int HEALTBAR_WIDTH = 126;
    private static final int HEALTBAR_HEIGHT = 14;
    private static final int X_POS = -65;
    private static final int Y_POS = -65;

    public static void render(RenderLivingEvent<? extends LivingEntity, ? extends EntityModel<?>> event) {
        LivingEntity pEntity = event.getEntity();
        Quaternion cameraOrientation = CLIENT.getEntityRenderDispatcher().cameraOrientation();

        renderHealthbar(event.getPoseStack(), pEntity, cameraOrientation, pEntity.getBbHeight());
    }

    public static void renderHealthbar(PoseStack poseStack, LivingEntity entity, Quaternion cameraOrientation, float cameraHeight) {
        boolean isFriendly = entity.getClassification(false).isFriendly();

        RenderSystem.disableDepthTest();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, new ResourceLocation(VikingMod.MOD_ID, HEALTH.getPath()));

        if (!canRender(entity)) {
            return;
        }

        poseStack.pushPose();
        poseStack.translate(0.0D, cameraHeight, 0.0D);
        poseStack.mulPose(cameraOrientation);
        poseStack.scale(-0.008F, -0.01F, 0.01F);

        if (isFriendly) {
            renderFriendlyEntityHealthbar(poseStack, entity);
        } else {
            renderHostileEntityHealthbar(poseStack, entity);
        }


        poseStack.popPose();
    }

    public static boolean canRender(LivingEntity entity) {
        return entitiyIsInDistance(entity) &&
                !playerIsBlind() &&
                entity != PLAYER;
    }

    public static boolean entitiyIsInDistance(LivingEntity entity) {
        double viewDistance = CLIENT.options.getEffectiveRenderDistance();
        return PLAYER != null && PLAYER.getBoundingBox().inflate(viewDistance).intersects(entity.getBoundingBox());
    }

    public static boolean playerIsBlind() {
        return PLAYER != null && PLAYER.getEffect(MobEffects.BLINDNESS) != null;
    }

    public static void renderFriendlyEntityHealthbar(PoseStack poseStack, LivingEntity entity) {
        GuiComponent.blit(poseStack, X_POS, Y_POS, 0, 0, HEALTBAR_WIDTH, HEALTBAR_HEIGHT,
                          126, 56);

        GuiComponent.blit(poseStack,
                          X_POS, Y_POS,
                          getScaledProgress((int) entity.getHealth(), (int) entity.getMaxHealth(), HEALTBAR_WIDTH), HEALTBAR_HEIGHT,
                          0, HEALTBAR_HEIGHT + 0,
                          HEALTBAR_WIDTH, HEALTBAR_HEIGHT,
                          126, 56);
    }

    public static void renderHostileEntityHealthbar(PoseStack poseStack, LivingEntity entity) {
        GuiComponent.blit(poseStack, X_POS, Y_POS, 0, HEALTBAR_HEIGHT * 2, HEALTBAR_WIDTH, HEALTBAR_HEIGHT,
                          126, 56);

        GuiComponent.blit(poseStack,
                          X_POS, Y_POS,
                          getScaledProgress((int) entity.getHealth(), (int) entity.getMaxHealth(), HEALTBAR_WIDTH), HEALTBAR_HEIGHT,
                          0, HEALTBAR_HEIGHT * 3,
                          HEALTBAR_WIDTH, HEALTBAR_HEIGHT,
                          126, 56);
    }

    public static int getScaledProgress(int health, int maxHealth, int textureSize) {
        return maxHealth != 0 && health != 0 ? health * textureSize / maxHealth : 0;
    }
}
