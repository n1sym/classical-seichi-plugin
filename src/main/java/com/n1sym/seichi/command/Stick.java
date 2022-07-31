package com.n1sym.seichi.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.n1sym.seichi.Tools;

public class Stick implements CommandExecutor {

  /**
   * @param sender コマンドの送信者
   * @param command コマンドのクラス
   * @param label コマンド名
   * @param args コマンドの引数
   */
  
  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (args.length > 0) {
      return false;
    }

    Player player = null;
	  if (sender instanceof Player) {
	  	player = (Player) sender;
      Tools.getStick(player);
    }
    
    return true;
  }
}