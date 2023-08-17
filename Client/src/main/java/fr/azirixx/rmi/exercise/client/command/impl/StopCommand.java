package fr.azirixx.rmi.exercise.client.command.impl;

import fr.azirixx.rmi.exercise.client.command.Command;

public class StopCommand extends Command {

    public StopCommand() {
        super("stop", "end");
    }

    @Override
    public String getUsage() {
        return "";
    }

    @Override
    public String getDescription() {
        return "Stop the client";
    }

    @Override
    public void onCommand(String label, String cmd, String[] args) {
        System.out.println("Stopping client...");
        Runtime.getRuntime().exit(0);
    }
}
