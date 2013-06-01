package com.mathill.cc.game.block;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import com.mathill.cc.ComplexConduits;
import com.mathill.cc.game.tile.TileController;
import com.mathill.cc.lib.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockController extends BlockCConduits {

    public static final int stoneMeta   = 0;
    public static final int ironMeta    = 1;
    public static final int steelMeta   = 2;
    public static final int goldMeta    = 3;
    public static final int diamondMeta = 4;

    @SideOnly(Side.CLIENT)
    private Icon[]          icons;

    protected BlockController(int id) {

        super(id, Material.wood);

        setUnlocalizedName("com.mathill.cc.game.block.BlockController");
        setCreativeTab(ComplexConduits.tabsCC);
    }

    public boolean onBlockActivated(World world,
                                    int x,
                                    int y,
                                    int z,
                                    EntityPlayer player,
                                    int par6,
                                    float par7) {

        if (player.isSneaking()) return false;

        TileController controller = (TileController) world.getBlockTileEntity(x, y, z);

        if (controller != null) {

            if (!controller.revalidate()) {

                controller.convertDummies();

                if (world.isRemote) {

                    player.sendChatToPlayer("Multi-Block Created!");
                }
            } else {

                player.openGui(ComplexConduits.instance, controller.getType(), world, x, y, z);
            }
        }

        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileController();
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister register) {

        icons = new Icon[5];

        for (int i = 0; i < icons.length; i++) {

            icons[i] = register.registerIcon(Reference.MOD_NAME + ":controller/" + getUnlocalizedName2() + i);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int par1,
                        int par2) {

        return icons[par2];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int par1,
                             CreativeTabs tabs,
                             List list) {

        list.add(new ItemStack(par1, 1, stoneMeta));
        list.add(new ItemStack(par1, 1, ironMeta));
        list.add(new ItemStack(par1, 1, steelMeta));
        list.add(new ItemStack(par1, 1, goldMeta));
        list.add(new ItemStack(par1, 1, diamondMeta));
    }
}
