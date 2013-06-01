package com.mathill.cc.game.tile.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import com.mathill.cc.game.block.ModBlocks;
import com.mathill.cc.game.model.ModelPipeRoller;
import com.mathill.cc.game.tile.TilePipeRoller;

import cpw.mods.fml.client.FMLClientHandler;

public class TileEntityPipeRollerRenderer extends TileEntitySpecialRenderer {

    private ModelPipeRoller modelPipeRoller = new ModelPipeRoller();

    @Override
    public void renderTileEntityAt(TileEntity tileEntity,
                                   double x,
                                   double y,
                                   double z,
                                   float f) {

        if (tileEntity instanceof TilePipeRoller) {

            GL11.glPushMatrix();

            GL11.glTranslatef((float) x + 0.5F, (float) y + 0.4F, (float) z - 0.55f);

            GL11.glPushMatrix();

            GL11.glRotatef(90f, 90f, 0f, 0f);
            GL11.glPushMatrix();
            GL11.glScalef(0f, 0f, 1.5f);
            GL11.glPopMatrix();

            FMLClientHandler.instance().getClient().renderEngine.bindTexture(ModBlocks.PIPE_ROLLER.tileTexture);

            modelPipeRoller.render();
            GL11.glPopMatrix();

            GL11.glPopMatrix();
        }
    }
}
