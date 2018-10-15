package inkohx.xyz.nukkit.survival.survival

import cn.nukkit.Player
import cn.nukkit.Server
import cn.nukkit.block.Block
import cn.nukkit.event.EventHandler
import cn.nukkit.event.Listener
import cn.nukkit.event.block.BlockBreakEvent
import cn.nukkit.event.entity.EntityLevelChangeEvent
import cn.nukkit.event.player.PlayerInteractEvent
import cn.nukkit.event.player.PlayerJoinEvent
import cn.nukkit.item.Item
import cn.nukkit.level.Position
import cn.nukkit.potion.Effect
import inkohx.xyz.nukkit.survival.Main
import inkohx.xyz.nukkit.survival.task.JoinWindowTask
import java.util.*

class SurvivalCore(private val main: Main) : Listener {
    private fun RandomFood(player: Player) {
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
            if (event.target.name == "survival") entity.setGamemode(Player.SURVIVAL) else entity.setGamemode(Player.ADVENTURE)
        }
    }

    @EventHandler
    fun BlockBreak(event: BlockBreakEvent) {
        val player = event.player
        RandomFood(player)
    }

    @EventHandler
    fun Tap(event: PlayerInteractEvent) {
        val player = event.player
        if (event.block.id == Block.DIAMOND_BLOCK) {
            player.addEffect(Effect.getEffect(Effect.REGENERATION).setDuration(30 * 20).setAmplifier(254))
            player.addEffect(Effect.getEffect(Effect.DAMAGE_RESISTANCE).setDuration(30 * 20).setAmplifier(254))
            player.teleport(Position((Math.random() * 5000), 255.0, (Math.random() * 5000), Server.getInstance().getLevelByName("survival")))
            player.sendTitle("§aSTART", "§aSurvival §bNetwork", 40, 60, 40)
        }
    }

    @EventHandler
    fun Join(event: PlayerJoinEvent) {
        val player = event.player
        main.server.scheduler.scheduleDelayedTask(JoinWindowTask(main, player), 60)
    }
}