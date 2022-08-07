package com.n1sym.seichi.utils;

import org.bukkit.Material;
import org.bukkit.block.Block;

public class ItemGroups {
  public static boolean isWaterMaterial(Block block) {
    boolean is_water = false;
    if (block.getType() == Material.WATER ||
        block.getType() == Material.BUBBLE_COLUMN ||
        block.getType() == Material.KELP_PLANT ||
        block.getType() == Material.KELP ||
        block.getType() == Material.SEAGRASS ||
        block.getType() == Material.TALL_SEAGRASS ||
        block.getType() == Material.POINTED_DRIPSTONE) {
      is_water = true;
    }
    return is_water;
  }
  public static boolean isLavaMaterial(Block block) {
    boolean is_lava = false;
    if (block.getType() == Material.LAVA) {
      is_lava = true;
    }
    return is_lava;
  }

  public static boolean isWoodMaterial(Block block) {
    boolean is_wood = false;
    if (block.getType() == Material.OAK_LOG ||
        block.getType() == Material.SPRUCE_LOG ||
        block.getType() == Material.BIRCH_LOG ||
        block.getType() == Material.ACACIA_LOG ||
        block.getType() == Material.DARK_OAK_LOG ||
        block.getType() == Material.MANGROVE_LOG ||
        block.getType() == Material.JUNGLE_LOG) {
      is_wood = true;
    }
    return is_wood;
  }
}
