package inkohx.xyz.nukkit.kotori

import cn.nukkit.plugin.PluginBase

class Main : PluginBase() {
    override fun onLoad() {
        this.saveDefaultConfig()
    }

    override fun onEnable() {
        server.pluginManager.registerEvents(EventListener(this), this)
        if (config.get("enable") as Boolean) logger.notice("ロード :)") else server.pluginManager.disablePlugin(this)
    }

    override fun onDisable() {
        logger.notice("アンロード :(")
    }
}