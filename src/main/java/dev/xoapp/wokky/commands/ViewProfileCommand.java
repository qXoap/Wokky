package dev.xoapp.wokky.commands;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import dev.xoapp.wokky.factory.ProfileFactory;
import dev.xoapp.wokky.profile.Profile;

import java.text.NumberFormat;

public class ViewProfileCommand extends Command {

    public ViewProfileCommand() {
        super("viewprofile", "View Other Player Profile");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {

        if (args[0].isEmpty()) {
            sender.sendMessage(TextFormat.colorize("&cUse /viewprofile (player)"));
            return false;
        }

        Player i_player = Server.getInstance().getPlayer(args[0]);

        if (i_player == null) {
            sender.sendMessage(TextFormat.colorize("&cPlayer not found"));
            return false;
        }

        Profile i_profile = ProfileFactory.get(i_player.getName());

        if (i_profile == null) {
            sender.sendMessage(TextFormat.colorize("&cPlayer Profile not found, reconnect"));
            return false;
        }

        i_profile.serialize().forEach((key, value) -> {
            int formatted_value = Integer.parseInt(NumberFormat.getInstance().format(value));

            sender.sendMessage(TextFormat.colorize("&e" + key + ": &f" + formatted_value));
        });

        return true;
    }
}
