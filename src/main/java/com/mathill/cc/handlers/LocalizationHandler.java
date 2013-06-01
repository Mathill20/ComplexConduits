package com.mathill.cc.handlers;

import net.minecraft.item.ItemStack;

import com.mathill.cc.game.block.BlockController;
import com.mathill.cc.game.block.ModBlocks;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class LocalizationHandler {

    public static void registerLocalizations() {

        LanguageRegistry.addName(ModBlocks.PIPE_ROLLER.block, "Pipe Roller");
        LanguageRegistry.addName(ModBlocks.PIPE_EXTRUDER.block, "Pipe Extruder");
        LanguageRegistry.addName(new ItemStack(ModBlocks.CONTROLLER.block, 1, BlockController.stoneMeta), "Stone Controller");
        LanguageRegistry.addName(new ItemStack(ModBlocks.CONTROLLER.block, 1, BlockController.ironMeta), "Iron Controller");
        LanguageRegistry.addName(new ItemStack(ModBlocks.CONTROLLER.block, 1, BlockController.steelMeta), "Steel Controller");
        LanguageRegistry.addName(new ItemStack(ModBlocks.CONTROLLER.block, 1, BlockController.goldMeta), "Gold Controller");
        LanguageRegistry.addName(new ItemStack(ModBlocks.CONTROLLER.block, 1, BlockController.diamondMeta), "Diamond Controller");
    }
}
