package red.ultimateredhorse.world;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

public class VoidListener implements Listener {
    @EventHandler
    public void onChunkLoad(ChunkLoadEvent chunkLoadEvent){
        if (chunkLoadEvent.isNewChunk()){
            for (int x = 0; x < 16; x++){
                for (int z = 0; z < 16; z++){
                    for (int y = chunkLoadEvent.getWorld().getMinHeight(); y < chunkLoadEvent.getWorld().getMaxHeight(); y++){
                        chunkLoadEvent.getWorld().setType(x*chunkLoadEvent.getChunk().getX(), y, z*chunkLoadEvent.getChunk().getZ(), Material.AIR );
                    }
                }
            }
        }
    }
}
