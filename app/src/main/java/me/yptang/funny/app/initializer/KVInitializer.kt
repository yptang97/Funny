package me.yptang.funny.app.initializer

import com.drake.engine.base.app
import com.tencent.mmkv.MMKV
import me.yptang.funny.app.AppInitializer

object KVInitializer : AppInitializer {
    override fun init() {
        MMKV.initialize(app)
    }
}