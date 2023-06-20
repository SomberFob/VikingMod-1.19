package net.somberfob.vikingmod.block.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import net.somberfob.vikingmod.block.ModBlocks;
import net.somberfob.vikingmod.block.entities.CrateBlockEntity;
import net.somberfob.vikingmod.block.entities.ModBlockEntities;

import javax.annotation.Nullable;
import java.util.List;

public class CrateBlock extends BaseEntityBlock {
    public static final EnumProperty<Direction> FACING = DirectionalBlock.FACING;
    public static final ResourceLocation CONTENTS = new ResourceLocation("contents");
    private static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 16, 16);


    public CrateBlock(Properties properties) {
        super(properties);
    }

    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new CrateBlockEntity(pPos, pState);
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState,
                                                                  BlockEntityType<T> blockEntityType) {
        return createTickerHelper(blockEntityType, ModBlockEntities.CRATE.get(),
                                  CrateBlockEntity::tick);
    }

    public List<ItemStack> getDrops(BlockState pState, LootContext.Builder pBuilder) {
        BlockEntity blockentity = pBuilder.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
        if (blockentity instanceof CrateBlockEntity crateBlockEntity) {
            pBuilder = pBuilder.withDynamicDrop(CONTENTS, (p_56218_, p_56219_) -> {
                for (int i = 0; i < crateBlockEntity.getContainerSize(); ++i) {
                    p_56219_.accept(crateBlockEntity.getItem(i));
                }
            });
        }

        return super.getDrops(pState, pBuilder);
    }

    public void playerWillDestroy(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
        BlockEntity blockentity = pLevel.getBlockEntity(pPos);
        if (blockentity instanceof CrateBlockEntity CrateBlockEntity) {

            if (!pLevel.isClientSide && pPlayer.isCreative() && !CrateBlockEntity.isEmpty()) {
                ItemStack itemStack = new ItemStack(ModBlocks.CRATE.get());
                blockentity.saveToItem(itemStack);
                ItemEntity itementity = new ItemEntity(pLevel, (double) pPos.getX() + 0.5D, (double) pPos.getY() + 0.5D, (double) pPos.getZ() + 0.5D, itemStack);
                itementity.setDefaultPickUpDelay();
                pLevel.addFreshEntity(itementity);
            }
        }

        super.playerWillDestroy(pLevel, pPos, pState, pPlayer);
    }

    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, LivingEntity pPlacer, ItemStack pStack) {
        if (pStack.hasCustomHoverName()) {
            BlockEntity blockentity = pLevel.getBlockEntity(pPos);
            if (blockentity instanceof CrateBlockEntity) {
                ((CrateBlockEntity) blockentity).setCustomName(pStack.getHoverName());
            }
        }

    }

    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
        CompoundTag compoundtag = BlockItem.getBlockEntityData(pStack);
        if (compoundtag != null) {
            if (compoundtag.contains("Items", 9)) {
                NonNullList<ItemStack> itemStackNonNullList = NonNullList.withSize(CrateBlockEntity.CONTAINER_SIZE, ItemStack.EMPTY);
                ContainerHelper.loadAllItems(compoundtag, itemStackNonNullList);

                int itemStackAmount = 9;
                int i = 0;
                for (ItemStack itemstack : itemStackNonNullList) {
                    if (!itemstack.isEmpty()) {
                        if (i <= itemStackAmount) {
                            ++i;
                            MutableComponent mutablecomponent = itemstack.getHoverName().copy();
                            mutablecomponent.append(" x").append(String.valueOf(itemstack.getCount())).withStyle(ChatFormatting.GRAY);
                            pTooltip.add(mutablecomponent);
                        }
                    }
                }
            }
        }

    }

    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);

            if (entity instanceof CrateBlockEntity) {
                NetworkHooks.openScreen(((ServerPlayer) pPlayer), (CrateBlockEntity) entity, pPos);
            }
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState blockState, Rotation rotation) {
        return blockState.setValue(FACING, rotation.rotate(blockState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState blockState, Mirror pMirror) {
        return blockState.rotate(pMirror.getRotation(blockState.getValue(FACING)));
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    public boolean hasAnalogOutputSignal(BlockState pState) {
        return true;
    }

    public int getAnalogOutputSignal(BlockState pBlockState, Level pLevel, BlockPos pPos) {
        return AbstractContainerMenu.getRedstoneSignalFromContainer((Container) pLevel.getBlockEntity(pPos));
    }

    public PushReaction getPistonPushReaction(BlockState pState) {
        return PushReaction.DESTROY;
    }
}