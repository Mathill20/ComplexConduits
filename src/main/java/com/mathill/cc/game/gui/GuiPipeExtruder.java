/**
 * Copyright (c) SpaceToad, 2011
 * http://www.mod-buildcraft.com
 *
 * BuildCraft is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://www.mod-buildcraft.com/MMPL-1.0.txt
 */

package com.mathill.cc.game.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;

import org.lwjgl.opengl.GL11;

import com.mathill.cc.game.block.ModBlocks;
import com.mathill.cc.game.block.container.ContainerPipeExtruder;
import com.mathill.cc.game.tile.TilePipeExtruder;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiPipeExtruder extends GuiContainer {

    TilePipeExtruder pipeExtruder;

    public GuiPipeExtruder(IInventory playerInventory, TilePipeExtruder pipeExtruder) {

        super(new ContainerPipeExtruder(playerInventory, pipeExtruder));

        this.pipeExtruder = pipeExtruder;
        xSize = 175;
        ySize = 207;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    @Override
    protected void drawGuiContainerForegroundLayer(int par1,
                                                   int par2) {

        fontRenderer.drawString("MyString", 28, 6, 4210752);
        fontRenderer.drawString("MyString2", 8, ySize - 96 + 2, 4210752);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    @Override
    protected void drawGuiContainerBackgroundLayer(float par1,
                                                   int par2,
                                                   int par3) {

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(ModBlocks.PIPE_EXTRUDER.guiTexture);
        int var5 = (width - xSize) / 2;
        int var6 = (height - ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, xSize, ySize);
    }
}
