package red.ultimateredhorse.blocks;

import org.bukkit.Material;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.HashMap;
import java.util.function.Function;

public class BarbedWire extends Block {
    private static final Material MATERIAL = Material.COBWEB;

    public static void reg(HashMap<Material, Function<BlockPlaceEvent, Block>> m2b) {
        m2b.put(MATERIAL, e -> {
            BarbedWire b = new BarbedWire();
            b.pos = e.getBlock().getLocation();
            return b;
        });
    }

    @Override
    public void destroy() {
        if (pos.getBlock().getType() == MATERIAL) {
            pos.getBlock().setType(Material.AIR);
        }
    }

    @Override
    protected void work() {
        pos.getWorld().getPlayers().forEach(player -> {
            if (player.getLocation().getBlockX() == pos.getBlockX() &&
                    player.getLocation().getBlockZ() == pos.getBlockZ() &&
                    player.getLocation().getBlockY() == pos.getBlockY()) {
                player.damage(100);
            }
        });
    }

    @Override
    protected boolean check() {
        return pos.getBlock().getType() == MATERIAL;
    }
}
