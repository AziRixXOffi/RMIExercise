package fr.azirixx.rmi.exercise.client.command.impl;

import fr.azirixx.rmi.exercise.client.RMIExercise;
import fr.azirixx.rmi.exercise.client.command.Command;
import fr.endmc.exercise.Player;

import java.rmi.RemoteException;
import java.util.Optional;

public class MessageCommand extends Command {


    public MessageCommand() {
        super("message", "msg");
    }

    @Override
    public String getUsage() {
        return "<player> <message>";
    }

    @Override
    public String getDescription() {
        return "Send a message to a player";
    }

    @Override
    public void onCommand(String label, String cmd, String[] args) throws Exception {
        if(args.length > 1) {
            String name = args[0];
            Optional<Player> target = RMIExercise.getRmiServer().getServer().getPlayers().stream().filter(player -> {
                try {
                    return player.getName().equalsIgnoreCase(name);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }).findFirst();
            if(target.isPresent()) {
                Player player = target.get();

                StringBuilder message = new StringBuilder();
                for (int i = 1; i < args.length; i++) {
                    message.append(args[i]).append(" ");
                }
                target.get().sendMessage(message.toString());

                System.out.println("Message sent to " + player.getName() + ".");
                return;
            }
            System.out.println("Player not found.");
            return;
        }
        System.out.println("Usage: /" + getLabel() + " " + getUsage());
    }
}
