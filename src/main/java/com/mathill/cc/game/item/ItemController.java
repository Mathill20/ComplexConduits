package com.mathill.cc.game.item;

import com.mathill.cc.game.block.BlockController;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemController extends ItemBlock {

    public ItemController(int par1) {

        super(par1);
        setHasSubtypes(true);
    }

    public String getUnlocalizedName(ItemStack stack) {

        String name = "";

        switch (stack.getItemDamage()) {

            case BlockController.diamondMeta:

                name = "diamond";
                break;
            case BlockController.goldMeta:

                name = "gold";
                break;
            case BlockController.ironMeta:

                name = "iron";
                break;
            case BlockController.steelMeta:

                name = "steel";
                break;
            case BlockController.stoneMeta:

                name = "stone";
                break;

            default:

                name = "Unknown";
                break;
        }

        return getUnlocalizedName() + "." + name;
    }

    public int getMetadata(int par1) {

        return par1;
    }
}
