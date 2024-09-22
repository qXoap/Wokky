package dev.xoapp.wokky;

import cn.nukkit.command.Command;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;
import dev.xoapp.wokky.commands.ProfileCommand;
import dev.xoapp.wokky.commands.ViewProfileCommand;
import dev.xoapp.wokky.factory.ProfileFactory;
import dev.xoapp.wokky.handlers.DeathHandler;
import dev.xoapp.wokky.handlers.MainHandler;

import java.util.HashMap;

public class Loader extends PluginBase {

    private static Loader instance;

    @Override
    public void onEnable() {
        instance = new Loader();

        commands().forEach((key, command) -> {
            getServer().getCommandMap().register(key, command);
        });

        getServer().getPluginManager().registerEvents(new MainHandler(), this);
        getServer().getPluginManager().registerEvents(new DeathHandler(), this);

        getLogger().info(TextFormat.colorize("&aLoaded &e" + commands().size() + "&a Commands"));
    }

    @Override
    public void onDisable() {
        ProfileFactory.save();
    }

    // This code is for test the hash_maps xd
    public HashMap<String, Command> commands() {
        HashMap<String, Command> data = new HashMap<>();

        data.put("profile", new ProfileCommand());
        data.put("viewprofile", new ViewProfileCommand());

        return data;
    }

    public static Loader getInstance() {
        return instance;
    }
}
