package inkohx.xyz.nukkit.kotori.survival

import cn.nukkit.Player
import cn.nukkit.Server
import cn.nukkit.event.EventHandler
import cn.nukkit.event.Listener
import cn.nukkit.event.block.BlockBreakEvent
import cn.nukkit.event.entity.EntityLevelChangeEvent
import cn.nukkit.event.player.PlayerJoinEvent
import cn.nukkit.item.Item
import cn.nukkit.level.Position
import java.util.*

class SurvivalCore : Listener {
    fun RandomFood(player: Player) {
        if (player.level.name == "world") return
        if (Random().nextInt(21) == Random().nextInt(21)) {
            player.inventory.addItem(Item(Item.STEAK, 0, 1))
            player.sendTip("§l§aブロックからステーキが出てきた！たまげたなぁ")
        }

    }

    @EventHandler
    fun LevelChange(event: EntityLevelChangeEvent) {
        val entity = event.entity
        if (entity is Player) {
            if (event.target.name == "survival") {
                entity.teleport(Position(Random().nextInt(1000).toDouble(), 255.0, Random().nextInt(1000).toDouble(), Server.getInstance().getLevelByName("survival")))
                entity.sendTitle("§aSTART", "§aSurvival §bNetwork", 40, 60, 40)
            }
        }
    }

    @EventHandler
    fun BlockBreak(event: BlockBreakEvent) {
        val player = event.player
        RandomFood(player)
    }

    @EventHandler
    fun PlayerJoin(event: PlayerJoinEvent) {
    }
}