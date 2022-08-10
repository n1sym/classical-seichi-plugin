package com.n1sym.seichi.listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.n1sym.seichi.CustomMenu;
import com.n1sym.seichi.utils.ItemGroups;

public class InteractClick implements Listener {
  @EventHandler
  public void onClickEvent(PlayerInteractEvent event) {
    Player player = event.getPlayer();
    ItemStack item = player.getInventory().getItemInMainHand();

    // カスタムメニューを開く
    if (item.getType() == Material.STICK) {
      CustomMenu.openCustomMenu(player);
      return;
    }

    // 凝固処理
    int seichi_level = player.getMetadata("seichi_level").get(0).asInt();
    int j_end = -4;
    if (seichi_level >= 30) {
      j_end = -6;
    }
    if (item.getType() == Material.DIAMOND_HOE) {
      Location player_location = player.getLocation();
      Block block = player_location.getBlock();
      for (int i = -4; i <= 4; i++) {
        for (int j = j_end; j <= 0; j++) {
          for (int k = -4; k <= 4; k++) {
            Location location = block.getLocation();
            Block neighborhood_block = location.add(i, j, k).getBlock();
            if (ItemGroups.isWaterMaterial(neighborhood_block)) {
              neighborhood_block.setType(Material.ICE);
            }
            if (ItemGroups.isLavaMaterial(neighborhood_block)) {
              neighborhood_block.setType(Material.MAGMA_BLOCK);
            }
          }
        }
      }
      return;
    }

  }
}
