package fr.azirixx.rmi.exercise.plugin;

import com.google.common.base.Charsets;
import fr.azirixx.rmi.exercise.plugin.rmi.RMIServer;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.nio.file.Files;
import java.rmi.registry.Registry;

public class RMIExercise extends JavaPlugin {

    private static RMIExercise instance;

    public static RMIExercise getInstance() {
        return instance;
    }

    RMIServer rmiServer;
    @Override
    public void onEnable() {
        instance = this;

        loadConfig();

        int port = Registry.REGISTRY_PORT;
        if(getConfig().isSet("port") && (getConfig().isInt("port") || !getConfig().getString("port").equals("default"))) {
            port = getConfig().getInt("port");
        }
        rmiServer = new RMIServer(port);
        rmiServer.start();
    }

    @Override
    public void onDisable() {
    }

    public RMIServer getRmiServer() {
        return rmiServer;
    }

    private void loadConfig() {
        try {
            File file = new File(getDataFolder(), "/config.yml");
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                InputStream in = this.getResource("config.yml");
                if(in != null) Files.copy(in, file.toPath());
                else file.createNewFile();
            }
            getConfig().load(new InputStreamReader(new FileInputStream(file), Charsets.UTF_8));
        } catch (InvalidConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }
}