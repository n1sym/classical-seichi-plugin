package com.n1sym.seichi;

import org.bukkit.boss.*;
import org.bukkit.entity.Player;

import com.n1sym.seichi.utils.Metadata;

import org.bukkit.Bukkit;

public class Bar {
  public static void createSeichiBar(Player player) {
    BossBar bar = Bukkit.getServer().createBossBar("", BarColor.GREEN, BarStyle.SOLID);
    bar.addPlayer(player);
    Metadata.set(player, "bar", bar);
  }

  public static void updateSeichiBar(Integer level, Integer count, Integer next, Player player, double progress) {
    BossBar boss_bar = (BossBar) player.getMetadata("bar").get(0).value();
    String now_seichi_count = String.format("%,d", count);
    String next_seichi_count = String.format("%,d", next);
    boss_bar.setTitle("level" + level.toString() + " " + now_seichi_count + "/" + next_seichi_count);
    boss_bar.setProgress(progress);
  }
}
