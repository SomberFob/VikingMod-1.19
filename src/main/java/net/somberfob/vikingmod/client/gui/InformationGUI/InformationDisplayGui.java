package net.somberfob.vikingmod.client.gui.InformationGUI;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.Criterion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.FormattedCharSequence;
import net.somberfob.vikingmod.VikingMod;
import net.somberfob.vikingmod.sounds.ModSounds;
import net.somberfob.vikingmod.util.RenderingHelper;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static net.somberfob.vikingmod.util.RenderingHelper.renderDefaultFont;

public class InformationDisplayGui extends Screen {
    int titleFontSize = 12;
    private static final ResourceLocation INFORMATION_DISPLAY = new ResourceLocation(VikingMod.MOD_ID, "textures/gui/information_display/information_display.png");
    private static final int TEXTURE_WIDTH = 128;
    private static final int TEXTURE_HEIGHT = 128;

    private ResourceLocation backgroundImg;
    private static final int BACKGROUND_IMG_HEIGHT = 72;
    private static final int BACKGROUND_IMG_WIDTH = 88;

    private final float scale = 0.9f;

    MutableComponent description;
    private int descriptionFontSize = 6;
    private static final int Description = 12;
    private static final int descriptionYPos = 12;

    MutableComponent buttonName = Component.literal("Close");
    private final int buttonWidth = (int)(40 * scale);
    private final int buttonHeight = (int)(10 * scale);
    private final int buttonXPos = (int)(44 * scale);
    private final int buttonYPos = (int)(111 * scale);

    private static final int DEFAULT_FONT_SIZE = 10;
    private static List<InformationComponent> components = new ArrayList<>();


    private MutableComponent title;


    public InformationDisplayGui(ResourceLocation backgroundIMG, MutableComponent title, MutableComponent description, int titleFontSize, int descriptionFontSize) {
        super(Component.literal("Information Display"));
        this.backgroundImg = backgroundIMG;
        this.title = title;
        this.description = description;
        this.titleFontSize = titleFontSize;
        this.descriptionFontSize = descriptionFontSize;
        Minecraft.getInstance().player.playSound(ModSounds.NOTIFY_OPEN.get());
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        if (pMouseX >= this.buttonXPos && pMouseY >= this.buttonYPos && pMouseX <= buttonXPos + buttonWidth && pMouseY <= (buttonYPos + buttonHeight))
        {
            this.onClose();
            Minecraft.getInstance().player.playSound(ModSounds.NOTIFY_CLOSE.get());
        };

        return super.mouseClicked(pMouseX, pMouseY, pButton);
    }

    @Override
    public void render(@NotNull PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
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
        GuiComponent.blit(pPoseStack, 20, 20,
                          BACKGROUND_IMG_WIDTH, BACKGROUND_IMG_HEIGHT,
                          0, 0,
                          BACKGROUND_IMG_WIDTH, BACKGROUND_IMG_HEIGHT,
                          BACKGROUND_IMG_WIDTH, BACKGROUND_IMG_HEIGHT);
    }

    private void renderText(PoseStack pPoseStack) {
        RenderingHelper.renderDefaultFont(pPoseStack, title, 20 + this.titleFontSize, 20, this.titleFontSize);

        this.renderDescription(pPoseStack, this.description,
                               20 + descriptionFontSize, (int)(20 + BACKGROUND_IMG_HEIGHT * scale) + descriptionFontSize,
                               descriptionFontSize);


        RenderingHelper.renderDefaultFont(pPoseStack, buttonName, ((buttonXPos + buttonWidth / 2) - (this.titleFontSize + RenderingHelper.getAbsFontOffset(RenderingHelper.getDefaultFontSize(), this.titleFontSize))), buttonYPos + buttonHeight / 2, this.titleFontSize);
    }

    private void renderDescription(PoseStack poseStack, Component text, int xPos, int yPos, int fontSize) {
        List<FormattedCharSequence> listOfStrings = (Minecraft.getInstance().font.split(text, TEXTURE_WIDTH + BACKGROUND_IMG_WIDTH + fontSize));

        for (int i = 0; i < listOfStrings.size(); i++) {
            RenderingHelper.renderDefaultFont(poseStack, listOfStrings.get(i), xPos, (yPos + i * fontSize), fontSize);
        }
    }
}

