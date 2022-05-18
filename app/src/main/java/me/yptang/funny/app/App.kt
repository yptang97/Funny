package me.yptang.funny.app

import android.app.Application
import com.drake.engine.base.Engine
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

        // 全局持有app 供需使用
        Engine.initialize(this)

        initializer.apply {
            add(NetInitializer)
            add(KVInitializer)

            forEach { it.init() }
        }
    }

}