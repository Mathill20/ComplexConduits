package com.mathill.cc.game.model;

import net.minecraft.client.model.ModelBase;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import com.mathill.cc.game.block.ModBlocks;

public class ModelPipeRoller extends ModelBase {

    private IModelCustom modelTutBox;

    public ModelPipeRoller() {

        modelTutBox = AdvancedModelLoader.loadModel(ModBlocks.PIPE_ROLLER.tileModel);
    }

    public void render() {

        modelTutBox.renderAll();
    }
}
