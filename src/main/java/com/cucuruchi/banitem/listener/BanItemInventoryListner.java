package com.cucuruchi.banitem.listener;

import com.cucuruchi.banitem.inventory.BanitemInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class BanItemInventoryListner implements Listener {

    @EventHandler
    public void InventoryClickEvent(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Inventory inv = event.getInventory();
        if (inv == null || !(inv.getHolder() instanceof BanitemInventory)){
            return;
        }
        ItemStack currentItem = event.getCurrentItem();
        event.setCancelled(true);
        if (currentItem == null) { return; }
    }
}
