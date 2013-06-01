package com.mathill.cc.game.item.render;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import com.mathill.cc.game.model.ModelPipeRoller;

public class ItemPipeRollerRenderer implements IItemRenderer {

    private ModelPipeRoller modelPipeRoller;

    public ItemPipeRollerRenderer() {

        this.modelPipeRoller = new ModelPipeRoller();
    }

    @Override
    public boolean handleRenderType(ItemStack item,
                                    ItemRenderType type) {

        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type,
                                         ItemStack item,
                                         ItemRendererHelper helper) {

        return true;
    }

    @Override
    public void renderItem(ItemRenderType type,
                           ItemStack item,
                           Object... data) {

        switch (type) {

            case ENTITY: {

                renderPipeRoller(0f, 0f, 0f, 0.5f);
                return;
            }

            case EQUIPPED: {

                renderPipeRoller(0f, 1f, 1f, 0.5f);
                return;
            }

            case INVENTORY: {

                renderPipeRoller(0f, 0f, 0f, 0.5f);
                return;
            }
            default:
                return;
        }
    }

    private void renderPipeRoller(float x,
                                  float y,
                                  float z,
                                  float scale) {

        GL11.glPushMatrix();

        // Disable Lighting Calculations
        GL11.glDisable(GL11.GL_LIGHTING);

        GL11.glTranslatef(x, y, z);
        GL11.glScalef(scale, scale, scale);
        GL11.glRotatef(180f, 0f, 180f, 0f);

        // FMLClientHandler.instance().getClient().renderEngine.bindTexture("/mods/cconduits/textures/blocks/empty.png");

        modelPipeRoller.render();

        // Re-enable Lighting Calculations
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}
