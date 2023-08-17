package fr.azirixx.rmi.exercise.plugin.rmi;

import fr.azirixx.rmi.exercise.plugin.impl.ServerImpl;
import fr.endmc.exercise.Server;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIManager {

    int port;

    ServerImpl server;

    public RMIManager(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public Server getServer() {
        return server;
    }

    public void start() {
        try {
            this.server = new ServerImpl();

            System.out.println("Connecting to localhost:" + getPort() + "...");

            Remote remote = UnicastRemoteObject.exportObject(this.server, 0);
            Registry registry = LocateRegistry.createRegistry(getPort());
            registry.bind("Server", remote);
            System.out.println("Connected to localhost:" + getPort() + "!");
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }

}
