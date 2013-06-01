package com.mathill.cc.lib;

import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.packet.Packet41EntityEffect;
import net.minecraft.network.packet.Packet9Respawn;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.MathHelper;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import cpw.mods.fml.common.registry.GameRegistry;

public class TeleportPlayer {

    public static void setPlayerDimension(EntityPlayerMP player,
                                          int dimension) {

        ServerConfigurationManager scm = player.mcServer.getConfigurationManager();
        int j = player.dimension;
        MinecraftServer mcServer = scm.getServerInstance();
        WorldServer worldserver = mcServer.worldServerForDimension(player.dimension);
        player.dimension = dimension;
        WorldServer worldserver1 = mcServer.worldServerForDimension(player.dimension);
        player.playerNetServerHandler.sendPacketToPlayer(new Packet9Respawn(player.dimension, (byte) player.worldObj.difficultySetting, worldserver1
                .getWorldInfo().getTerrainType(), worldserver1.getHeight(), player.theItemInWorldManager.getGameType()));
        worldserver.removePlayerEntityDangerously(player);
        player.isDead = false;
        transferPlayerToWorld(player, j, worldserver, worldserver1);
        scm.func_72375_a(player, worldserver);
        player.playerNetServerHandler.setPlayerLocation(player.posX, player.posY, player.posZ, player.rotationYaw, player.rotationPitch);
        player.theItemInWorldManager.setWorld(worldserver1);
        scm.updateTimeAndWeatherForPlayer(player, worldserver1);
        scm.syncPlayerInventory(player);
        Iterator iterator = player.getActivePotionEffects().iterator();

        while (iterator.hasNext()) {
            PotionEffect potioneffect = (PotionEffect) iterator.next();
            player.playerNetServerHandler.sendPacketToPlayer(new Packet41EntityEffect(player.entityId, potioneffect));
        }

        GameRegistry.onPlayerChangedDimension(player);
    }

    private static void transferPlayerToWorld(EntityPlayerMP player,
                                              int fromDimension,
                                              WorldServer serverFrom,
                                              WorldServer serverTo) {

        WorldProvider pOld = serverFrom.provider;
        WorldProvider pNew = serverTo.provider;

        double moveFactor = pOld.getMovementFactor() / pNew.getMovementFactor();
        double d0 = player.posX * moveFactor, d1 = player.posZ * moveFactor;
        float f = player.rotationYaw;
        serverFrom.theProfiler.startSection("moving");
        serverFrom.theProfiler.startSection("placing");
        d0 = (double) MathHelper.clamp_int((int) d0, -29999872, 29999872);
        d1 = (double) MathHelper.clamp_int((int) d1, -29999872, 29999872);

        if (player.isEntityAlive()) {
            serverTo.spawnEntityInWorld(player);
            player.setLocationAndAngles(d0, player.posY, d1, player.rotationYaw, player.rotationPitch);
            serverTo.updateEntityWithOptionalForce(player, false);
        }

        serverFrom.theProfiler.endSection();

        player.setWorld(serverTo);

    }

}
