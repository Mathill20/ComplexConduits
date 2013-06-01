package com.mathill.cc.game.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import buildcraft.api.tools.IToolWrench;

public abstract class BlockWrenchable extends BlockCConduits {

    protected BlockWrenchable(int id, Material material) {

        super(id, material);
    }

    @Override
    public boolean onBlockActivated(World world,
                                    int x,
                                    int y,
                                    int z,
                                    EntityPlayer player,
                                    int par6,
                                    float par7,
                                    float par8,
                                    float par9) {

        Item equipped = player.getCurrentEquippedItem() != null ? player.getCurrentEquippedItem().getItem() : null;

        if (equipped instanceof IToolWrench && ((IToolWrench) equipped).canWrench(player, x, y, z)) {

            if (player.isSneaking()) {

                this.breakBlock(world, x, y, z, 0, 0);
                return true;
            } else {

                int meta = world.getBlockMetadata(x, y, z);

                switch (ForgeDirection.values()[meta]) {
                    case WEST:
                        world.setBlockMetadataWithNotify(x, y, z, ForgeDirection.SOUTH.ordinal(), 0);
                        break;
                    case EAST:
                        world.setBlockMetadataWithNotify(x, y, z, ForgeDirection.NORTH.ordinal(), 0);
                        break;
                    case NORTH:
                        world.setBlockMetadataWithNotify(x, y, z, ForgeDirection.WEST.ordinal(), 0);
                        break;
                    case SOUTH:
                    default:
                        world.setBlockMetadataWithNotify(x, y, z, ForgeDirection.EAST.ordinal(), 0);
                        break;
                }

                world.markBlockForUpdate(x, y, z);
                ((IToolWrench) equipped).wrenchUsed(player, x, y, z);
                return true;
            }
        } else {

            return false;
        }
    }
}
