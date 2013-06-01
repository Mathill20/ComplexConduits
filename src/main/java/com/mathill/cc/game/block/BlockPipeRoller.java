package com.mathill.cc.game.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.mathill.cc.ComplexConduits;
import com.mathill.cc.game.tile.TilePipeRoller;
import com.mathill.cc.lib.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPipeRoller extends BlockCConduits {

    public BlockPipeRoller(int id) {

        super(id, Material.wood);
        setUnlocalizedName(ModBlocks.PIPE_ROLLER.blockName);
        setCreativeTab(ComplexConduits.tabsCC);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {

        blockIcon = iconRegister.registerIcon(Reference.MOD_ID + ":empty");
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TilePipeRoller();
    }

    @Override
    public TileEntity createTileEntity(World world,
                                       int metadata) {

        return new TilePipeRoller();
    }

    @Override
    public boolean hasTileEntity() {

        return true;
    }

    @Override
    public boolean renderAsNormalBlock() {

        return false;
    }

    public boolean isOpaqueCube() {

        return false;
    }

    @Override
    public int getRenderType() {

        return ModBlocks.PIPE_ROLLER.tileRenderId;
    }
}
