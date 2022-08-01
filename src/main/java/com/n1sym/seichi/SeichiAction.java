package com.n1sym.seichi;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.block.Block;

import com.n1sym.seichi.utils.Direction;
import com.n1sym.seichi.utils.Range;

import net.kyori.adventure.text.Component;

public class SeichiAction {
  public static void exec(Player player, Block block) {
    int seichi_level = player.getMetadata("seichi_level").get(0).asInt();
    String direction = Direction.getCardinalDirection(player);
    int[] range = Range.calc(direction, seichi_level);

    // プレイヤーが真下(±10F)を向いていた時
    if (Direction.isLookingDownward(player)){
      range = Range.calc("DOWN", seichi_level);
    }

    int breaked_count = rangeBreak(block, range, player);
    SeichiCountManager.add(player, breaked_count);
  }

  private static int rangeBreak(Block block, int[] range, Player player) {
    int i_start = range[0];
    int i_end = range[1];
    int j_start = range[2];
    int j_end = range[3];
    int k_start = range[4];
    int k_end = range[5];

    // 起点ブロックが自分の足元と同じ高さだった場合、y範囲の起点を0とする
    if (player.getLocation().getY() == block.getLocation().getY()) {
      j_start = 0;
      j_end += 1;
    }

    if (isDenyGravityValue(block, range)) {
      player.sendMessage(Component.text("範囲破壊は露天掘りのみ許可しています"));
      return 1;
    }

    int breaked_count = 0;
    for (int i = i_start; i <= i_end; i++) {
      for (int j = j_start; j <= j_end; j++) {
        for (int k = k_start; k <= k_end; k++) {
          Location location = block.getLocation();
          Block neighborhood_block = location.add(i, j, k).getBlock();
          if (i == 0 && j == 0 && k == 0) {
            breaked_count += 1;
            continue;
          }
          if (neighborhood_block.getType() != Material.AIR && !isOutsideTheWorld(location)) {
            breaked_count += 1;
            neighborhood_block.setType(Material.AIR);
          }
        }
      }
    }
    return breaked_count;
  }

  private static boolean isOutsideTheWorld(Location location) {
    boolean bool = false;
    // ここは >= 1000 だったり >1000 だったりしそう
    if (Math.abs(location.getX()) > 1000 || Math.abs(location.getZ()) > 1000) {
      bool = true;
    }
    return bool;
  }

  private static boolean isDenyGravityValue(Block block, int[] range) {
    boolean bool = false;
    int i_start = range[0];
    int i_end = range[1];
    int j_end = range[3];
    int k_start = range[4];
    int k_end = range[5];
    int air_count = 0;
    int block_count = 0;

    for (int i = i_start; i <= i_end; i++) {
      for (int j = j_end + 1; j <= j_end + 5; j++) {
        for (int k = k_start; k <= k_end; k++) {
          Location location = block.getLocation();
          Block neighborhood_block = location.add(i, j, k).getBlock();
          if (neighborhood_block.getType() == Material.AIR) {
            air_count += 1;
          } else {
            block_count += 1;
          }
        }
      }
    }

    double gravity_rate = Double.valueOf(block_count) / Double.valueOf(air_count + block_count);
    if (gravity_rate > 0.5) {
      bool = true;
    }
    return bool;
  }

  
}
