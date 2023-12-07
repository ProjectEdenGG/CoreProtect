package net.coreprotect.utility.eden;

import net.kyori.adventure.identity.Identified;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.ComponentLike;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class PlayerUtils {
    /**
     * Sends a message to a player
     * @param recipient a {@link CommandSender}, {@link Identified}, or {@link UUID}
     * @param message a {@link String} or {@link ComponentLike}
     * @param objects used to {@link String#format(String, Object...) String#format} the message if <code>message</code> is a {@link String}
     */
    public static void send(@Nullable Object recipient, @Nullable Object message, @NotNull Object... objects) {
        if (recipient == null || message == null)
            return;

        if (message instanceof String string && objects.length > 0)
            message = String.format(string, objects);

        if (recipient instanceof CommandSender sender) {
            if (!(message instanceof String || message instanceof ComponentLike))
                message = message.toString();

            if (message instanceof String string)
                sender.sendMessage(new JsonBuilder(string));
            else if (message instanceof ComponentLike componentLike)
                sender.sendMessage(componentLike);
        }

        else if (recipient instanceof OfflinePlayer offlinePlayer) {
            Player player = offlinePlayer.getPlayer();
            if (player != null)
                send(player, message);
        }

        else if (recipient instanceof UUID uuid)
            send(getPlayer(uuid), message);

        else if (recipient instanceof Identity identity)
            send(getPlayer(identity), message);

        else if (recipient instanceof Identified identified)
            send(getPlayer(identified.identity()), message);
    }

    public static @NotNull OfflinePlayer getPlayer(UUID uuid) {
        return Bukkit.getOfflinePlayer(uuid);
    }

    public static @NotNull OfflinePlayer getPlayer(Identity identity) {
        return getPlayer(identity.uuid());
    }
}
