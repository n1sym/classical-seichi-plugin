package com.n1sym.seichi;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

public class Scheduler{
  public void setRepeatingTask(Player player) {
    Plugin plugin = Main.getInstance();
    BukkitScheduler scheduler = plugin.getServer().getScheduler();
    scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
      @Override
      public void run() {
        SeichiCountManager.store(player);
      }
    }, 0L, 20L*60);
  }
}
