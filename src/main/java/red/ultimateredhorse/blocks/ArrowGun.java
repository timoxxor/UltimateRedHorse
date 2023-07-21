package red.ultimateredhorse.blocks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.type.Dispenser;
import org.bukkit.entity.Arrow;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.util.Vector;
import red.ultimateredhorse.UltimateRedHorse;

import java.util.HashMap;
import java.util.function.Function;

public class ArrowGun extends Block {
    private static final Material MATERIAL = Material.DISPENSER;
    private static final short DELAY = 20 * 2;
    private static final float SPEED = 1.5F;
    private static final int LIFETIME = DELAY * 2;

    private Vector vector;
    private Location spawn;
    private short t;

    public static void reg(HashMap<Material, Function<BlockPlaceEvent, Block>> m2b) {
        m2b.put(MATERIAL, e -> {
            ArrowGun b = new ArrowGun();
            b.vector = ((Dispenser) e.getBlock().getBlockData()).getFacing().getDirection();
            b.pos = e.getBlock().getLocation();
            b.spawn = b.pos.clone().add(b.vector).add(0.5, 0.5, 0.5);
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
        t++;
        if (t % DELAY == 0) {
            Arrow a = pos.getWorld().spawnArrow(spawn, vector, SPEED, 0);
            a.setGravity(false);
            a.setDamage(100);
            Bukkit.getServer().getScheduler().runTaskLater(UltimateRedHorse.instance, a::remove, LIFETIME);
        }
    }

    @Override
    protected boolean check() {
        return pos.getBlock().getType() == MATERIAL;
    }
}
