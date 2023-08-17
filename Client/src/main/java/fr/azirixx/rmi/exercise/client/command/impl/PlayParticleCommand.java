package fr.azirixx.rmi.exercise.client.command.impl;

import fr.azirixx.rmi.exercise.client.RMIExercise;
import fr.azirixx.rmi.exercise.client.command.Command;
import fr.endmc.exercise.Player;

import java.rmi.RemoteException;
import java.util.Optional;

public class PlayParticleCommand extends Command {


    public PlayParticleCommand() {
        super("playparticle","particle");
    }

    @Override
    public String getUsage() {
        return "<player> <particle> <world> <x> <y> <z>";
    }

    @Override
    public String getDescription() {
        return "Play a particle";
    }

    @Override
    public void onCommand(String label, String cmd, String[] args) throws Exception {
        if(args.length > 5) {
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

                String particle = args[1];
                String worldName = args[2];
                double x = Double.parseDouble(args[3]);
                double y = Double.parseDouble(args[4]);
                double z = Double.parseDouble(args[5]);

                player.spawnParticle(particle, worldName, x, y, z);

                System.out.println("Payer " + player.getName() + " has been teleported to " + worldName + " at " + x + ", " + y + ", " + z + ".");
                return;
            }
            System.out.println("Player not found.");
            return;
        }
        System.out.println("Usage: /" + getLabel() + " " + getUsage());
    }
}
