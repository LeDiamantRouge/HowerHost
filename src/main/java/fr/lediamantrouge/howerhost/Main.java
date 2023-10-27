package fr.lediamantrouge.howerhost;

import fr.kotlini.supragui.InvHandler;
import fr.lediamantrouge.howerhost.command.HostCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        getCommand("host").setExecutor(new HostCommand());

        InvHandler.register(this);

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }

    @Override
    public void onDisable() {

    }

    public static Main getInstance() {
        return instance;
    }
}
