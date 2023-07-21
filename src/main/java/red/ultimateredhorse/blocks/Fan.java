package red.ultimateredhorse.blocks;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.type.Dispenser;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.HashMap;
import java.util.function.Function;

public class Fan extends Block {

    private static final Material MATERIAL = Material.DISPENSER;
    private static final float POWER = 0.5F;
    private static final float DISTANCE = 4F;

    private Location center;
    public static void reg(HashMap<Material, Function<BlockPlaceEvent, Block>> m2b) {
        m2b.put(MATERIAL, e -> {
            Fan b = new Fan();
            b.pos = e.getBlock().getLocation();
            b.center = b.pos.clone().add(0.5,0.5,0.5);
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
            if (player.getLocation().getBlockX()==pos.getBlockX() &&
                    player.getLocation().getBlockZ()==pos.getBlockZ() &&
                    player.getLocation().getBlockY() > pos.getBlockY() &&
                    player.getLocation().getY() < pos.getBlockY() + DISTANCE) {
                player.setVelocity(player.getVelocity().setY(POWER));
            }
        });
    }

    @Override
    protected boolean check() {
        return pos.getBlock().getType() == MATERIAL;
    }
}
