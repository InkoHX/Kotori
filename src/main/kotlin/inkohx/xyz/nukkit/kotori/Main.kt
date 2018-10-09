package inkohx.xyz.nukkit.kotori

import cn.nukkit.plugin.PluginBase

class Main : PluginBase() {
    override fun onEnable() {
        server.pluginManager.registerEvents(EventListener(this), this)
        logger.notice("ロード :)")
    }

    override fun onDisable() {
        logger.notice("アンロード :(")
    }
}