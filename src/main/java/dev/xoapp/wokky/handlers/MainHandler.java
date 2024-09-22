package dev.xoapp.wokky.handlers;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.utils.TextFormat;
import dev.xoapp.wokky.factory.ProfileFactory;
import dev.xoapp.wokky.profile.Profile;

public class MainHandler implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        Profile profile = ProfileFactory.get(player.getName());

        if (profile != null) {
            return;
        }

        player.sendMessage(TextFormat.colorize("&aProfile created. loading data.."));
        ProfileFactory.register(new Profile(player.getName()));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {

        Player player = event.getPlayer();

        Profile profile = ProfileFactory.get(player.getName());

        if (profile == null) {
            return;
        }

        ProfileFactory.save();
        ProfileFactory.delete(player.getName());
    }
}
