package me.yptang.funny.app

import android.app.Application
import me.yptang.funny.app.initializer.KVInitializer
import me.yptang.funny.app.initializer.NetInitializer

class App : Application() {

    /**l
     * **为保证合规问题，在用户未同意相关隐私政策之前
     * 请不要提前初始化某些手机用户信息的库，如友盟**
     */
    private val initializer = mutableListOf<AppInitializer>()

    override fun onCreate() {
        super.onCreate()

        initializer.apply {
            add(NetInitializer)
            add(KVInitializer)

            forEach { it.init(this@App) }
        }
    }

}