package com.mathill.cc.configuration;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;

import com.mathill.cc.lib.Reference;

import cpw.mods.fml.common.FMLLog;

public class ConfigurationHandler {

    public static Configuration configuration;

    public static void init(File configFile) {

        configuration = new Configuration(configFile);

        try {

            configuration.load();

        } catch (Exception e) {

            FMLLog.log(Level.SEVERE, e, Reference.MOD_NAME + " has had a problem loading its configuration");
        } finally {

            configuration.save();
        }
    }

    public static void set(String categoryName,
                           String propertyName,
                           String newValue) {

        configuration.load();

        if (configuration.getCategoryNames().contains(categoryName)) {

            if (configuration.getCategory(categoryName).containsKey(propertyName)) {

                configuration.getCategory(categoryName).get(propertyName).set(newValue);
            }
        }

        configuration.save();
    }
}
