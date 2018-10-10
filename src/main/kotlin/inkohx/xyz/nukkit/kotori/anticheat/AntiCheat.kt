package inkohx.xyz.nukkit.kotori.anticheat

class AntiCheat {
    fun getBannedReason(reason: String): String {
        return "§4[チート検出システム]\n§cあなたはシステムにBANされました。\n§e理由: $reason \n§7もしこれが間違いならば報告して下さい。\n§7Discord: https://discord.gg/EF2G5dh"
    }
}