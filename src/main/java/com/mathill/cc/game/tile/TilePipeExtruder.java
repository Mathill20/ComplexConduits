package com.mathill.cc.game.tile;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import com.mathill.cc.game.block.ModBlocks;
import com.mathill.cc.lib.Sounds;

public class TilePipeExtruder extends TileCConduit implements IInventory {

    public static final int INVENTORY_SIZE = 33;

    private ItemStack[]     inventory;
    public int              numUsingPlayers;
    public float            lidAngle;
    public float            prevLidAngle;
    private int             ticksSinceSync;

    public TilePipeExtruder() {

        super();
        inventory = new ItemStack[INVENTORY_SIZE];
    }

    @Override
    public int getSizeInventory() {

        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int i) {

        return inventory[i];
    }

    @Override
    public ItemStack decrStackSize(int slot,
                                   int amount) {

        ItemStack stack = getStackInSlot(slot);

        if (stack != null) {

            if (stack.stackSize <= amount) {

                setInventorySlotContents(slot, null);
            } else {

                stack = stack.splitStack(amount);
            }
        }

        return stack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {

        ItemStack stack = this.inventory[slot];
        this.inventory[slot] = null;
        return stack;
    }

    @Override
    public void setInventorySlotContents(int slot,
                                         ItemStack stack) {

        if (stack != null && stack.stackSize > getInventoryStackLimit()) {

            stack.stackSize = getInventoryStackLimit();
        }

        this.inventory[slot] = stack;

        onInventoryChanged();
    }

    @Override
    public String getInvName() {

        return "container." + ModBlocks.PIPE_EXTRUDER.tileName;
    }

    @Override
    public int getInventoryStackLimit() {

        return 64;
    }

    /**
     * Called when a client event is received with the event number and argument, see World.sendClientEvent
     */
    @Override
    public boolean receiveClientEvent(int eventID,
                                      int numUsingPlayers) {

        if (eventID == 1) {
            this.numUsingPlayers = numUsingPlayers;
            return true;
        } else return super.receiveClientEvent(eventID, numUsingPlayers);
    }

    @Override
    public void openChest() {

        ++numUsingPlayers;
        worldObj.addBlockEvent(xCoord, yCoord, zCoord, ModBlocks.PIPE_EXTRUDER.blockId, 1, numUsingPlayers);
    }

    @Override
    public void closeChest() {

        --numUsingPlayers;
        worldObj.addBlockEvent(xCoord, yCoord, zCoord, ModBlocks.PIPE_EXTRUDER.blockId, 1, numUsingPlayers);
    }

    @Override
    public void updateEntity() {

        super.updateEntity();

        if (++ticksSinceSync % 20 * 4 == 0) {
            worldObj.addBlockEvent(xCoord, yCoord, zCoord, Block.enderChest.blockID, 1, numUsingPlayers);
        }

        prevLidAngle = lidAngle;
        float angleIncrement = 0.1F;
        double adjustedXCoord, adjustedZCoord;

        if (numUsingPlayers > 0 && lidAngle == 0.0F) {
            adjustedXCoord = xCoord + 0.5D;
            adjustedZCoord = zCoord + 0.5D;
            worldObj.playSoundEffect(adjustedXCoord, yCoord + 0.5D, adjustedZCoord, Sounds.CHEST_OPEN, 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
        }

        if (numUsingPlayers == 0 && lidAngle > 0.0F || numUsingPlayers > 0 && lidAngle < 1.0F) {
            float var8 = lidAngle;

            if (numUsingPlayers > 0) {
                lidAngle += angleIncrement;
            } else {
                lidAngle -= angleIncrement;
            }

            if (lidAngle > 1.0F) {
                lidAngle = 1.0F;
            }

            if (lidAngle < 0.5F && var8 >= 0.5F) {
                adjustedXCoord = xCoord + 0.5D;
                adjustedZCoord = zCoord + 0.5D;
                worldObj.playSoundEffect(adjustedXCoord,
                                         yCoord + 0.5D,
                                         adjustedZCoord,
                                         Sounds.CHEST_CLOSE,
                                         0.5F,
                                         worldObj.rand.nextFloat() * 0.1F + 0.9F);
            }

            if (lidAngle < 0.0F) {
                lidAngle = 0.0F;
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {

        super.readFromNBT(nbtTagCompound);

        // Read in the ItemStacks in the inventory from NBT
        NBTTagList tagList = nbtTagCompound.getTagList("Items");
        inventory = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < tagList.tagCount(); ++i) {

            NBTTagCompound tagCompound = (NBTTagCompound) tagList.tagAt(i);
            byte slot = tagCompound.getByte("Slot");

            if (slot >= 0 && slot < inventory.length) {

                inventory[slot] = ItemStack.loadItemStackFromNBT(tagCompound);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {

        super.writeToNBT(nbtTagCompound);

        // Write the ItemStacks in the inventory to NBT
        NBTTagList tagList = new NBTTagList();

        for (int currentIndex = 0; currentIndex < inventory.length; ++currentIndex) {

            if (inventory[currentIndex] != null) {

                NBTTagCompound tagCompound = new NBTTagCompound();
                tagCompound.setByte("Slot", (byte) currentIndex);
                inventory[currentIndex].writeToNBT(tagCompound);
                tagList.appendTag(tagCompound);
            }
        }

        nbtTagCompound.setTag("Items", tagList);
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {

        return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this;
    }

    @Override
    public boolean isInvNameLocalized() {

        return false;
    }

    @Override
    public boolean isStackValidForSlot(int i,
                                       ItemStack itemstack) {

        // TODO: Add actual logic
        return true;
    }
}
