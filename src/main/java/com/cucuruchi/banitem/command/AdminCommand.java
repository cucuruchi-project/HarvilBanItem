package com.cucuruchi.banitem.command;

import com.cucuruchi.banitem.inventory.BanitemInventory;
import com.cucuruchi.banitem.inventory.BanitemSettingInventory;
import com.cucuruchi.banitem.message.HelpMessage;
import com.cucuruchi.harvillibrary.extension.ConfigExtension;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

import static com.cucuruchi.harvillibrary.extension.MessageExtension.sendMessage;


public class AdminCommand implements CommandExecutor, TabExecutor {
    private final ConfigExtension config;
    private Boolean banItemApply;
    private List<String> banItems;
    private List<String> exceptPlayers;

    public AdminCommand(ConfigExtension config, Boolean banItemApply, List<String> banItems, List<String> exceptPlayers) {
        this.config = config;
        this.banItemApply = banItemApply;
        this.banItems = banItems;
        this.exceptPlayers = exceptPlayers;
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
                BanitemSettingInventory banItemSettingInventory = new BanitemSettingInventory(banItems);
                player.openInventory(banItemSettingInventory.getInventory());
                break;
            case "확인":
                BanitemInventory banitemInventory = new BanitemInventory(banItems);
                player.openInventory(banitemInventory.getInventory());
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
        return switch (args[0]){
            case "" -> List.of("설정", "확인", "리로드", "플레이어추가", "플레이어제거", "플레이어목록", "도움말");
            case "플레이어추가" -> List.of(Bukkit.getOnlinePlayers().stream().map(Player::getName).toArray(String[]::new));
            case "플레이어제거" -> exceptPlayers;
            default -> Collections.emptyList();
        };
    }
}
