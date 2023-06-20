package net.somberfob.vikingmod.util;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.font.FontManager;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.LivingEntity;
import org.apache.logging.log4j.core.pattern.TextRenderer;

public class RenderingHelper {
    private final static int DEFAULT_FONT_SIZE = 10;

    public static void renderDefaultFont(PoseStack poseStack, FormattedCharSequence text, int xPos, int yPos, int fontSize, float scale) {
        int defaultColor = 16777215;
        xPos *= scale;
        yPos *= scale;

        poseStack.pushPose();
        poseStack.translate(xPos,yPos, 0);
        poseStack.scale(0.1f, 0.1f, 1f);
        poseStack.scale(fontSize, fontSize, 1f);
        poseStack.translate(-(xPos), -(yPos), 0);
        GuiComponent.drawString(poseStack, Minecraft.getInstance().font, text, xPos, yPos, defaultColor);
        poseStack.popPose();
    }

    public static void renderDefaultFont(PoseStack poseStack, Component text, int xPos, int yPos, int fontSize) {
        renderDefaultFont(poseStack, text, xPos, yPos, fontSize, 1f);
    }

    public static void renderDefaultFont(PoseStack poseStack, Component text, int xPos, int yPos, int fontSize, float scale) {
        int defaultColor = 16777215;
        xPos *= scale;
        yPos *= scale;

        poseStack.pushPose();
        poseStack.translate(xPos,yPos, 0);
        poseStack.scale(0.1f, 0.1f, 1f);
        poseStack.scale(fontSize, fontSize, 1f);
        poseStack.translate(-(xPos), -(yPos), 0);
        GuiComponent.drawString(poseStack, Minecraft.getInstance().font, text, xPos, yPos, defaultColor);
        poseStack.popPose();
    }

    public static int getAbsFontOffset(int defaultFontSize, int newFontSize) {
        return Math.abs(defaultFontSize - newFontSize);
    }

    public static int getDefaultFontSize() {
        return DEFAULT_FONT_SIZE;
    }

    public static float ScaledFontSize(int fontSize) {
        return 0.1f * fontSize;
    }

    public static int rgbaToDecimal(int r, int g, int b, int a) {
        int decimal = (a << 24) | (r << 16) | (g << 8) | b;
        return decimal;
    }

    public static int rgbToDecimal(int r, int g, int b) {
        int a = 255;
        int decimal = (a << 24) | (r << 16) | (g << 8) | b;
        return decimal;
    }

    public static void renderEntityToGui(LivingEntity entity, PoseStack poseStack, float scale, double x, double y) {
        Minecraft minecraft = Minecraft.getInstance();
        EntityRenderDispatcher entityRenderDispatcher = minecraft.getEntityRenderDispatcher();
        MultiBufferSource.BufferSource multiBufferSource = minecraft.renderBuffers().bufferSource();

        poseStack.pushPose();
        poseStack.translate(x, y, 0);
        poseStack.scale(scale, scale, scale);
        poseStack.mulPose(Vector3f.ZP.rotation(3.15f));

        entityRenderDispatcher.render(entity, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, poseStack, multiBufferSource, 15728880);
        multiBufferSource.endBatch();
        entityRenderDispatcher.setRenderShadow(true);

        poseStack.popPose();
    }
}

