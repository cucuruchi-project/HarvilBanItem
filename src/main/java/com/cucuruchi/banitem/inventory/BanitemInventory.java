package com.cucuruchi.banitem.inventory;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BanitemInventory implements InventoryHolder {

    private List<String> banitems;
    private final Inventory inventory;

    public BanitemInventory(List<String> banitems) {
        this.banitems = banitems;
        this.inventory = Bukkit.createInventory(this, 54, "BANITEM-LIST");
        if (!this.banitems.isEmpty()){
            try {
                banitems.forEach(item -> inventory.setItem(this.banitems.indexOf(item), new ItemStack(Material.getMaterial(item))));
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