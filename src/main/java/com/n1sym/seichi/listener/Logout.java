package com.n1sym.seichi.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.n1sym.seichi.repository.AccountRepository;
import com.n1sym.seichi.repository.SeichiCountRepository;

import org.bukkit.entity.Player;
import java.util.UUID;

public class Logout implements Listener{
  @EventHandler
  public void onPlayerLogout(PlayerQuitEvent event) {
    Player player = event.getPlayer();
    UUID uuid = player.getUniqueId();
    try {
      Integer id = AccountRepository.find(uuid);
      Integer count = player.getMetadata("seichi_count").get(0).asInt();
      SeichiCountRepository.set(id, count);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
