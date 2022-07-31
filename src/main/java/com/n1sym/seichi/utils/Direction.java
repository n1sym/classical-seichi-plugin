package com.n1sym.seichi.utils;

import org.bukkit.entity.Player;

public class Direction {
  public static boolean isLookingDownward(Player player){
    double rotation = (player.getLocation().getPitch()) % 360.0F;
    boolean is_downward = false;
    if (rotation > 80) {
      is_downward = true;
    }
    return is_downward;
  }
  public static String getCardinalDirection(Player player) {
    double rotation = (player.getLocation().getYaw() - 90.0F) % 360.0F;
    if (rotation < 0.0D) {
        rotation += 360.0D;
    }
    if ((0.0D <= rotation) && (rotation < 45D)) {
        return "N";
    }
    if ((45D <= rotation) && (rotation < 135D)) {
        return "E";
    }
    if ((135D <= rotation) && (rotation < 225D)) {
        return "S";
    }
    if ((225D <= rotation) && (rotation < 315D)) {
        return "W";
    }
    if ((315D <= rotation) && (rotation < 360.0D)) {
        return "N";
    }
    return null;
  }
}
