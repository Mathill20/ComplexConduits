package com.mathill.cc.proxy;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.mathill.cc.game.block.BlockFurnaceDummy;
import com.mathill.cc.game.block.BlockPipeExtruder;
import com.mathill.cc.game.block.BlockPipeRoller;
import com.mathill.cc.game.block.ModBlocks;
import com.mathill.cc.game.item.ItemController;
import com.mathill.cc.game.tile.TileController;
import com.mathill.cc.game.tile.TileFurnaceDummy;
import com.mathill.cc.game.tile.TilePipeExtruder;
import com.mathill.cc.game.tile.TilePipeRoller;
import com.mathill.cc.lib.Reference;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID,
                                      EntityPlayer player,
                                      World world,
                                      int x,
                                      int y,
                                      int z) {

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID,
                                      EntityPlayer player,
                                      World world,
                                      int x,
                                      int y,
                                      int z) {

        return null;
    }

    public boolean isRenderWorld(World world) {

        return world.isRemote;
    }

    public void initBlocks() {

        ModBlocks.PIPE_EXTRUDER.block = new BlockPipeExtruder(ModBlocks.PIPE_EXTRUDER.blockId);
        GameRegistry.registerBlock(ModBlocks.PIPE_EXTRUDER.block, ModBlocks.PIPE_EXTRUDER.blockName);

        ModBlocks.PIPE_ROLLER.block = new BlockPipeRoller(ModBlocks.PIPE_ROLLER.blockId);
        GameRegistry.registerBlock(ModBlocks.PIPE_ROLLER.block, ModBlocks.PIPE_ROLLER.blockName);

        ModBlocks.CONTROLLER.block = new BlockPipeRoller(ModBlocks.CONTROLLER.blockId);
        GameRegistry.registerBlock(ModBlocks.CONTROLLER.block, ItemController.class, Reference.MOD_ID + ModBlocks.CONTROLLER.blockName);

        ModBlocks.MULTI_FURNACE_DUMMY.block = new BlockFurnaceDummy(ModBlocks.MULTI_FURNACE_DUMMY.blockId);
        GameRegistry.registerBlock(ModBlocks.MULTI_FURNACE_DUMMY.block, ModBlocks.MULTI_FURNACE_DUMMY.blockName);
    }

    public void initItems() {

    }

    public void initTileEntities() {

        GameRegistry.registerTileEntity(TilePipeExtruder.class, ModBlocks.PIPE_EXTRUDER.tileName);
        GameRegistry.registerTileEntity(TilePipeRoller.class, ModBlocks.PIPE_ROLLER.tileName);
        GameRegistry.registerTileEntity(TileController.class, ModBlocks.CONTROLLER.tileName);
        GameRegistry.registerTileEntity(TileFurnaceDummy.class, ModBlocks.MULTI_FURNACE_DUMMY.tileName);
    }

    public void initRenderingAndTextures() {

    }

    public void initRecipes() {

        ItemStack cobble = new ItemStack(Block.cobblestone);
        ItemStack furnace = new ItemStack(Block.furnaceIdle);
        ItemStack sand = new ItemStack(Block.sand);

        GameRegistry.addRecipe(new ItemStack(ModBlocks.PIPE_EXTRUDER.block),
                               new Object[] { "cfc", "sfs", "cfc", 'c', cobble, 'f', furnace, 's', sand });

    }

    public void registerSoundHandler() {

    }
}
