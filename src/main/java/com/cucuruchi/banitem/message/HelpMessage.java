package com.cucuruchi.banitem.message;

import com.cucuruchi.harvillibrary.extension.ColorExtension;
import com.cucuruchi.harvillibrary.extension.MessageExtension;
import com.cucuruchi.harvillibrary.extension.StringExtension;
import org.bukkit.entity.Player;

public class HelpMessage {

    static final String prefix = ColorExtension.process(StringExtension.transChatColor("&f[<GRADIENT:B58AD8> BANITEM </GRADIENT:4D3278>&f]"));

    public static void send(Player player){
    MessageExtension.helpMessage(player, prefix, "금지아이템",
            "설정",
            "확인",
            "리로드",
            "플레이어추가",
            "플레이어제거",
            "플레이어목록",
            "도움말");
    }
}
