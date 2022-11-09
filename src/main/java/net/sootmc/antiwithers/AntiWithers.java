package net.sootmc.antiwithers;

import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class AntiWithers extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("AntiWithers has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("AntiWithers has been disabled!");
    }

    @EventHandler
    public void onWitherSpawn(EntitySpawnEvent event) {
        if(event.getEntityType().equals(EntityType.WITHER)) {
            event.setCancelled(true);
        }
    }


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        for(World world : getServer().getWorlds()) {
            world.getEntities().forEach(entity -> {
                if(entity.getType().equals(EntityType.WITHER)) {
                    entity.remove();
                    getLogger().info("Removed a wither from " + world.getName());
                }
            });
        }
    }
}
