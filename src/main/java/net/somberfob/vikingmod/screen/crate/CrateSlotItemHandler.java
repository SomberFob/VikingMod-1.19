package net.somberfob.vikingmod.screen.crate;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.somberfob.vikingmod.block.ModBlocks;
import org.jetbrains.annotations.NotNull;

public class CrateSlotItemHandler extends SlotItemHandler {
    public CrateSlotItemHandler(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        if (stack.getItem().equals(ModBlocks.CRATE.get().asItem())) {
            return false;
        }

        return super.mayPlace(stack);
    }


}
