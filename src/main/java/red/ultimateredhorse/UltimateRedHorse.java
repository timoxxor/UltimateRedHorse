package red.ultimateredhorse;

import org.bukkit.plugin.java.JavaPlugin;
import red.ultimateredhorse.blocks.BlocksLogic;
import red.ultimateredhorse.commands.Command;
import red.ultimateredhorse.commands.Command2;
import red.ultimateredhorse.commands.SelectionListener;

public final class UltimateRedHorse extends JavaPlugin {
    public static UltimateRedHorse instance;

    @Override
    public void onEnable() {
        instance = this;
        BlocksLogic.reg(this);
        getServer().getPluginManager().registerEvents(new SelectionListener(), this);
        this.getCommand("saveBebra").setExecutor(new Command());
        this.getCommand("loadBebra").setExecutor(new Command2());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void loadStructure(String name){

    }
}
