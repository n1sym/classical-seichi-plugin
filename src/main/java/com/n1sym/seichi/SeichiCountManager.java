package com.n1sym.seichi;

import java.util.UUID;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.n1sym.seichi.repository.AccountRepository;
import com.n1sym.seichi.repository.SeichiCountRepository;
import com.n1sym.seichi.utils.Metadata;

import net.kyori.adventure.text.Component;

public class SeichiCountManager {
  public static void set(Player player, Integer count) {
    int res[] = calcSeichiLevel(count);
    Integer seichi_level = res[0];
    int rounded_prev_count = res[1];
    int rounded_next_count = res[2];

    Metadata.set(player, "seichi_count", count);
    Metadata.set(player, "seichi_level", seichi_level);
    Metadata.set(player, "prev_count", rounded_prev_count);
    Metadata.set(player, "next_count", rounded_next_count);

    int seichi_skill_level = SeichiSkillManager.calc(seichi_level);
    SeichiSkillManager.set(player, seichi_skill_level);

    // プレイヤー名に整地レベルを表示する
    String str_level = seichi_level.toString();
    String display_name = player.getName();
    player.displayName(Component.text("Lv" + str_level + " " + display_name));
    player.playerListName(Component.text("Lv" + str_level + " " + display_name));
  }

  public static void add(Player player, Integer add_count) {
    int old_seichi_count = player.getMetadata("seichi_count").get(0).asInt();
    int seichi_count = old_seichi_count + add_count;
    Metadata.set(player, "seichi_count", seichi_count);

    int next_count = player.getMetadata("next_count").get(0).asInt();
    int seichi_level = player.getMetadata("seichi_level").get(0).asInt();
    int prev_count = player.getMetadata("prev_count").get(0).asInt();

    // レベルアップ処理
    if (next_count <= seichi_count) {
      SeichiCountManager.set(player, seichi_count);
      next_count = player.getMetadata("next_count").get(0).asInt();
      seichi_level = player.getMetadata("seichi_level").get(0).asInt();

      int seichi_skill_level = SeichiSkillManager.calc(seichi_level);
      SeichiSkillManager.set(player, seichi_skill_level);

      sendSeichiLvUpMessage(player, seichi_level);
    }

    double base = (double)(next_count - prev_count);
    double progress = (double)((seichi_count - prev_count) / base);
    Bar.updateSeichiBar(seichi_level, seichi_count, next_count, player, progress);
  }

  public static void store(Player player){
    UUID uuid = player.getUniqueId();
    try {
      Integer id = AccountRepository.find(uuid);
      Integer count = player.getMetadata("seichi_count").get(0).asInt();
      SeichiCountRepository.set(id, count);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void sendSeichiLvUpMessage(Player player, int level) {
    CommandSender sender = (CommandSender) player;
    sender.sendMessage("整地レベルが上がりました!");
    if (level == 10) {
      sender.sendMessage("範囲破壊レベルが 2 に上がりました!");
    }
    if (level == 20) {
      sender.sendMessage("範囲破壊レベルが 3 に上がりました!");
    }
    if (level == 30) {
      sender.sendMessage("範囲破壊レベルが 4 に上がりました!");
    }
    if (level == 40) {
      sender.sendMessage("範囲破壊レベルが 5 に上がりました!");
    }
  }

  private static int[] calcSeichiLevel(Integer seichi_count) {
    double required_count = 1000.0;
    Integer player_seichi_level = 1;
    double factor = 1.15;
    double total = 1000.0;

    while (total <= seichi_count) {
      required_count *= factor;
      total += required_count;
      player_seichi_level += 1;
    }

    int res[] = new int[2];
    int round_prev_count = (int) (total - required_count);
    int rounded_next_count = (int) total;
    res = new int[] { player_seichi_level, round_prev_count, rounded_next_count };
    return res;
  }

}
