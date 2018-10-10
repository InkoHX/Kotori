package inkohx.xyz.nukkit.kotori.serverlist

import java.io.IOException
import java.util.HashMap
import java.util.concurrent.ExecutionException

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ning.http.client.AsyncHttpClient

import cn.nukkit.Server

class ServerListAPI {

    private var accessToken = ""

    private val maxPlayers = Server.getInstance().maxPlayers

    fun login(token: String): String {
        val data = HashMap<String, String>()
        data["max"] = maxPlayers.toString()
        data["now"] = "0"
        data["type"] = "start"
        data["server_token"] = token
        val ret = this.sendData("update", data)

        data.clear()
        data["state"] = "1"
        data["access_token"] = this.accessToken
        this.sendData("push", data)

        return ret
    }

    fun logout(): String {
        val data = HashMap<String, String>()
        data["type"] = "stop"
        data["access_token"] = this.accessToken
        return this.sendData("update", data)
    }

    fun updatePlayers(type: String): String {
        val data = HashMap<String, String>()
        data["max"] = maxPlayers.toString()
        data["now"] = (Server.getInstance().onlinePlayers.size - if (type == "join") 0 else 1).toString()
        data["type"] = type
        data["access_token"] = this.accessToken
        return this.sendData("update", data)
    }

    fun updateTime(): String {
        val data = HashMap<String, String>()
        data["max"] = maxPlayers.toString()
        data["type"] = "time"
        data["access_token"] = this.accessToken
        return this.sendData("update", data)
    }

    private fun sendData(endPoint: String, data: Map<String, String>): String {
        val client = AsyncHttpClient()
        var ret = ""
        try {
            val builder = client.preparePost("http://api.pmmp.jp.net/$endPoint")
            for (key in data.keys) {
                val value = data[key]
                builder.addFormParam(key, value)
            }
            val responseData = Gson().fromJson<Map<String, String>>(builder.execute().get().getResponseBody("UTF-8"),
                    object : TypeToken<HashMap<String, String>>() {

                    }.type)
            if (responseData != null) {
                if (responseData.containsKey("token")) {
                    this.accessToken = responseData["token"]!!.replace("'", "")
                }

                if (responseData.containsKey("msg")) {
                    ret = responseData["msg"].toString()
                }
            }
        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            client.close()
        }
        return ret
    }
}