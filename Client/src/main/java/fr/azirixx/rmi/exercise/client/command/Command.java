package fr.azirixx.rmi.exercise.client.command;

import java.util.List;

public abstract class Command {

    private String label;
    private List<String> aliases;

    public Command(String name, String... aliases) {
        this.label = name.toLowerCase();
        this.aliases = List.of(aliases);
    }

    public String getLabel() {
        return label;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public abstract String getUsage();

    public abstract String getDescription();

    public abstract void onCommand(String label, String cmd, String[] args) throws Exception;
}
