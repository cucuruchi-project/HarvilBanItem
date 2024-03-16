package com.cucuruchi.banitem.utility;

import com.cucuruchi.harvillibrary.extension.StringExtension;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemUtil {

    public static ItemStack createPlayerHead(String player){
        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        skullMeta.setDisplayName(StringExtension.transChatColor("&a" + player));
        skullMeta.setOwningPlayer(Bukkit.getOfflinePlayer(player));
        itemStack.setItemMeta(skullMeta);
        return itemStack;
    }
}
