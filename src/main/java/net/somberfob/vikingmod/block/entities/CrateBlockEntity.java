package net.somberfob.vikingmod.block.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.somberfob.vikingmod.block.custom.CrateBlock;
import net.somberfob.vikingmod.screen.crate.CrateMenu;

import java.util.stream.IntStream;

public class CrateBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {
    public static final int CONTAINER_SIZE = 9;
    private final int[] slots = IntStream.range(0, CONTAINER_SIZE).toArray();
    private NonNullList<ItemStack> itemStacks = NonNullList.withSize(CONTAINER_SIZE, ItemStack.EMPTY);

    public CrateBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.CRATE.get(), pPos, pBlockState);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState,
                            CrateBlockEntity entity) {
    }

    @Override
    public void onLoad() {
        super.onLoad();
    }

    public void load(CompoundTag pTag) {
        this.loadFromTag(pTag);
    }

    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        if (!this.trySaveLootTable(pTag)) {
            ContainerHelper.saveAllItems(pTag, this.itemStacks, false);
        }
    }

    public void loadFromTag(CompoundTag pTag) {
        this.itemStacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(pTag) && pTag.contains("Items", 9)) {
            ContainerHelper.loadAllItems(pTag, this.itemStacks);
        }
    }

    @Override
    public boolean canTakeItemThroughFace(int pIndex, ItemStack pStack, Direction pDirection) {
        return false;
    }

    @Override
    public int[] getSlotsForFace(Direction pSide) {
        return slots;
    }

    public int getContainerSize() {
        return this.itemStacks.size();
    }

    protected Component getDefaultName() {
        return Component.literal("Crate");
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
    }

    protected NonNullList<ItemStack> getItems() {
        return this.itemStacks;
    }

    protected void setItems(NonNullList<ItemStack> pItems) {
        this.itemStacks = pItems;
    }

    @Override
    public boolean canPlaceItemThroughFace(int pIndex, ItemStack pItemStack, @org.jetbrains.annotations.Nullable Direction pDirection) {
        return (Block.byItem(pItemStack.getItem()) instanceof CrateBlock) && pItemStack.getItem().canFitInsideContainerItems();
    }

    protected AbstractContainerMenu createMenu(int pId, Inventory pInventory) {
        return new CrateMenu(pId, pInventory, this);
    }
}