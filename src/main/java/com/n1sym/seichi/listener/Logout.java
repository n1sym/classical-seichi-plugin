package com.n1sym.seichi.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.n1sym.seichi.SeichiCountManager;

import org.bukkit.entity.Player;

public class Logout implements Listener{
  @EventHandler
  public void onPlayerLogout(PlayerQuitEvent event) {
    Player player = event.getPlayer();
    SeichiCountManager.store(player);
  }
}
