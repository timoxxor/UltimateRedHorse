package red.ultimateredhorse.blocks;

import jdk.internal.vm.annotation.Hidden;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.block.BlockPlaceEvent;

public abstract class Block {
    protected Location pos;
    public boolean tick() {
        if (!check()) return false;
        work();
        return true;
    }
    public abstract void destroy();
    protected abstract void work();
    protected abstract boolean check();
}
