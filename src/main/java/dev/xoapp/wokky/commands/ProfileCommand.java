package dev.xoapp.wokky.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import dev.xoapp.wokky.factory.ProfileFactory;
import dev.xoapp.wokky.profile.Profile;

import java.text.NumberFormat;
import java.util.HashMap;

public class ProfileCommand extends Command {

    public ProfileCommand() {
        super("profile", "View Your profile data");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {

        if (!sender.isPlayer()) {
            return false;
        }

        Player player = sender.asPlayer();

        Profile profile = ProfileFactory.get(player.getName());

        if (profile == null) {
            player.sendMessage(TextFormat.colorize("&cProfile Error, Please reconnect"));
            return false;
        }

        HashMap<String, Integer> profile_data = profile.serialize();

        profile_data.forEach((key, value) -> {

            int formatted_value = Integer.parseInt(NumberFormat.getInstance().format(value));

            player.sendMessage(TextFormat.colorize("&e" + key + ": &f" + formatted_value));
        });

        return true;
    }
}
