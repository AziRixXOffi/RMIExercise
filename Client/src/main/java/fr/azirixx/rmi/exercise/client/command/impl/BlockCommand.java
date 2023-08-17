package fr.azirixx.rmi.exercise.client.command.impl;

import fr.azirixx.rmi.exercise.client.RMIExercise;
import fr.azirixx.rmi.exercise.client.command.Command;
import fr.endmc.exercise.Server;

public class BlockCommand extends Command {


    public BlockCommand() {
        super("block");
    }

    @Override
    public String getUsage() {
        return "<world> <x> <y> <z> [material]";
    }

    @Override
    public String getDescription() {
        return "Get or set a block";
    }

    @Override
    public void onCommand(String label, String cmd, String[] args) throws Exception {
        if(args.length > 3) {
            Server server = RMIExercise.getRmiServer().getServer();

            String worldName = args[0];
            int x = Integer.parseInt(args[1]);
            int y = Integer.parseInt(args[2]);
            int z = Integer.parseInt(args[3]);
            String material = args.length > 4 ? args[4] : null;

            if(material == null) {
                System.out.println("Get block (" + worldName + ", " + x + ", " + y + ", " + z + ") : " + server.getBlock(worldName, x, y, z));
            } else {
                server.setBlock(worldName, x, y, z, material);
                System.out.println("Set block (" + worldName + ", " + x + ", " + y + ", " + z + ") : " + material);
            }
            return;
        }
        System.out.println("Usage: /" + getLabel() + " " + getUsage());
    }
}
