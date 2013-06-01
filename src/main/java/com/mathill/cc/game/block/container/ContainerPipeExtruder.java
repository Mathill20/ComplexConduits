/**
 * Copyright (c) SpaceToad, 2011
 * http://www.mod-buildcraft.com
 *
 * BuildCraft is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://www.mod-buildcraft.com/MMPL-1.0.txt
 */

package com.mathill.cc.game.block.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

import com.mathill.cc.game.tile.TilePipeExtruder;

public class ContainerPipeExtruder extends ContainerCConduits {

    IInventory       playerIInventory;
    TilePipeExtruder table;

    boolean          networkSynchronized = false;

    public ContainerPipeExtruder(IInventory playerInventory, TilePipeExtruder table) {

        super(table.getSizeInventory());
        this.playerIInventory = playerInventory;

        addSlotToContainer(new Slot(table, 0, 20, 20));
        addSlotToContainer(new Slot(table, 1, 38, 20));
        addSlotToContainer(new Slot(table, 2, 20, 38));
        addSlotToContainer(new Slot(table, 3, 38, 38));
        addSlotToContainer(new Slot(table, 4, 90, 29));

        for (int row = 0; row < 2; row++) {

            for (int column = 0; column < 9; column++) {

                addSlotToContainer(new Slot(table, 5 + (row * 9) + column, 8 + column * 18, 74 + row * 18));
            }
        }

        for (int l = 0; l < 3; l++) {

            for (int k1 = 0; k1 < 9; k1++) {

                addSlotToContainer(new Slot(playerInventory, k1 + l * 9 + 9, 8 + k1 * 18, 123 + l * 18));
            }

        }

        for (int i1 = 0; i1 < 9; i1++) {

            addSlotToContainer(new Slot(playerInventory, i1, 8 + i1 * 18, 181));
        }

        this.table = table;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {

        return table.isUseableByPlayer(entityplayer);
    }

    @Override
    public void updateProgressBar(int i,
                                  int j) {

        // table.getGUINetworkData(i, j);
    }

    @Override
    public void detectAndSendChanges() {

        super.detectAndSendChanges();

        for (int i = 0; i < crafters.size(); i++) {

            // table.sendGUINetworkData(this, (ICrafting) crafters.get(i));
        }
    }
}
