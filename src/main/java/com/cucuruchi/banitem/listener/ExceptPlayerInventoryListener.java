package com.cucuruchi.banitem.listener;

import com.cucuruchi.banitem.inventory.ExceptPlayerInventory;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class ExceptPlayerInventoryListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inv = event.getClickedInventory();
        if (inv == null ||!(inv.getHolder() instanceof ExceptPlayerInventory)) {
            return;
        }
        event.setCancelled(true);
    }
}
