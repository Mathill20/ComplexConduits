package com.mathill.cc.game.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import com.mathill.cc.ComplexConduits;
import com.mathill.cc.game.tile.TilePipeExtruder;
import com.mathill.cc.lib.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPipeExtruder extends BlockWrenchable {

    protected Icon[] icons = new Icon[8];

    public BlockPipeExtruder(int id) {

        super(id, Material.wood);
        setUnlocalizedName(ModBlocks.PIPE_EXTRUDER.blockName);
        setCreativeTab(ComplexConduits.tabsCC);
        // this.setBlockBounds(0.05F, 0.0F, 0.05F, 0.875F, 0.8F, 0.875F);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {

        blockIcon = iconRegister.registerIcon(Reference.MOD_ID + ":Empty");
    }

    @Override
    public TileEntity createNewTileEntity(World var1) {

        return new TilePipeExtruder();
    }

    @Override
    public TileEntity createTileEntity(World world,
                                       int metadata) {

        return new TilePipeExtruder();
    }

    public boolean hasTileEntity() {

        return true;
    }

    @Override
    public boolean renderAsNormalBlock() {

        return false;
    }

    @Override
    public int getRenderType() {

        return ModBlocks.PIPE_EXTRUDER.tileRenderId;
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

        boolean superSuccess = super.onBlockActivated(world, x, y, z, player, par6, par7, par8, par9);

        if (!superSuccess) {

            if (player.isSneaking()) return false;

            if (ComplexConduits.proxy.isRenderWorld(world)) {

                player.openGui(ComplexConduits.instance, ModBlocks.PIPE_EXTRUDER.guiId, world, x, y, z);
            }
        }

        return true;
    }
}
