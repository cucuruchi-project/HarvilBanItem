package com.cucuruchi.banitem.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class BanItemInventoryListner implements Listener {

    @EventHandler
    public void InventoryClickEvent(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        ItemStack currentItem = event.getCurrentItem();
        if (currentItem == null) {
            return;
        }
        event.setCancelled(true);
    }
}
