package inkohx.xyz.nukkit.kotori.anticheat

import cn.nukkit.Server
import cn.nukkit.event.EventHandler
import cn.nukkit.event.Listener
import cn.nukkit.event.server.DataPacketReceiveEvent
import cn.nukkit.network.protocol.LoginPacket

class AntiToolbox : Listener {
    @EventHandler
    fun AntiToolbox(event: DataPacketReceiveEvent) {
        val packet = event.packet
        if (packet is LoginPacket && packet.clientId.toInt() == 0) {
            event.setCancelled()
            Server.getInstance().nameBans.addBan(packet.username.replace(' ', '_'), "Toolbox")
        }
    }
}