package wtf.moonlight.features.command.impl;

import wtf.moonlight.MoonLight;
import wtf.moonlight.features.command.Command;
import wtf.moonlight.features.command.CommandExecutionException;
import wtf.moonlight.utils.misc.DebugUtils;

import static wtf.moonlight.utils.InstanceAccess.mc;

public class FriendCommand extends Command {
    @Override
    public String[] getAliases() {
        return new String[]{"friend", "f", "fr"};
    }

    @Override
    public void execute(final String[] arguments) throws CommandExecutionException {
        if (arguments.length == 1) {
            DebugUtils.sendMessage("Usage: " + getUsage());
            return;
        }
        final String lowerCase = arguments[1].toLowerCase();
        if (arguments.length == 2) {
            switch (lowerCase) {
                case "clear": {
                    DebugUtils.sendMessage("Cleared all friended players");
                    MoonLight.INSTANCE.getFriendManager().getFriends().clear();
                    break;
                }
                case "list": {
                    if (!MoonLight.INSTANCE.getFriendManager().getFriends().isEmpty()) {
                        DebugUtils.sendMessage("Friend§7[§f" + MoonLight.INSTANCE.getFriendManager().getFriends().size() + "§7]§f : §a" + MoonLight.INSTANCE.getFriendManager().getFriendsName());
                        break;
                    }
                    DebugUtils.sendMessage("The friend list is empty");
                    break;
                }
            }
        } else {
            if (arguments.length != 3) {
                throw new CommandExecutionException(this.getUsage());
            }
            if (arguments[2].contains(mc.thePlayer.getName())) {
                DebugUtils.sendMessage("§c§lNO");
                return;
            }
            final String lowerCase2 = arguments[1].toLowerCase();
            switch (lowerCase2) {
                case "add": {
                    DebugUtils.sendMessage("§b" + arguments[2] + " §7has been §2friended");
                    MoonLight.INSTANCE.getFriendManager().add(arguments[2]);
                    break;
                }
                case "remove": {
                    DebugUtils.sendMessage("§b" + arguments[2] + " §7has been §2unfriended");
                    MoonLight.INSTANCE.getFriendManager().remove(arguments[2]);
                    break;
                }
            }
        }
    }

    @Override
    public String getUsage() {
        return "friend add <name> | remove <name> | list | clear";
    }
}