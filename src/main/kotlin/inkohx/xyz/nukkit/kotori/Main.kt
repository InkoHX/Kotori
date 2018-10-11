package inkohx.xyz.nukkit.kotori

import cn.nukkit.plugin.PluginBase
import inkohx.xyz.nukkit.kotori.anticheat.AntiFly
import inkohx.xyz.nukkit.kotori.anticheat.AntiReach
import inkohx.xyz.nukkit.kotori.anticheat.AntiSpam
import inkohx.xyz.nukkit.kotori.anticheat.AntiToolbox
import inkohx.xyz.nukkit.kotori.serverlist.ServerListAPI
import inkohx.xyz.nukkit.kotori.survival.SurvivalCore
import inkohx.xyz.nukkit.kotori.task.ServerListTask

class Main : PluginBase() {

    internal val api = ServerListAPI()

    override fun onLoad() {
        this.saveDefaultConfig()
    }

    override fun onEnable() {
        if (config.get("plugin-enable") as Boolean) logger.notice("Kotoriをロード :)") else server.pluginManager.disablePlugin(this)
        this.server.loadLevel("survival")
        registerEvents()
        ServerList()
    }

    override fun onDisable() {
        if (!(config.get("serverlist-enable") as Boolean)) logger.notice(api.logout())
        logger.notice("アンロード :(")
    }

    private fun ServerList() {
        if (config.get("serverlist-enable") as Boolean) {
            logger.notice(api.login(config.get("serverlist-token").toString()))
            server.scheduler.scheduleRepeatingTask(ServerListTask(this), 600)
        } else {
            logger.warning("ServerListは使わない設定になっています。")
        }
    }

    private fun registerEvents() {
        server.pluginManager.registerEvents(EventListener(this), this)
        server.pluginManager.registerEvents(AntiSpam(), this)
        server.pluginManager.registerEvents(AntiFly(), this)
        server.pluginManager.registerEvents(AntiToolbox(), this)
        server.pluginManager.registerEvents(AntiReach(), this)
        server.pluginManager.registerEvents(SurvivalCore(), this)
    }
}