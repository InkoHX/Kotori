package inkohx.xyz.nukkit.kotori

import cn.nukkit.plugin.PluginBase
import inkohx.xyz.nukkit.kotori.serverlist.ServerListAPI
import inkohx.xyz.nukkit.kotori.task.ServerListTask

class Main : PluginBase() {

    internal val api = ServerListAPI()

    override fun onLoad() {
        this.saveDefaultConfig()
    }

    override fun onEnable() {
        if (config.get("plugin-enable") as Boolean) logger.notice("Kotoriをロード :)") else server.pluginManager.disablePlugin(this)
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
            server.scheduler.scheduleRepeatingTask(ServerListTask(this), 20)
        } else {
            logger.warning("ServerListは使わない設定になっています。")
        }
    }

    private fun registerEvents() {
        server.pluginManager.registerEvents(EventListener(this), this)
    }
}