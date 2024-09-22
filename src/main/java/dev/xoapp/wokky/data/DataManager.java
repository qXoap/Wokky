package dev.xoapp.wokky.data;

import cn.nukkit.Server;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import dev.xoapp.wokky.Loader;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class DataManager {

    private final Config config;

    public DataManager(String name){
        File file = new File(Loader.getInstance().getDataFolder() + "players/" + name + ".json");

        try {
            if (file.createNewFile()) {
                Server.getInstance().getLogger().info(TextFormat.colorize("&aPlayer &e" + name + "&a Registered"));
            }
        } catch (IOException exception) {
            Server.getInstance().getLogger().info(TextFormat.colorize("&aPlayer &c" + name + "&a Cant be registered"));
        }

        this.config = new Config(file, Config.JSON);
    }

    public void setData(HashMap<String, Integer> data) {
        data.forEach(this.config::set);
        this.config.save();
    }

    public HashMap<String, Object> getSavedData() {
        return (HashMap<String, Object>) this.config.getAll();
    }
}
