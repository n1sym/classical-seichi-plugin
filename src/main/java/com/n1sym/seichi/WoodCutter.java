package com.n1sym.seichi;

import java.util.ArrayDeque;
import java.util.Deque;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import com.n1sym.seichi.utils.ItemGroups;

public class WoodCutter {
  public static int cut(Block block) {
    int cnt = 0;
    Deque<Block> queue = new ArrayDeque<>();
    queue.add(block);
    while (queue.peek() != null) {
      Block tblock = queue.poll();
      for (int i = -1; i <= 1; i++) {
        for (int j = -1; j <= 1; j++) {
          for (int k = -1; k <= 1; k++) {
            Location location = tblock.getLocation();
            Block nblock = location.add(i, j, k).getBlock();
            if (ItemGroups.isWoodMaterial(nblock)) {
              queue.add(nblock);
              cnt += 1;
              nblock.setType(Material.AIR);
            }
          }
        }
      }
    }
    return cnt;
  }
}
