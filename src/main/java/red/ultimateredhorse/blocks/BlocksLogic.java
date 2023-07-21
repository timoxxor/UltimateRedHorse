package red.ultimateredhorse.blocks;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

public class BlocksLogic implements Listener {

    private static final HashMap<Material, Function<BlockPlaceEvent, Block>> m2b = new HashMap<>();
    private static final ArrayList<Block> activeBlocks = new ArrayList<Block>();
    private static BlocksLogic bl;
    private static final ArrayList<Block> toDel = new ArrayList<>();

    public static void reg(Plugin plugin) {
        if (bl != null) return;
        bl = new BlocksLogic();
        regBlocks();
        plugin.getServer().getPluginManager().registerEvents(bl, plugin);
        plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, () -> bl.tick(), 0, 1);
    }

    private static void regBlocks() {
        ArrowGun.reg(m2b);
        Fan.reg(m2b);
    }

    private void tick() {
        activeBlocks.forEach(block -> {
            if (!block.tick()) {
                toDel.add(block);
            }
        });
        toDel.forEach(block -> {
            block.destroy();
            activeBlocks.remove(block);
        });
        toDel.clear();
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if (m2b.containsKey(e.getBlock().getType())) {
            Block b = m2b.get(e.getBlock().getType()).apply(e);
            if (b != null) {
                activeBlocks.add(b);
            }
        }
    }
}
