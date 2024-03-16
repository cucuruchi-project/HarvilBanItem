package com.cucuruchi.banitem.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class BanItemListner implements Listener {

    @EventHandler
    public void onInteractItem(PlayerInteractEvent e){
        Player player = e.getPlayer();
    }
}
