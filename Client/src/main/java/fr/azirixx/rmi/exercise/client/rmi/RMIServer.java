package fr.azirixx.rmi.exercise.client.rmi;

import fr.endmc.exercise.Server;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {

    int port;

    Registry registry;

    public RMIServer( int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public Registry getRegistry() {
        return registry;
    }

    public Server getServer() {
        try {
            return (Server) registry.lookup("Server");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void start() {
        try {
            System.out.println("Connecting to localhost:" + getPort() + "...");
            this.registry = LocateRegistry.getRegistry(getPort());
            System.out.println("Connected to localhost:" + getPort() + "!");
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

}
