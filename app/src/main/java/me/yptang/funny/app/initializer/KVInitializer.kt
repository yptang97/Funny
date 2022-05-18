package me.yptang.funny.app.initializer

import android.content.Context
import com.tencent.mmkv.MMKV
import me.yptang.funny.app.AppInitializer

object KVInitializer : AppInitializer {
    override fun init(context: Context) {
        MMKV.initialize(context)
    }
}