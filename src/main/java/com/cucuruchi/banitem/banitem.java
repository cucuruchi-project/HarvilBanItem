package com.cucuruchi.banitem;

import com.cucuruchi.banitem.command.AdminCommand;
import com.cucuruchi.banitem.inventory.BanitemInventory;
import com.cucuruchi.banitem.inventory.BanitemSettingInventory;
import com.cucuruchi.banitem.listener.BanItemInventoryListner;
import com.cucuruchi.banitem.listener.BanItemSettingInventoryListener;
import com.cucuruchi.harvillibrary.extension.ConfigExtension;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class banitem extends JavaPlugin {
    @Override
    public void onEnable() {

        ConfigExtension config = new ConfigExtension(this, this.getDataFolder().getPath(), "config.yml");
        Boolean banItemApply = config.getBoolean("banitem-apply");
        List<String> banItems = config.getStringList("banitems");
        List<String> exceptPlayers = config.getStringList("except-players");


        registerCommand("금지아이템", new AdminCommand(config, banItemApply, banItems, exceptPlayers));
        getServer().getPluginManager().registerEvents(new BanItemInventoryListner(), this);
        getServer().getPluginManager().registerEvents(new BanItemSettingInventoryListener(config, banItems), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerCommand(String commandName, CommandExecutor className){
        getCommand(commandName).setExecutor(className);
        getCommand(commandName).setTabCompleter((TabCompleter) className);
    }
}
