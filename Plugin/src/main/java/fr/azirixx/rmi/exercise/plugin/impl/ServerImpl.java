package fr.azirixx.rmi.exercise.plugin.impl;

import fr.azirixx.rmi.exercise.plugin.RMIExercise;
import fr.endmc.exercise.Player;
import fr.endmc.exercise.Server;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class ServerImpl implements Server {

    Map<UUID, PlayerImpl> players;
    Map<UUID, Player> remotePlayers;

    public ServerImpl() {
        this.players = new HashMap<>();
        this.remotePlayers = new HashMap<>();
    }

    private Player getRemotePlayer(UUID uuid) throws RemoteException {
        if(!this.players.containsKey(uuid)) {
            PlayerImpl player = new PlayerImpl(uuid);

            this.players.put(uuid, player);
            this.remotePlayers.put(uuid, (Player) UnicastRemoteObject.exportObject(player, 0));
        }
        return this.remotePlayers.get(uuid);
    }

    @Override
    public Player getPlayer(UUID uuid) throws RemoteException {
        return getRemotePlayer(uuid);
    }

    @Override
    public List<Player> getPlayers() throws RemoteException {
        return Bukkit.getOnlinePlayers()
                .stream()
                .map(player -> {
                    try {
                        return getRemotePlayer(player.getUniqueId());
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public int getMaxPlayers() throws RemoteException {
        return Bukkit.getMaxPlayers();
    }

    @Override
    public void setBlock(String worldName, int x, int y, int z, String materialName) throws RemoteException {
        Material material = Material.getMaterial(materialName);
        if(material == null) {
            throw new RemoteException("Material not found");
        }

        World world = Bukkit.getWorld(worldName);
        if(world == null) {
            throw new RemoteException("World not found");
        }

        Location location = new Location(world, x, y, z);
        Bukkit.getScheduler().runTask(RMIExercise.getInstance(), () -> location.getBlock().setType(material));
    }

    @Override
    public String getBlock(String worldName, int x, int y, int z) throws RemoteException {
        World world = Bukkit.getWorld(worldName);
        if(world == null) {
            throw new RemoteException("World not found");
        }

        Location location = new Location(world, x, y, z);
        return location.getBlock().getType().toString();
    }
}
