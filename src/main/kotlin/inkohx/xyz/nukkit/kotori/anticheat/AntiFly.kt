package inkohx.xyz.nukkit.kotori.anticheat

import cn.nukkit.Server
import cn.nukkit.event.EventHandler
import cn.nukkit.event.Listener
import cn.nukkit.event.player.PlayerToggleFlightEvent

class AntiFly : Listener {
    @EventHandler
    fun Flying(event: PlayerToggleFlightEvent) {
        val player = event.player
        if (player.isOp) return
        if (event.isFlying) {
            player.kick(AntiCheat().getBannedReason("Fly"))
            Server.getInstance().nameBans.addBan(player.name, "Fly")
            Server.getInstance().ipBans.addBan(player.address, "Fly")
        } else {
            player.kick(AntiCheat().getBannedReason("Fly"))
            Server.getInstance().nameBans.addBan(player.name, "Fly")
            Server.getInstance().ipBans.addBan(player.address, "Fly")
        }
    }
}