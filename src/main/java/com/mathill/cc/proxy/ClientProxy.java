package com.mathill.cc.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

import com.mathill.cc.client.SoundHandler;
import com.mathill.cc.game.block.ModBlocks;
import com.mathill.cc.game.block.container.ContainerPipeExtruder;
import com.mathill.cc.game.gui.GuiPipeExtruder;
import com.mathill.cc.game.item.render.ItemPipeRollerRenderer;
import com.mathill.cc.game.tile.TilePipeExtruder;
import com.mathill.cc.game.tile.TilePipeRoller;
import com.mathill.cc.game.tile.render.TileEntityPipeRollerRenderer;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

    @Override
    public Object getServerGuiElement(int id,
                                      EntityPlayer player,
                                      World world,
                                      int x,
                                      int y,
                                      int z) {

        if (!world.blockExists(x, y, z)) return null;

        TileEntity tile = world.getBlockTileEntity(x, y, z);

        if (id == ModBlocks.PIPE_EXTRUDER.guiId && tile instanceof TilePipeExtruder) {

            return new ContainerPipeExtruder(player.inventory, (TilePipeExtruder) tile);
        } else {

            return null;
        }
    }

    @Override
    public Object getClientGuiElement(int id,
                                      EntityPlayer player,
                                      World world,
                                      int x,
                                      int y,
                                      int z) {

        if (!world.blockExists(x, y, z)) return null;

        TileEntity tile = world.getBlockTileEntity(x, y, z);

        if (id == ModBlocks.PIPE_EXTRUDER.guiId && tile instanceof TilePipeExtruder) {

            return new GuiPipeExtruder(player.inventory, (TilePipeExtruder) tile);
        } else {

            return null;
        }
    }

    @Override
    public void initTileEntities() {

        super.initTileEntities();

        ClientRegistry.bindTileEntitySpecialRenderer(TilePipeRoller.class, new TileEntityPipeRollerRenderer());
        MinecraftForgeClient.registerItemRenderer(ModBlocks.PIPE_ROLLER.blockId, new ItemPipeRollerRenderer());
    }

    @Override
    public void initRenderingAndTextures() {

        ModBlocks.PIPE_EXTRUDER.tileRenderId = RenderingRegistry.getNextAvailableRenderId();
        ModBlocks.PIPE_ROLLER.tileRenderId = RenderingRegistry.getNextAvailableRenderId();
    }

    @Override
    public void registerSoundHandler() {

        MinecraftForge.EVENT_BUS.register(new SoundHandler());
    }
}
