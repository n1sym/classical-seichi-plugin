package com.n1sym.seichi.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import com.n1sym.seichi.Bar;
import com.n1sym.seichi.SeichiCountManager;
import com.n1sym.seichi.repository.AccountNameRepository;
import com.n1sym.seichi.repository.AccountRepository;
import com.n1sym.seichi.repository.SeichiCountRepository;
import com.n1sym.seichi.utils.Metadata;

import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import java.util.UUID;

public class Login implements Listener {
  @EventHandler
  public void onPlayerLogin(PlayerLoginEvent event) {
    Player player = event.getPlayer();
    String name = player.getName();
    UUID uuid = player.getUniqueId();
    Bukkit.getLogger().info(name + "がログインしました");

    try {
      Integer id = AccountRepository.find(uuid);
      Integer count = SeichiCountRepository.find(id);
      if (AccountNameRepository.find(id).isEmpty()) {
        AccountNameRepository.create(id, name);
      }
      Metadata.set(player, "seichi_count", count);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    Integer count = player.getMetadata("seichi_count").get(0).asInt();
    Bar.createSeichiBar(player);
    SeichiCountManager.set(player, count);
    SeichiCountManager.add(player, 0);
  }
}
