package fr.azirixx.rmi.exercise.plugin.impl;

import fr.azirixx.rmi.exercise.plugin.RMIExercise;
import fr.endmc.exercise.Player;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;

import java.rmi.RemoteException;
import java.util.UUID;

public class PlayerImpl implements Player {


    UUID uuid;
    public PlayerImpl(UUID uuid) {
        this.uuid = uuid;
    }

    public org.bukkit.entity.Player getBukkitPlayer() throws RemoteException{
        org.bukkit.entity.Player player = Bukkit.getPlayer(uuid);
        if(player == null) {
            throw new RemoteException("Player not found");
        }

        return player;
    }

    @Override
    public UUID getUniqueId() throws RemoteException {
        return uuid;
    }

    @Override
    public String getName() throws RemoteException {
        return getBukkitPlayer().getName();
    }

    @Override
    public void sendMessage(String s) throws RemoteException {
        getBukkitPlayer().sendMessage(s);
    }

    @Override
    public void teleport(String worldName, double x, double y, double z) throws RemoteException {
        World world = Bukkit.getWorld(worldName);
        if(world == null) {
            throw new RemoteException("World not found");
        }

        org.bukkit.entity.Player player = getBukkitPlayer();
        Bukkit.getScheduler().runTask(RMIExercise.getInstance(), () -> player.teleport(new Location(world, x, y, z)));
    }

    @Override
    public void spawnParticle(String particleName, String worldName, double x, double y, double z) throws RemoteException {
        try {
            Particle particle = Particle.valueOf(particleName);
            World world = Bukkit.getWorld(worldName);
            if (world == null) {
                throw new RemoteException("World not found");
            }
            org.bukkit.entity.Player player = getBukkitPlayer();
            Bukkit.getScheduler().runTask(RMIExercise.getInstance(), () -> player.spawnParticle(particle, new Location(world, x, y, z), 1));
        } catch (IllegalArgumentException e) {
            throw new RemoteException("Invalid particle");
        }

    }
}
