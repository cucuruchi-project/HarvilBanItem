package com.cucuruchi.banitem.command;

import com.cucuruchi.banitem.inventory.BanItemSettingInventory;
import com.cucuruchi.banitem.inventory.BanitemInventory;
import com.cucuruchi.banitem.message.HelpMessage;
import com.cucuruchi.harvillibrary.extension.ConfigExtension;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.cucuruchi.harvillibrary.extension.MessageExtension.sendMessage;


public class AdminCommand implements CommandExecutor, TabExecutor {
    private final ConfigExtension config;
    private Boolean banItemApply;
    private List<String> banItems;
    private List<String> exceptPlayers;
    private final BanitemInventory banitemInventory;
    private final BanItemSettingInventory banItemSettingInventory;
    public AdminCommand(ConfigExtension config, Boolean banItemApply, List<String> banItems, List<String> exceptPlayers, BanitemInventory banitemInventory, BanItemSettingInventory banItemSettingInventory) {
        this.config = config;
        this.banItemApply = banItemApply;
        this.banItems = banItems;
        this.exceptPlayers = exceptPlayers;
        this.banitemInventory = banitemInventory;
        this.banItemSettingInventory = banItemSettingInventory;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player player)) { return false;}

        if (!player.hasPermission("Banitem.Admin")){
            sendMessage(player, "You don't have Permission (Banitem.Admin)");
            return false;
        }
        if (args.length == 0){
            HelpMessage.send(player);
            return true;
        }

        switch (args[0]){
            case "설정":
                banItemSettingInventory.create(player);
                break;
            case "확인":
                banitemInventory.create(player);
                break;
            case "리로드":
                config.reload();
                sendMessage(player, "config.yml 리로드가 완료되었습니다.");
                break;
            case "플레이어추가":
                if (args.length < 2){
                    sendMessage(player, "&c올바른 명령어를 입력해주세요.");
                    break;
                }
                exceptPlayers.add(args[1]);
                config.set("except-players", exceptPlayers.toArray());
                sendMessage(player, "&c" + args[1] + "님을 예외 플레이어로 등록하였습니다.");
                break;
            case "플레이어제거":
                if (args.length < 2){
                    sendMessage(player, "&c올바른 명령어를 입력해주세요.");
                    break;
                }
                exceptPlayers.remove(args[1]);
                config.set("except-players", exceptPlayers.toArray());
                sendMessage(player, "&c" + args[1] + "님을 예외 플레이어에서 제거하였습니다.");
                break;
            case "플레이어목록":
                sendMessage(player,"제외 플레이어 목록");
                exceptPlayers.forEach(playerName -> sendMessage(player, "플레이어 이름 : " + playerName + "\n"));
                break;
            case "도움말":
                HelpMessage.send(player);
                break;
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        return null;
    }
}
