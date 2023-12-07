package net.coreprotect.event;

import org.bukkit.Location;
import org.bukkit.Material;

public class CoreProtectPreLogBlockEvent extends CoreProtectPreLogEvent {
    private boolean cancelled = false;

    BlockAction action;
    Location location;
    Material type;
    String blockData;

    public CoreProtectPreLogBlockEvent(String user, BlockAction action, Location location, Material type, String blockData) {
        super(user);
        this.action = action;
        this.location = location;
        this.type = type;
        this.blockData = blockData;
    }

    public BlockAction getAction() {
        return action;
    }

    public Location getLocation() {
        return location;
    }

    public Material getType() {
        return type;
    }

    public String getBlockData() {
        return blockData;
    }

    public enum BlockAction {
        PLACE,
        BREAK
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }
}
