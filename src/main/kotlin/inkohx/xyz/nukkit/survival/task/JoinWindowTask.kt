package inkohx.xyz.nukkit.survival.task

import cn.nukkit.Player
import cn.nukkit.form.window.FormWindowSimple
import cn.nukkit.scheduler.PluginTask
import inkohx.xyz.nukkit.survival.Main

class JoinWindowTask(owner: Main, private val player: Player) : PluginTask<Main>(owner) {
    override fun onRun(currentTick: Int) {
        player.showFormWindow(FormWindowSimple("Welcome", "ようこそサバイバルサーバーへ\nこのサーバーはVectorNetworkProjectによって開発されおりサーバーソフトはNukkitプラグインはKotlinで書いています。\n§c注意: このサーバーはVectorNetwork公開まで開かれるサーバーでありこれがメインゲームではありません。§r\n\nこのサーバーはなんなの？\nサバイバルサーバーです。Mobが沸いたり新アイテム、エンチャント、が使用可能になっています。\n\nFeedback\nこのサーバーはテスト中です。いずれVectorNetworkのゲームとして追加される予定です。プレイした感想や意見を報告してください。\n\nWebsite: https://vector-network.tk\nDiscord: https://discord.gg/EF2G5dh"))
    }
}