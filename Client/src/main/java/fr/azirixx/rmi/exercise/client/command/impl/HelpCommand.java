package fr.azirixx.rmi.exercise.client.command.impl;

import fr.azirixx.rmi.exercise.client.RMIExercise;
import fr.azirixx.rmi.exercise.client.command.Command;

public class HelpCommand extends Command {

    public HelpCommand() {
        super("help");
    }

    @Override
    public String getUsage() {
        return "";
    }

    @Override
    public String getDescription() {
        return "Get help";
    }

    @Override
    public void onCommand(String label, String cmd, String[] args) {
        System.out.println(">>>>>>>>> HELP <<<<<<<<<");
        for (Command command : RMIExercise.getCommandManager().getCommands().values()) {
            System.out.println("- /" + command.getLabel() + " " + command.getUsage() + " : " + command.getDescription());
        }
    }
}
