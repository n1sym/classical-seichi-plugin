package com.n1sym.seichi.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.entity.Player;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import com.n1sym.seichi.SeichiAction;
import com.n1sym.seichi.SeichiCountManager;
import com.n1sym.seichi.WoodCutter;
import com.n1sym.seichi.utils.ItemGroups;

public class BlockBreak implements Listener {
  @EventHandler
  public void onBlockBreak(BlockBreakEvent event) {
    event.setDropItems(false);

    Player player = event.getPlayer();
    ItemStack i = player.getInventory().getItemInMainHand();
    Block block = event.getBlock();

    // 木こり処理
    if (i.getType() == Material.STONE_AXE && ItemGroups.isWoodMaterial(block)) {
      int breaked_count = WoodCutter.cut(block);
      SeichiCountManager.add(player, breaked_count);
      return;
    }

    // 通常破壊処理
    if ((i.getType() != Material.STONE_PICKAXE) && (i.getType() != Material.STONE_SHOVEL)) {
      SeichiCountManager.add(player, 1);
      return;
    }

    // 範囲破壊
    SeichiAction.exec(player, block);
  }
}
