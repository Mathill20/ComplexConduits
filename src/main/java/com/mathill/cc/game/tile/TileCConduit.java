package com.mathill.cc.game.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TileCConduit extends TileEntity {

    protected ForgeDirection orientation;
    protected byte           state;
    protected String         customName;

    public TileCConduit() {

        orientation = ForgeDirection.SOUTH;
        state = 0;
        customName = "";
    }

    public ForgeDirection getOrientation() {

        return orientation;
    }

    public void setOrientation(ForgeDirection orientation) {

        this.orientation = orientation;
    }

    public void setOrientation(int orientation) {

        this.orientation = ForgeDirection.getOrientation(orientation);
    }

    public short getState() {

        return state;
    }

    public void setState(byte state) {

        this.state = state;
    }

    public boolean hasCustomName() {

        return customName != null && customName.length() > 0;
    }

    public String getCustomName() {

        return customName;
    }

    public void setCustomName(String customName) {

        this.customName = customName;
    }

    public boolean isUseableByPlayer(EntityPlayer player) {

        return true;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {

        super.readFromNBT(nbtTagCompound);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {

        super.writeToNBT(nbtTagCompound);
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("TileEE Data - xCoord: %d, yCoord: %d, zCoord: %d, customName: '%s', orientation: %s, state: %d\n",
                                           xCoord,
                                           yCoord,
                                           zCoord,
                                           customName,
                                           orientation,
                                           state));

        return stringBuilder.toString();
    }

}
