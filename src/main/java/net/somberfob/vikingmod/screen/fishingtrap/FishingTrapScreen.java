package net.somberfob.vikingmod.screen.fishingtrap;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.somberfob.vikingmod.VikingMod;

public class FishingTrapScreen extends AbstractContainerScreen<FishingTrapMenu> {
    public static final ResourceLocation TEXTURE =
            new ResourceLocation(VikingMod.MOD_ID, "textures/gui/fishing_trap_gui.png");

    public FishingTrapScreen(FishingTrapMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTick, int MouseX, int MouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(poseStack, x, y, 0, 0, imageWidth, imageHeight);

        renderProgressArrow(poseStack, x, y);
    }

    private void renderProgressArrow(PoseStack poseStack, int x, int y) {
        if (this.menu.isCrafting()) {
            blit(poseStack, x + 83, y + 28, 176, 0, 8, menu.getScaledProgress());
        }
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float delta) {
        renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, delta);
        renderTooltip(poseStack, mouseX, mouseY);
    }
}
