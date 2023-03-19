package net.somberfob.vikingmod.client.gui.InformationGUI;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3d;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.somberfob.vikingmod.VikingMod;
import net.somberfob.vikingmod.sounds.ModSounds;
import net.somberfob.vikingmod.util.RenderingHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;

import java.nio.Buffer;
import java.util.List;

import static net.somberfob.vikingmod.util.RenderingHelper.*;

public class InformationDisplayGui extends Screen {
    private static final ResourceLocation INFORMATION_DISPLAY = new ResourceLocation(VikingMod.MOD_ID, "textures/gui/information_display/information_display.png");
    private static final int TEXTURE_WIDTH = 128;
    private static final int TEXTURE_HEIGHT = 128;
    private static final int BORDER_SIZE = 5;

    private final int SPACE_BETWEEN_IMG = 20;
    private final float scale = 1.5f;

    private final ResourceLocation backgroundImg;
    private static final int BACKGROUND_IMG_HEIGHT = 72;
    private static final int BACKGROUND_IMG_WIDTH = 88;

    private final MutableComponent title;
    private int titleFontSize;

    MutableComponent description;
    private final int descriptionFontSize;

    MutableComponent buttonName = Component.literal("Close");
    private int buttonWidth;
    private int buttonHeight;
    private int buttonXPos;
    private int buttonYPos;

    public InformationDisplayGui(ResourceLocation backgroundIMG, MutableComponent title, MutableComponent description, int titleFontSize, int descriptionFontSize) {
        super(Component.literal("Information Display"));
        this.backgroundImg = backgroundIMG;
        this.title = title;
        this.description = description;
        this.titleFontSize = titleFontSize;
        this.descriptionFontSize = descriptionFontSize;
        if (Minecraft.getInstance().player != null) {
            Minecraft.getInstance().player.playSound(ModSounds.NOTIFY_OPEN.get());
        }
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {

        if (pMouseX >= this.buttonXPos && pMouseY >= this.buttonYPos && pMouseX <= buttonXPos + buttonWidth && pMouseY <= (buttonYPos + buttonHeight))
        {
            this.onClose();
            if (Minecraft.getInstance().player != null) Minecraft.getInstance().player.playSound(ModSounds.NOTIFY_CLOSE.get());
        };

        return super.mouseClicked(pMouseX, pMouseY, pButton);
    }

    @Override
    public void render(@NotNull PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        buttonXPos = Mth.ceil(TEXTURE_WIDTH  * scale) / 2 - this.titleFontSize - 1;
        buttonHeight = this.titleFontSize - Mth.ceil(RenderingHelper.ScaledFontSize(this.titleFontSize));
        int scaledFontSize = Mth.ceil(Minecraft.getInstance().font.width(this.buttonName) * RenderingHelper.ScaledFontSize(this.titleFontSize));
        buttonYPos = Mth.ceil((TEXTURE_HEIGHT * scale) - (this.titleFontSize * scale));
        buttonWidth = scaledFontSize;

        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1, 1, 1, 1);

        pPoseStack.pushPose();
        pPoseStack.scale(this.scale, this.scale, 1);
        this.renderTextures(pPoseStack);

        pPoseStack.popPose();

        this.renderText(pPoseStack);
    }

    private void renderTextures(PoseStack pPoseStack) {
        RenderSystem.setShaderTexture(0, INFORMATION_DISPLAY);
        GuiComponent.blit(pPoseStack, 0, 0,
                          TEXTURE_WIDTH, TEXTURE_HEIGHT,
                          0, 0,
                          TEXTURE_WIDTH, TEXTURE_HEIGHT,
                          TEXTURE_WIDTH, TEXTURE_HEIGHT);

        RenderSystem.setShaderTexture(0, backgroundImg);
        GuiComponent.blit(pPoseStack, SPACE_BETWEEN_IMG, SPACE_BETWEEN_IMG,
                          BACKGROUND_IMG_WIDTH, BACKGROUND_IMG_HEIGHT,
                          0, 0,
                          BACKGROUND_IMG_WIDTH, BACKGROUND_IMG_HEIGHT,
                          BACKGROUND_IMG_WIDTH, BACKGROUND_IMG_HEIGHT);

    }


    private void renderText(PoseStack pPoseStack) {
        RenderingHelper.renderDefaultFont(pPoseStack, title, SPACE_BETWEEN_IMG, (int) (SPACE_BETWEEN_IMG - this.titleFontSize / scale), this.titleFontSize, scale);
        this.renderDescription(pPoseStack, this.description, SPACE_BETWEEN_IMG, SPACE_BETWEEN_IMG + BACKGROUND_IMG_HEIGHT, descriptionFontSize);


        RenderingHelper.renderDefaultFont(pPoseStack, buttonName, (int) (TEXTURE_WIDTH / 2 - this.titleFontSize / scale), Mth.ceil(TEXTURE_HEIGHT - buttonHeight), this.titleFontSize, scale);
    }

    private void renderDescription(PoseStack poseStack, Component text, int xPos, int yPos, int fontSize) {
        List<FormattedCharSequence> listOfStrings = (Minecraft.getInstance().font.split(text, TEXTURE_WIDTH + BACKGROUND_IMG_WIDTH + fontSize));

        for (int i = 0; i < listOfStrings.size(); i++) {
            RenderingHelper.renderDefaultFont(poseStack, listOfStrings.get(i), xPos, (yPos + i * fontSize), fontSize, this.scale);
        }
    }
}

