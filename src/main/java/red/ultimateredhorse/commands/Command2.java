package red.ultimateredhorse.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import red.ultimateredhorse.structuregen.BlockInfo;
import red.ultimateredhorse.structuregen.Box;

import java.io.File;
import java.util.logging.Level;

import static red.ultimateredhorse.UltimateRedHorse.instance;

public class Command2 implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        if (commandSender instanceof Player){
            Player player = (Player) commandSender;
            if (player.isOp()){
                if (strings == null) return false;
                String arg = strings[0];
                try {
                    Bukkit.getServer().getLogger().log(Level.WARNING, arg);
                    Box<BlockInfo> box = (Box<BlockInfo>) Box.loadFormFile(new File(instance.getDataFolder(), arg));
                    box.build((x, y, z, value) -> player.getWorld().setBlockData(player.getLocation().add(x, y, z), value.getBlockData()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return false;
    }
}