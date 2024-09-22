package dev.xoapp.wokky.handlers;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerDeathEvent;
import dev.xoapp.wokky.factory.ProfileFactory;
import dev.xoapp.wokky.profile.Profile;

public class DeathHandler implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {

        Player entity = event.getEntity();

        if (!entity.isPlayer()) {
            return;
        }

        Player player = entity.asPlayer();

        Profile profile = ProfileFactory.get(player.getName());

        if (profile != null) {
            profile.setDeaths(profile.getDeaths() + 1);
        }

        Entity killer = player.getKiller();

        if (killer == null) {
            return;
        }

        if (!(killer instanceof Player)) {
            return;
        }

        Player i_player = ((Player) killer).asPlayer();

        Profile i_profile = ProfileFactory.get(i_player.getName());

        if (i_profile == null) {
            return;
        }

        i_profile.setKills(i_profile.getKills() + 1);
     }
}
