package inkohx.xyz.nukkit.kotori

import cn.nukkit.event.EventHandler
import cn.nukkit.event.Listener
import cn.nukkit.event.server.DataPacketReceiveEvent
import cn.nukkit.network.protocol.LoginPacket

class EventListener(internal var plugin: Main) : Listener {
    @EventHandler
    fun Receive(event: DataPacketReceiveEvent) {
        val packet = event.packet
        if (packet is LoginPacket) packet.username = packet.username.replace(' ', '_')
    }
}