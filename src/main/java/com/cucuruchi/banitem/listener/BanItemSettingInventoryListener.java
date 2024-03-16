package com.cucuruchi.banitem.listener;

import com.cucuruchi.harvillibrary.extension.ConfigExtension;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
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
    public void onInventoryCloseEvent(InventoryCloseEvent e){
        Player player = (Player) e.getPlayer();
        ItemStack[] itemType = e.getInventory().getContents();
        int position = 0;
        for (ItemStack itemStack : itemType) {
            banItems.clear();
            banItems.add(itemStack.getType().name());
        }
        config.set("banitems", banItems.toArray());
        sendMessage(player, "밴 아이템 설정 완료");
    }
}
