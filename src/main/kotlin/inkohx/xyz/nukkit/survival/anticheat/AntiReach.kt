package inkohx.xyz.nukkit.survival.anticheat

import cn.nukkit.Player
import cn.nukkit.event.EventHandler
import cn.nukkit.event.Listener
import cn.nukkit.event.entity.EntityDamageByEntityEvent
import cn.nukkit.event.entity.EntityDamageEvent
import cn.nukkit.item.Item

class AntiReach : Listener {
    @EventHandler
    fun AntiReach(event: EntityDamageEvent) {
        val entity = event.entity
        if (event is EntityDamageByEntityEvent && entity is Player) {
            val damager = event.damager
            if (damager is Player) {
                if (damager.gamemode == Player.CREATIVE || damager.inventory.itemInHand.id == Item.BOW) return
                if (damager.distance(entity) > 3.9) event.setCancelled()
            }
        }
    }
}