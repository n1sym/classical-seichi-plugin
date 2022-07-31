package com.n1sym.seichi.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import com.n1sym.seichi.SeichiMenu;
import com.n1sym.seichi.Tools;

public class InventoryClick implements Listener {
  @EventHandler
  public void onClick(InventoryClickEvent event) {
    if (event.getClickedInventory() == null){
      return;
    }
    if (!(event.getClickedInventory().getHolder() instanceof SeichiMenu)) {
      return;
    }
    event.setCancelled(true);
    ItemStack item = event.getCurrentItem();
    Player player = (Player) event.getWhoClicked();
    if (item != null) {
      if (item.getType() == Material.SCAFFOLDING) {
        Tools.getScaffoldingStack(player);
      }
      if (item.getType() == Material.TORCH) {
        Tools.getTorchStack(player);
      }
      if (item.getType() == Material.STONE_PICKAXE) {
        Tools.getInitTools(player);
      }
    }
  }
}
