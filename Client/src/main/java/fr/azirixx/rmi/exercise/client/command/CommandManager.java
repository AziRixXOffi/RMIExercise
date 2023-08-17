package fr.azirixx.rmi.exercise.client.command;

import java.util.*;

public class CommandManager {

    Thread thread;
    Map<String, Command> commands;

    public CommandManager() {
        commands = new HashMap<>();
    }

    public void start() {
        thread = new Thread(() -> {
            while(thread.isAlive()) {
                try {
                    String cmd = System.console().readLine();
                    Command command = getCommand(cmd.split(" ")[0]);

                    if(command != null) {
                        List<String> args = new ArrayList<>(Arrays.asList(cmd.split(" ")));
                        args.remove(0);

                        command.onCommand(command.getLabel(), cmd, args.toArray(new String[0]));
                    } else {
                        System.err.println("Command not found.");
                    }
                } catch (Exception e) {
                    System.err.println("An error occurred while executing the command.");
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public void registerCommand(Command command) {
        commands.put(command.getLabel(), command);
    }

    public Command getCommand(String cmd) {
        cmd = cmd.toLowerCase();

        if(commands.containsKey(cmd)) {
            return commands.get(cmd);
        }
        for (Command command : commands.values()) {
            if(command.getAliases().contains(cmd)) {
                return command;
            }
        }

        return null;
    }

    public Map<String, Command> getCommands() {
        return commands;
    }
}
