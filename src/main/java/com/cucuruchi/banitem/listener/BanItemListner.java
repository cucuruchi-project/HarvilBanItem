package com.cucuruchi.banitem.listener;

import com.cucuruchi.harvillibrary.extension.ColorExtension;
import com.cucuruchi.harvillibrary.extension.StringExtension;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.List;

import static com.cucuruchi.harvillibrary.extension.MessageExtension.sendMessage;

public class BanItemListner implements Listener {

    private final List<String> banitems;
    private final List<String> exceptPlayers;
    private final String prefix = ColorExtension.process(StringExtension.transChatColor("&f[<GRADIENT:B58AD8> 금지아이템 </GRADIENT:4D3278>&f]"));
    public BanItemListner(List<String> banitems, List<String> exceptPlayers) {
        this.banitems = banitems;
        this.exceptPlayers = exceptPlayers;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        Player player = event.getPlayer();
        if (banitems.contains(event.getBlock().getType().toString())){
            if (exceptPlayers.contains(player.getName())){
                return;
            }
            event.setCancelled(true);
            player.getInventory().remove(event.getBlock().getType());
            sendMessage(player, prefix + "  금지아이템은 설치할 수 없습니다. (블럭 : &e" + event.getBlock().getType() + " &f)");
        }
    }
    
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (banitems.contains(event.getBlock().getType().toString())){
            if (exceptPlayers.contains(player.getName())){
                return;
            }
            event.setCancelled(true);
            player.getInventory().remove(event.getBlock().getType());
            sendMessage(player, prefix + "  금지아이템은 파괴할 수 없습니다. (블럭 : &e" + event.getBlock().getType() + " &f)");
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (banitems.contains(event.getItem().getType().toString())) {
                if (exceptPlayers.contains(player.getName())) {
                    return;
                }
                event.setCancelled(true);
                player.getInventory().remove(event.getItem().getType());
                sendMessage(player, prefix + "  금지아이템은 사용할 수 없습니다. (아이템 : &e" + event.getItem().getType() + " &f)");
            }
        }
    }

    @EventHandler
    public void onCraftingItem(CraftItemEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (banitems.contains(event.getRecipe().getResult().getType().toString())) {
            if (exceptPlayers.contains(player.getName())) {
                return;
            }
            event.setCancelled(true);
            player.closeInventory();
            sendMessage(player, prefix + "  금지아이템은 조합할 수 없습니다. (아이템 : &e" + event.getRecipe().getResult().getType() + " &f)");
        }
    }

}


