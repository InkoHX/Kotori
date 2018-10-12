package inkohx.xyz.nukkit.survival.anticheat

/*
 * 参考にしたプラグイン
 * https://github.com/haniokasai/SPAMBAN
 */

import java.util.HashMap

import cn.nukkit.Server
import cn.nukkit.event.EventHandler
import cn.nukkit.event.Listener
import cn.nukkit.event.player.PlayerChatEvent
import cn.nukkit.event.player.PlayerCommandPreprocessEvent

class AntiSpam : Listener {
    companion object {
        private val lc = HashMap<String, Int>()
        private val spam = HashMap<String, Int>()
        private var ctime = (System.currentTimeMillis() / 1000).toInt()
    }

    @EventHandler
    fun AntiSpamCommand(event: PlayerCommandPreprocessEvent) {
        val player = event.player
        val name = player.name
        ctime = (System.currentTimeMillis() / 1000).toInt()
        if (lc.containsKey(name)) {
            when {
                !spam.containsKey(name) -> {
                    spam[name] = 0
                    lc[name] = ctime
                }
                ctime - lc[name]!! > 5 -> {
                    spam.remove(name)
                    lc[name] = ctime
                }
                ctime - lc[name]!! <= 3 -> {
                    val i = spam[name]!! + 1
                    spam[name] = i
                    lc[name] = ctime
                }
            }
            if (spam.containsKey(name) && spam[name]!! > 15) {
                player.kick(AntiCheat().getBannedReason("スパム"), false)
                Server.getInstance().ipBans.addBan(player.address, "スパム")
                Server.getInstance().nameBans.addBan(player.name, "スパム")
            }
        } else lc[name] = ctime
    }

    @EventHandler
    fun AntiSpamChat(event: PlayerChatEvent) {
        val player = event.player
        val name = player.name
        ctime = (System.currentTimeMillis() / 1000).toInt()
        if (lc.containsKey(name)) {
            when {
                !spam.containsKey(name) -> {
                    spam[name] = 0
                    lc[name] = ctime
                }
                ctime - lc[name]!! > 5 -> {
                    spam.remove(name)
                    lc[name] = ctime
                }
                ctime - lc[name]!! <= 3 -> {
                    val i = spam[name]!! + 1
                    spam[name] = i
                    lc[name] = ctime
                }
            }
            if (spam.containsKey(name) && spam[name]!! > 15) {
                player.kick(AntiCheat().getBannedReason("スパム"), false)
                Server.getInstance().ipBans.addBan(player.address, "スパム")
                Server.getInstance().nameBans.addBan(player.name, "スパム")
            }
        } else lc[name] = ctime
    }
}