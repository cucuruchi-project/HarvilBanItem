package com.cucuruchi.banitem.listener;

import com.cucuruchi.banitem.inventory.BanitemSettingInventory;
import com.cucuruchi.harvillibrary.extension.ConfigExtension;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

import static com.cucuruchi.harvillibrary.extension.MessageExtension.sendMessage;

public class BanItemSettingInventoryListener implements Listener {

    private final ConfigExtension config;
    private List<String> banItems;
    public BanItemSettingInventoryListener(ConfigExtension config, List<String> banItems) {
        this.config = config;
        this.banItems = banItems;
    }

    @EventHandler
    public void onInventoryCloseEvent(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();
        Inventory inv = event.getInventory();
        if (inv == null || !(inv.getHolder() instanceof BanitemSettingInventory banitemSettingInventory)){
            return;
        }
        banItems.clear();
        ItemStack[] itemType = banitemSettingInventory.getInventory().getContents();
        for (int i = 0; i < banitemSettingInventory.getInventory().getSize(); i++) {
            if (itemType[i] != null){
                banItems.add(String.valueOf(itemType[i].getType()));
            }
        }
        config.set("banitems", banItems.toArray());
        config.save();
        sendMessage(player, "밴 아이템 설정 완료");
    }
}
