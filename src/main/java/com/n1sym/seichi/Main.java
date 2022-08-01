package com.n1sym.seichi;

import org.bukkit.plugin.java.JavaPlugin;

import com.n1sym.seichi.command.Stick;
import com.n1sym.seichi.listener.BlockBreak;
import com.n1sym.seichi.listener.InteractClick;
import com.n1sym.seichi.listener.InventoryClick;
import com.n1sym.seichi.listener.Login;
import com.n1sym.seichi.listener.Logout;

public class Main extends JavaPlugin {
  @Override
  public void onEnable() {
    getLogger().info("整地プラグインを有効にしました。");
    this.getCommand("stick").setExecutor(new Stick());
    this.getServer().getPluginManager().registerEvents(new Login(), this);
    this.getServer().getPluginManager().registerEvents(new Logout(), this);
    this.getServer().getPluginManager().registerEvents(new InventoryClick(), this);
    this.getServer().getPluginManager().registerEvents(new InteractClick(), this);
    this.getServer().getPluginManager().registerEvents(new BlockBreak(), this);
  }
}