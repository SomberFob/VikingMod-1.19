package net.somberfob.vikingmod.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.protocol.Packet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.somberfob.vikingmod.VikingMod;
import net.somberfob.vikingmod.network.Messages;
import net.somberfob.vikingmod.network.packet.TeleportC2SPacket;
import net.somberfob.vikingmod.util.RenderingHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JoinGui extends Screen {
    private static final ResourceLocation TEXTURE = new ResourceLocation(VikingMod.MOD_ID, "textures/gui/joingui/joingui.png");
    private static final ResourceLocation HOVER_TEXTURE = new ResourceLocation(VikingMod.MOD_ID, "textures/gui/joingui/joingui_hover.png");

    private static final int TEXTURE_HEIGHT = 168;
    private static final int TEXTURE_WIDTH = 176;

    private int centerX;
    private int centerY;

    private int slotX1;
    private int slotCircleCenterX1;
    private int slotY;
    private int slotX2;
    private int slotCircleCenterX2;

    private List<Component> slot1Text = new ArrayList<>();
    private List<Component> slot2Text = new ArrayList<>();

    protected JoinGui() {
        super(Component.literal("title"));
    }

    @Override
    protected void init() {
        super.init();
        this.centerX = this.width / 2 - TEXTURE_WIDTH / 2;
        this.centerY = this.height / 2 - TEXTURE_HEIGHT / 2;

        this.slotX1 = centerX + 8;
        this.slotCircleCenterX1 = centerX + 43;
        this.slotY = centerY + 48;
        this.slotX2 = centerX + 98;
        this.slotCircleCenterX2 = centerX + 133;

        slot1Text.add(Component.literal("As Saxons, we defend our country with unwavering determination. Together, we rise against Viking invaders, reclaiming our lands.").withStyle(ChatFormatting.WHITE));
        slot1Text.add(Component.literal("United, we'll drive them out, back to where they came. Our courage knows no bounds; our ancestors' spirit fuels us. Prepare, for victory is near!").withStyle(ChatFormatting.WHITE));

        slot2Text.add(Component.literal("As Vikings, we sail with fierce ambition. Our goal is to conquer new lands and forge our destiny. No country shall withstand our might").withStyle(ChatFormatting.WHITE));
        slot2Text.add(Component.literal("Let them try to drive us out, but we will prevail. We are warriors bound by a legacy of strength. Prepare to witness the true power of the Viking horde. Victory is our destiny!").withStyle(ChatFormatting.WHITE));
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        if (pButton == 0) {
            if (slotCollision(pMouseX, pMouseY, slotX1, slotY, slotCircleCenterX1, centerY)) {
                Messages.sendToServer(new TeleportC2SPacket(Level.NETHER, 10000, 0, 128));
            } else if (slotCollision(pMouseX, pMouseY, slotX2, slotY, slotCircleCenterX2, centerY)) {
                Messages.sendToServer(new TeleportC2SPacket(Level.END, 10000, 0, 128));
            }

            this.close();
        }

        return super.mouseClicked(pMouseX, pMouseY, pButton);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    @Override
    public void render(@NotNull PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);

        ClientLevel level = Minecraft.getInstance().level;

        RenderSystem.setShaderTexture(0, TEXTURE);
        this.blit(pPoseStack, centerX, centerY, 0, 0, TEXTURE_WIDTH, TEXTURE_HEIGHT);

        if (slotCollision(pMouseX, pMouseY, slotX1, slotY, slotCircleCenterX1, centerY)) {
            renderHoveredSlot(pPoseStack, centerX, centerY, 0);
            this.renderTooltip(pPoseStack, slot1Text, java.util.Optional.empty(), pMouseX, pMouseY);
        } else if (slotCollision(pMouseX, pMouseY, slotX2, slotY, slotCircleCenterX2, centerY)) {
            renderHoveredSlot(pPoseStack, centerX + TEXTURE_WIDTH / 2, centerY, TEXTURE_WIDTH / 2);
            this.renderTooltip(pPoseStack, slot2Text, java.util.Optional.empty(), pMouseX, pMouseY);
        }


        renderText(pPoseStack, centerX, centerY + 28);

        if (level == null) return;

        RenderingHelper.renderEntityToGui(new Villager(EntityType.VILLAGER, level), pPoseStack, 40,
                centerX + 42, centerY + 142);

        RenderingHelper.renderEntityToGui(new Villager(EntityType.VILLAGER, level, VillagerType.DESERT), pPoseStack, 40,
                centerX + TEXTURE_WIDTH - 42, centerY + 142);
    }

    private void renderHoveredSlot(PoseStack PoseStack, int x, int y, int textureX) {
        RenderSystem.setShaderTexture(0, HOVER_TEXTURE);
        this.blit(PoseStack, x, y, textureX, 0, TEXTURE_WIDTH / 2, TEXTURE_HEIGHT);
    }

    private boolean slotCollision(double mouseX, double mouseY, int slotX, int slotY, int circleCenterX, int centerY) {
        int slotWidth = 71;
        int slotHeight = 107;
        double circleCenterY = centerY + 61.2;
        int radius = 36;

        boolean inRect = slotX <= mouseX && mouseX <= slotX + slotWidth && slotY <= mouseY && mouseY <= slotY + slotHeight;
        double distance = Math.sqrt(Math.pow(mouseX - circleCenterX, 2) + Math.pow(mouseY - circleCenterY, 2));

        return distance <= radius || inRect;
    }

    private void renderText(PoseStack poseStack, int centerX, int centerY) {
        int slotWidth = 71;
        int fontSize = 16;
        ChatFormatting color = ChatFormatting.DARK_GRAY;
        int center2Y = centerY - fontSize;

        RenderingHelper.renderDefaultFont(poseStack, Component.literal("SAXON").withStyle(color), centerX + slotWidth / 2 - fontSize, center2Y , fontSize);
        RenderingHelper.renderDefaultFont(poseStack, Component.literal("VIKINGS").withStyle(color), (centerX + TEXTURE_WIDTH) - slotWidth, center2Y, fontSize);
    }

    public static void open() {
        if (Minecraft.getInstance() == null) return;
        Minecraft.getInstance().setScreen(new JoinGui());
    }

    private void close() {
        if (Minecraft.getInstance().player != null) {
            Minecraft.getInstance().player.closeContainer();
        }
    }
}
