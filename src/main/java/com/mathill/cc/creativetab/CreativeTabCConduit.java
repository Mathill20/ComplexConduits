package com.mathill.cc.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabCConduit extends CreativeTabs {

    public CreativeTabCConduit(int parameter, String stringParameter) {

        super(parameter, stringParameter);
    }

    @Override
    @SideOnly(Side.CLIENT)
    /**
     * the itemID for the item to be displayed on the tab
     */
    public int getTabIconItemIndex() {

        return Item.axeDiamond.itemID;
    }

}
