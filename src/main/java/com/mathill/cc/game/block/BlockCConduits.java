package com.mathill.cc.game.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import com.mathill.cc.ComplexConduits;
import com.mathill.cc.game.tile.TileCConduit;
import com.mathill.cc.lib.CommonMethods;
import com.mathill.cc.lib.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class BlockCConduits extends BlockContainer {

    protected BlockCConduits(int id, Material material) {

        super(id, material);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {

        blockIcon = iconRegister.registerIcon(Reference.MOD_ID + ":Empty");
    }

    /**
     * Sets the direction of the block when placed
     */
    @Override
    public void onBlockPlacedBy(World world,
                                int x,
                                int y,
                                int z,
                                EntityLiving entityLiving,
                                ItemStack itemStack) {

        int direction = 0;
        int facing = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

        if (facing == 0) {
            direction = ForgeDirection.NORTH.ordinal();
        } else if (facing == 1) {
            direction = ForgeDirection.EAST.ordinal();
        } else if (facing == 2) {
            direction = ForgeDirection.SOUTH.ordinal();
        } else if (facing == 3) {
            direction = ForgeDirection.WEST.ordinal();
        }

        world.setBlockMetadataWithNotify(x, y, z, direction, 3);

        if (itemStack.hasDisplayName()) {

            ((TileCConduit) world.getBlockTileEntity(x, y, z)).setCustomName(itemStack.getDisplayName());
        }

        ((TileCConduit) world.getBlockTileEntity(x, y, z)).setOrientation(direction);
    }

    public static void preDestroyBlock(World world,
                                       int i,
                                       int j,
                                       int k) {

        TileEntity tile = world.getBlockTileEntity(i, j, k);

        if (tile instanceof IInventory && !ComplexConduits.proxy.isRenderWorld(world)) {

            IInventory inventory = (IInventory) tile;

            for (int l = 0; l < inventory.getSizeInventory(); ++l) {

                ItemStack items = inventory.getStackInSlot(l);

                if (items != null && items.stackSize > 0) {

                    CommonMethods.dropItems(world, inventory.getStackInSlot(l).copy(), i, j, k);
                }
            }
        }
    }
}
