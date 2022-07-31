package com.n1sym.seichi;

import org.bukkit.entity.Player;

import com.n1sym.seichi.utils.Metadata;

public class SeichiSkillManager {
  public static int get(Player player) {
    int skill_level = player.getMetadata("seichi_skill_level").get(0).asInt();
    return skill_level;
  }

  public static void set(Player player, Integer level) {
    Metadata.set(player, "seichi_skill_level", level);
  }

  public static int calc(int seichi_level){
    return (seichi_level / 10) + 1;
  }
}
