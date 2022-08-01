package com.n1sym.seichi;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import net.kyori.adventure.text.Component;

public class CustomMenu {
  public static void openCustomMenu(Player player) {
    Inventory inventory = Bukkit.createInventory(new SeichiMenu(), InventoryType.CHEST, Component.text("My custom メニュー!"));
    addMypageButton(inventory, player);
    addToolButton(inventory);
    addScafButton(inventory);
    addTorchButton(inventory);
    player.openInventory(inventory);
  }

  public static void addMypageButton(Inventory inventory, Player player){
    ItemStack item = new ItemStack(Material.PLAYER_HEAD);
    SkullMeta skull_meta = (SkullMeta)item.getItemMeta();
    OfflinePlayer op = (OfflinePlayer)player;
    skull_meta.setOwningPlayer(op);
    item.setItemMeta(skull_meta);

    ItemMeta meta = item.getItemMeta();
    int seichi_skill_level = SeichiSkillManager.get(player);
    List<Component> lores = new ArrayList<Component>(){
      {
        add(Component.text("マイページ"));
        add(Component.text("範囲破壊レベル: " + seichi_skill_level));
      }
    };

    meta.lore(lores);
    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    item.setItemMeta(meta);
    inventory.addItem(item);
  }

  public static void addToolButton(Inventory inventory){
    ItemStack scaf = new ItemStack(Material.STONE_PICKAXE);
    ItemMeta meta = scaf.getItemMeta();
    List<Component> lores = new ArrayList<Component>(){
      {
        add(Component.text("整地ツールセットを取得"));
      }
    };
    meta.lore(lores);
    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    scaf.setItemMeta(meta);
    inventory.addItem(scaf);
  }

  public static void addScafButton(Inventory inventory){
    ItemStack scaf = new ItemStack(Material.SCAFFOLDING);
    ItemMeta meta = scaf.getItemMeta();
    List<Component> lores = new ArrayList<Component>(){
      {
        add(Component.text("足場ブロックを取得"));
      }
    };
    meta.lore(lores);
    scaf.setItemMeta(meta);
    inventory.addItem(scaf);
  }

  public static void addTorchButton(Inventory inventory){
    ItemStack item = new ItemStack(Material.TORCH);
    ItemMeta meta = item.getItemMeta();
    List<Component> lores = new ArrayList<Component>(){
      {
        add(Component.text("松明を取得"));
      }
    };
    meta.lore(lores);
    item.setItemMeta(meta);
    inventory.addItem(item);
  }  
}
