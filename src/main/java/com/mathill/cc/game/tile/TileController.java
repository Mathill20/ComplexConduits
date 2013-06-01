package com.mathill.cc.game.tile;

import net.minecraft.block.Block;

import com.mathill.cc.lib.Orientation;
import com.mathill.cc.lib.Point3d;

public class TileController extends TileCConduit {

    private Point3d[] blockPoints;

    public boolean revalidate() {

        int[] blockIds = new int[] { Block.furnaceBurning.blockID, Block.furnaceIdle.blockID };
        return getLargestFaceFromPoint(xCoord, yCoord, zCoord, Orientation.XY, 1, 1, blockIds) != null;
    }

    public void convertDummies() {

        // worldObj.setBlock(xCoord, y, z, ModBlocks.MULTI_FURNACE_DUMMY.blockId);
    }

    public int getType() {

        return -1;
    }

    public void invalidateMultiBlock() {

        // int metadata = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
        // metadata &= BlockMultiFurnace.MASK_DIR;
        // worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, metadata, 2);
        //
        // furnaceBurnTime = 0;
        // currentItemBurnTime = 0;
        // furnaceCookTime = 0;
        //
        // revertDummies();
    }

    /**
     * Finds the largest 2d set of blocks that are continguous.<br>
     * 
     * If startx = 0, starty = 0, startz = 0, orientation=XY, maxRadius = 3 and minRadius = 0, <br>
     * a return of {Point(0,0,0), Point(1,1,0)} would be valid. <br>
     * <br>
     * Explanation of minRadius.<br>
     * The minRadius defines the minimum number of blocks away from the start point that must exist in order for the face to be valid. If startx = 0, starty = 0, startz = 0, orientation = XY, maxRadius = 3, and minRadius = 0, <br>
     * a return of {Point(0,0), Point(1,1)} would be invalid, but <br>
     * a return of {Point(-1,-1), Point(1,1)} would be valid. <br>
     * <br>
     * If startx = 0, starty = 0, startz = 0, orientation = XY, maxRadius = 3, and minRadius = 3, <br>
     * the only valid return would be {Point(-3,-3),Point(3,3)}
     * 
     * @param startx
     * @param starty
     * @param startz
     * @param orientation
     * @param maxRadius
     *            max distance a block can be away.
     * @param minRadius
     *            the minimum radius the face can be.
     * @return two points defining the available face or null if no valid face exists.
     */
    protected Point3d[] getLargestFaceFromPoint(final int startx,
                                                final int starty,
                                                final int startz,
                                                final Orientation orientation,
                                                final int maxRadius,
                                                final int minRadius,
                                                int... validBlocks) {

        if (minRadius > maxRadius) return null;

        int radius = 0;
        boolean isValid = false;

        while (radius <= minRadius && (isValid = checkRadius(startx, starty, startz, radius, orientation, validBlocks))) {

            radius++;
        }

        if (!isValid) return null;

        while (radius < maxRadius && (isValid = checkRadius(startx, starty, startz, radius, orientation, validBlocks))) {

            radius++;
        }

        if (!isValid) {

            radius--;
        }

        Point3d[] points = new Point3d[2];
        points[1] = new Point3d(startx - radius, starty - radius, startz);
        points[2] = new Point3d(startx + radius, starty - radius, startz);

        return points;
    }

    /**
     * Only checks the the outer ring from the specified center block.
     * 
     * @param maxRadius
     * @return
     */
    protected boolean checkRadius(final int startx,
                                  final int starty,
                                  final int startz,
                                  final int radius,
                                  final Orientation orientation,
                                  int... validBlocks) {

        int blockId;
        boolean isBlock = true;

        switch (orientation) {

            case XY: {

                int x = startx;
                int y;
                int z = startz;

                for (y = starty - radius; y <= starty + radius && isBlock; y++) {

                    blockId = worldObj.getBlockId(x - radius, y, z);
                    isBlock |= isValidBlock(blockId, validBlocks);

                    blockId = worldObj.getBlockId(x + radius, y, z);
                    isBlock |= isValidBlock(blockId, validBlocks);
                }

                for (x = startx - radius + 1; x <= startx + radius - 1 && isBlock; x++) {

                    blockId = worldObj.getBlockId(x, y - radius, z);
                    isBlock |= isValidBlock(blockId, validBlocks);

                    blockId = worldObj.getBlockId(x, y + radius, z);
                    isBlock |= isValidBlock(blockId, validBlocks);
                }

                return isBlock;
            }
            case XZ: {

                int x = startx;
                int y = starty;
                int z;

                for (z = startz - radius; z <= startz + radius && isBlock; z++) {

                    blockId = worldObj.getBlockId(x - radius, y, z);
                    isBlock |= isValidBlock(blockId, validBlocks);

                    blockId = worldObj.getBlockId(x + radius, y, z);
                    isBlock |= isValidBlock(blockId, validBlocks);
                }

                for (x = startx - radius + 1; x <= startx + radius - 1; x++) {

                    blockId = worldObj.getBlockId(x, y, z - radius);
                    isBlock |= isValidBlock(blockId, validBlocks);

                    blockId = worldObj.getBlockId(x, y, z + radius);
                    isBlock |= isValidBlock(blockId, validBlocks);
                }

                return isBlock;
            }
            case YZ: {

                int x = startx;
                int y = starty;
                int z = startz;

                for (z = startz - radius; z <= startz + radius && isBlock; z++) {

                    blockId = worldObj.getBlockId(x, y, z);
                    isBlock |= isValidBlock(blockId, validBlocks);

                    blockId = worldObj.getBlockId(x, y, z);
                    isBlock |= isValidBlock(blockId, validBlocks);
                }

                for (y = starty - radius + 1; y <= starty + radius - 1; y++) {

                    blockId = worldObj.getBlockId(x, y, z);
                    isBlock |= isValidBlock(blockId, validBlocks);

                    blockId = worldObj.getBlockId(x, y, z);
                    isBlock |= isValidBlock(blockId, validBlocks);
                }

                return isBlock;
            }
            default:

                return false;
        }
    }

    protected boolean isValidBlock(int blockId,
                                   int... blocks) {

        boolean isValid = true;

        int i = 0;

        while (i < blocks.length && (isValid = (blockId == blocks[i++]))) {}

        return isValid;
    }
}
