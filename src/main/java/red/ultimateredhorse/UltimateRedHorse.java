package red.ultimateredhorse;

import org.bukkit.plugin.java.JavaPlugin;
import red.ultimateredhorse.blocks.BlocksLogic;

public final class UltimateRedHorse extends JavaPlugin {
    public static UltimateRedHorse instance;

    @Override
    public void onEnable() {
        instance = this;
        BlocksLogic.reg(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
