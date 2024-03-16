package com.cucuruchi.banitem.inventory;

import com.cucuruchi.banitem.utility.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ExceptPlayerInventory implements InventoryHolder {

    private final List<String> exceptPlayers;
    private final Inventory inventory;
    public ExceptPlayerInventory(List<String> exceptPlayers) {
        this.exceptPlayers = exceptPlayers;
        this.inventory = Bukkit.createInventory(this, 54, "EXCEPT-PLAYER-LIST");
        if (!this.exceptPlayers.isEmpty()){
            try {
                exceptPlayers.forEach(player -> inventory.setItem(this.exceptPlayers.indexOf(player), ItemUtil.createPlayerHead(player)));
            } catch (NullPointerException e){
                Bukkit.getLogger().warning(e.getMessage());
            }
        }

    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
}
