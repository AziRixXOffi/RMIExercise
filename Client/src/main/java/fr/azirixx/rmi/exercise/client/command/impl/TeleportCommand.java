package fr.azirixx.rmi.exercise.client.command.impl;

import fr.azirixx.rmi.exercise.client.RMIExercise;
import fr.azirixx.rmi.exercise.client.command.Command;
import fr.endmc.exercise.Player;

import java.rmi.RemoteException;
import java.util.Optional;

public class TeleportCommand extends Command {


    public TeleportCommand() {
        super("teleport", "tp");
    }


    @Override
    public String getUsage() {
        return "<player> <world> <x> <y> <z>";
    }

    @Override
    public String getDescription() {
        return "Teleport a player";
    }

    @Override
    public void onCommand(String label, String cmd, String[] args) throws Exception {
        if(args.length > 4) {
            String name = args[0];
            Optional<Player> target = RMIExercise.getRmiServer().getServer().getPlayers().stream().filter(player -> {
                try {
                    return player.getName().equalsIgnoreCase(name);
                } catch (RemoteException ignored) {
                    return false;
                }
            }).findFirst();
            if(target.isPresent()) {
                Player player = target.get();

                String worldName = args[1];
                double x = Double.parseDouble(args[2]);
                double y = Double.parseDouble(args[3]);
                double z = Double.parseDouble(args[4]);

                player.teleport(worldName, x, y, z);

                System.out.println("Payer " + player.getName() + " has been teleported to " + worldName + " at " + x + ", " + y + ", " + z + ".");
                return;
            }
            System.err.println("Player not found.");
            return;
        }
        System.out.println("Usage: /" + getLabel() + " " + getUsage());
    }
}
