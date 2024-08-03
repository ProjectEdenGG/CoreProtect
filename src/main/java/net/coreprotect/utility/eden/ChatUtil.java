package net.coreprotect.utility.eden;

import net.coreprotect.language.Phrase;
import net.coreprotect.utility.Color;
import net.coreprotect.utility.Util;
import org.bukkit.command.CommandSender;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ChatUtil {

    public static void send(String[] data, CommandSender player, String command, String action, String time, int unix, String tag, String phrase){
        long timeLong = Long.parseLong(time);
        String formattedTime = Color.GREY + getFormattedTime(timeLong);
        String timeSince = Color.GREY + getTimeSince(timeLong, unix);

        List<String> dataFinal = Arrays.stream(data).filter(line -> line != null && !line.isEmpty()).toList();

        new JsonBuilder().group()
                .next(timeSince).hover(formattedTime).group()
                .next(" " + tag + " ").group()
                .next(phrase).hover(action).group()
                //.next(" [Data]").hover(dataFinal).group()
                .send(player);
    }

    public static void send(String[] data, CommandSender player, String command, int worldId, int x, int y, int z, boolean displayWorld, boolean italic, String action, String time, int unix, String tag, String phrase){
        send(data, player, command, worldId, x, y, z, displayWorld, italic, action, time, unix, tag, phrase, null);
    }

    public static void send(String[] data, CommandSender player, String command, int worldId, int x, int y, int z, boolean displayWorld, boolean italic, String action, String time, int unix, String tag, String phrase, String phraseHover){
        StringBuilder worldDisplay = new StringBuilder();
        if (displayWorld)
            worldDisplay.append("/" + Util.getWorldName(worldId));

        long timeLong = Long.parseLong(time);
        String formattedTime = Color.GREY + getFormattedTime(timeLong);
        String timeSince = Color.GREY + getTimeSince(timeLong, unix);

        String teleportCommand = "/" + command + " teleport wid:" + worldId + " " + (x + 0.50) + " " + y + " " + (z + 0.50);
        String coords = Color.GREY + (italic ? Color.ITALIC : "") + "(x" + x + "/y" + y + "/z" + z + worldDisplay.toString() + ")";

        String phraseHoverFinal = coords + " " + action;
        if(phraseHover != null && !phraseHover.isEmpty())
            phraseHoverFinal = phraseHover + "\n" + phraseHoverFinal;

        List<String> dataFinal = Arrays.stream(data).filter(line -> line != null && !line.isEmpty()).toList();

        new JsonBuilder()
            .next(timeSince).hover(formattedTime).group()
            .next(" " + tag + " ").group()
            .next(phrase).hover(phraseHoverFinal).command(teleportCommand).group()
            //.next(" [Data]").hover(dataFinal).group()
            .send(player);
    }

    public static String getTimeSince(long resultTime, long currentTime) {
        StringBuilder message = new StringBuilder();
        double timeSince = currentTime - (resultTime + 0.00);
        if (timeSince < 0.00) {
            timeSince = 0.00;
        }

        // minutes
        timeSince = timeSince / 60;
        if (timeSince < 60.0) {
            message.append(Phrase.build(Phrase.LOOKUP_TIME, new DecimalFormat("0.00").format(timeSince) + "/m"));
        }

        // hours
        if (message.length() == 0) {
            timeSince = timeSince / 60;
            if (timeSince < 24.0) {
                message.append(Phrase.build(Phrase.LOOKUP_TIME, new DecimalFormat("0.00").format(timeSince) + "/h"));
            }
        }

        // days
        if (message.length() == 0) {
            timeSince = timeSince / 24;
            message.append(Phrase.build(Phrase.LOOKUP_TIME, new DecimalFormat("0.00").format(timeSince) + "/d"));
        }

        return message.toString();
    }

    public static String getFormattedTime(long time){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z").format(new Date(time * 1000L));
    }
}
