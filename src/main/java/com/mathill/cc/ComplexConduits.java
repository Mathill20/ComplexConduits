package com.mathill.cc;

import java.io.File;

import net.minecraft.creativetab.CreativeTabs;

import com.mathill.cc.configuration.ConfigurationHandler;
import com.mathill.cc.creativetab.CreativeTabCConduit;
import com.mathill.cc.handlers.LocalizationHandler;
import com.mathill.cc.lib.Reference;
import com.mathill.cc.proxy.CommonProxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.FingerprintWarning;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLFingerprintViolationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = Reference.MOD_ID,
     name = Reference.MOD_NAME,
     version = Reference.VERSION_NUMBER,
     dependencies = Reference.DEPENDENCIES,
     certificateFingerprint = Reference.FINGERPRINT)
@NetworkMod(channels = { Reference.CHANNEL_NAME },
            clientSideRequired = true,
            serverSideRequired = false,
            packetHandler = com.mathill.cc.network.PacketHandler.class)
public class ComplexConduits {

    @Instance(Reference.MOD_ID)
    public static ComplexConduits instance;

    public static CreativeTabs    tabsCC = new CreativeTabCConduit(CreativeTabs.getNextID(), Reference.MOD_ID);

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy     proxy;

    @FingerprintWarning
    public void invalidFingerprint(FMLFingerprintViolationEvent event) {

    }

    @ServerStarting
    public void serverStarting(FMLServerStartingEvent event) {

    }

    @PreInit
    public void preInit(FMLPreInitializationEvent event) {

        ConfigurationHandler.init(getModConfig(event));

        proxy.registerSoundHandler();
        proxy.initBlocks();
        proxy.initItems();
        proxy.initRecipes();
        LocalizationHandler.registerLocalizations();
    }

    @Init
    public void load(FMLInitializationEvent event) {

        NetworkRegistry.instance().registerGuiHandler(instance, proxy);
        proxy.initTileEntities();
    }

    // Do work that needs to interact with other mods in this method.
    @PostInit
    public void modsLoaded(FMLPostInitializationEvent event) {

    }

    private File getModConfig(FMLPreInitializationEvent event) {

        String path = event.getModConfigurationDirectory().getAbsolutePath();
        return new File(path + File.separator + Reference.CHANNEL_NAME + File.separator + Reference.MOD_ID + ".cfg");
    }
}
