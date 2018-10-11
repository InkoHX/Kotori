package inkohx.xyz.nukkit.kotori

import cn.nukkit.event.EventHandler
import cn.nukkit.event.Listener
import cn.nukkit.event.player.PlayerJoinEvent
import cn.nukkit.event.player.PlayerLoginEvent
import cn.nukkit.event.player.PlayerQuitEvent
import cn.nukkit.event.server.DataPacketReceiveEvent
import cn.nukkit.network.protocol.LoginPacket
import cn.nukkit.utils.DummyBossBar

class EventListener(private val plugin: Main) : Listener {
    @EventHandler
    fun Receive(event: DataPacketReceiveEvent) {
        val packet = event.packet
        if (packet is LoginPacket) {
            if (!(plugin.config.get("player-namefix") as Boolean)) return
            packet.username = packet.username.replace(' ', '_')
        }
    }

    @EventHandler
    fun Join(event: PlayerJoinEvent) {
        if (!(plugin.config.get("serverlist-enable") as Boolean)) plugin.api.updatePlayers("join")
        event.player.createBossBar(DummyBossBar.Builder(event.player).length(100F).text("§l§aサバイバル").color(255, 255, 0).build())
    }

    @EventHandler
    fun Quit(event: PlayerQuitEvent) {
        if (!(plugin.config.get("serverlist-enable") as Boolean)) plugin.api.updatePlayers("quit")
    }

    @EventHandler
    fun Login(event: PlayerLoginEvent) {
        val player = event.player
        player.setCheckMovement(false)
    }
}