package inkohx.xyz.nukkit.survival

import cn.nukkit.plugin.PluginBase
import inkohx.xyz.nukkit.survival.anticheat.AntiFly
import inkohx.xyz.nukkit.survival.anticheat.AntiReach
import inkohx.xyz.nukkit.survival.anticheat.AntiSpam
import inkohx.xyz.nukkit.survival.anticheat.AntiToolbox
import inkohx.xyz.nukkit.survival.serverlist.ServerListAPI
import inkohx.xyz.nukkit.survival.survival.SurvivalCore
import inkohx.xyz.nukkit.survival.task.ServerListTask

class Main : PluginBase() {

    internal val api = ServerListAPI()

    override fun onLoad() {
        this.saveDefaultConfig()
    }

    override fun onEnable() {
        if (config.get("plugin-enable") as Boolean) logger.notice("ロード :)") else server.pluginManager.disablePlugin(this)
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