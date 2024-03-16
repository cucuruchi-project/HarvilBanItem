package com.cucuruchi.banitem.inventory;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class BanitemInventory {

    private List<String> banItems;
    public BanitemInventory(List<String> banItems) {
        this.banItems = banItems;
    }

    public void create(Player player){
        Inventory inventory = Bukkit.createInventory(player, 54, "BANITEM-LIST");
        if (banItems.isEmpty()){
            return;
        }
        try {
            banItems.forEach(item -> inventory.setItem(banItems.indexOf(item), new ItemStack(Material.getMaterial(item))));
        } catch (NullPointerException e){
            Bukkit.getLogger().warning(e.getMessage());
        }
    }
}
