package fr.azirixx.rmi.exercise.client;

import fr.azirixx.rmi.exercise.client.command.CommandManager;
import fr.azirixx.rmi.exercise.client.command.impl.*;
import fr.azirixx.rmi.exercise.client.rmi.RMIServer;

import java.rmi.registry.Registry;

public class RMIExercise {

    private static RMIServer rmiServer;
    private static CommandManager commandManager;

    public static void main(String[] args) {
        int port = Registry.REGISTRY_PORT;
        for (int i = 0; i < args.length-1; i++) {
            String key = args[i].toLowerCase();
            String value = args[i+1];
            if(key.equals("--port") || key.equals("-p")) {
                port = Integer.parseInt(value);
            }
        }
        rmiServer = new RMIServer(port);
        rmiServer.start();

        commandManager = new CommandManager();
        commandManager.start();
        commandManager.registerCommand(new BlockCommand());
        commandManager.registerCommand(new ListCommand());
        commandManager.registerCommand(new MessageCommand());
        commandManager.registerCommand(new PlayParticleCommand());
        commandManager.registerCommand(new StopCommand());
        commandManager.registerCommand(new TeleportCommand());
        commandManager.registerCommand(new HelpCommand());

        System.out.println("Client is ready! Type \"help\" for more information.");
    }

    public static RMIServer getRmiServer() {
        return rmiServer;
    }

    public static CommandManager getCommandManager() {
        return commandManager;
    }
}