package fr.azirixx.rmi.exercise.client.command.impl;

import fr.azirixx.rmi.exercise.client.RMIExercise;
import fr.azirixx.rmi.exercise.client.command.Command;
import fr.endmc.exercise.Player;
import fr.endmc.exercise.Server;

import java.util.List;

public class ListCommand extends Command {


    public ListCommand() {
        super("list", "stats");
    }

    @Override
    public String getUsage() {
        return "";
    }

    @Override
    public String getDescription() {
        return "List players";
    }

    @Override
    public void onCommand(String label, String cmd, String[] args) throws Exception {
        Server server = RMIExercise.getRmiServer().getServer();
        List<Player> players = server.getPlayers();
        System.out.println("Stats: " + players.size() + "/" + server.getMaxPlayers());
        for (Player player : server.getPlayers()) {
            System.out.println("- " + player.getName());
        }
    }
}
