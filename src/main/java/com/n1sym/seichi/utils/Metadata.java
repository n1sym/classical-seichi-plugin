package com.n1sym.seichi.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;


public class Metadata {
  public static void set(Player player, String key, Object val) {
    Plugin plugin = Bukkit.getPluginManager().getPlugin("ClassicalSeichi");
    player.setMetadata(key, new FixedMetadataValue(plugin, val));
  }
}
