package pl.bmstefanski.tools.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.bmstefanski.tools.impl.CommandImpl;
import pl.bmstefanski.tools.impl.configuration.Messages;
import pl.bmstefanski.tools.util.MessageUtils;

import java.util.Collections;

public class ClearCommand extends CommandImpl {

    public ClearCommand() {
        super("clear", "clear command", "/clear [player]", "clear", Collections.singletonList(""));
    }

    @Override
    public void onExecute(CommandSender commandSender, String[] args) {

        final Player player = (Player) commandSender;

        if (args.length > 1) {
            MessageUtils.sendMessage(player, Messages.CORRECT_USAGE.replace("%usage%", getUsage()));
            return;
        }

        if (args.length == 0) {
            player.getInventory().clear();
            MessageUtils.sendMessage(player, Messages.CLEAR);
        } else {
            if (Bukkit.getPlayer(args[0]) == null) {
                MessageUtils.sendMessage(player, Messages.PLAYER_NOT_FOUND.replace("%player%", args[0]));
                return;
            }

            final Player target = Bukkit.getPlayer(args[0]);

            target.getInventory().clear();

            MessageUtils.sendMessage(target, Messages.CLEAR);
            MessageUtils.sendMessage(player, Messages.CLEAR_OTHER.replace("%player%", target.getName()));
        }
    }
}
