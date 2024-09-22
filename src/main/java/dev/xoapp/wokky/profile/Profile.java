package dev.xoapp.wokky.profile;

import cn.nukkit.Server;
import com.google.gson.JsonObject;
import dev.xoapp.wokky.data.DataManager;

import java.io.IOException;
import java.util.HashMap;

public class Profile {

    private final String name;

    private int kills = 0;
    private int deaths = 0;

    public Profile(String name) {
        this.name = name;
        this.load();
    }

    private void load() {

        HashMap<String, Object> data = getData().getSavedData();

        if (data.isEmpty()) {
            return;
        }

        data.forEach((key, value) -> {

            double double_val = Double.parseDouble(value.toString());

            int int_value = (int) double_val;

            if (key.equals("kills")) {
                this.setKills(int_value);
                return;
            }

            this.setDeaths(int_value);
        });
    }

    public String getName() {
        return name;
    }

    public Object getPlayer() {
        return Server.getInstance().getPlayerExact(this.name);
    }

    public int getKills() {
        return kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public DataManager getData() { return new DataManager(this.name); }

    public HashMap<String, Integer> serialize() {

        HashMap<String, Integer> data = new HashMap<>();

        data.put("kills", this.kills);
        data.put("deaths", this.deaths);

        return data;
    }
}
