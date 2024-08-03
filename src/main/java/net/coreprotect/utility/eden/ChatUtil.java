package net.coreprotect.utility.eden;

import net.coreprotect.utility.Color;
import net.coreprotect.utility.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatUtil {

    public static String getTeleportCommand(String commandBase, int x, int y, int z, int worldId){
        return "/" + commandBase + " teleport wid:" + worldId + " " + (x + 0.50) + " " + y + " " + (z + 0.50);
    }

    public static String getCoords(int x, int y, int z, int worldId, boolean italic, boolean displayWorld){
        StringBuilder worldDisplay = new StringBuilder();
        if (displayWorld)
            worldDisplay.append("/" + getWorldName(worldId));

        return Color.GREY + (italic ? Color.ITALIC : "") + "(x" + x + "/y" + y + "/z" + z + worldDisplay.toString() + ")";
    }

    public static String getWorldName(int worldId){
        return Util.getWorldName(worldId);
    }


    public static String getFormattedTime(long time){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z").format(new Date(time * 1000L));
    }
}
