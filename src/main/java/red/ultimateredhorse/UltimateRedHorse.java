package red.ultimateredhorse;

import org.bukkit.plugin.java.JavaPlugin;
import red.ultimateredhorse.blocks.BlocksLogic;

public final class UltimateRedHorse extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        BlocksLogic.reg(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
