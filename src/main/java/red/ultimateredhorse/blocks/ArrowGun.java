package red.ultimateredhorse.blocks;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.type.Dispenser;
import org.bukkit.entity.Arrow;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.function.Function;

public class ArrowGun extends Block {
    private static final Material MATERIAL = Material.DISPENSER;
    private static final short DELAY = 20 * 2;
    private static final float SPEED = 4.5F;

    private Vector vector;
    private short t;

    public static void reg(HashMap<Material, Function<BlockPlaceEvent, Block>> m2b) {
        m2b.put(MATERIAL, e -> {
            ArrowGun b = new ArrowGun();
            b.vector = ((Dispenser)e.getBlock().getBlockData()).getFacing().getDirection();
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
        t++;
        if (t % DELAY == 0) {
            Arrow a = pos.getWorld().spawnArrow(pos.clone().add(vector),vector, SPEED,0);
            a.setGravity(false);
            a.setDamage(100);
        }
    }

    @Override
    protected boolean check() {
        return pos.getBlock().getType() == MATERIAL;
    }
}
