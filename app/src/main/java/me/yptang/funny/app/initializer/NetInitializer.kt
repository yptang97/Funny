package me.yptang.funny.app.initializer

import android.content.Context
import com.drake.net.NetConfig
import com.drake.net.interceptor.LogRecordInterceptor
import com.drake.net.okhttp.setConverter
import com.drake.net.okhttp.setDebug
import com.drake.net.okhttp.setDialogFactory
import com.drake.net.okhttp.setRequestInterceptor
import com.drake.tooltip.dialog.BubbleDialog
import me.yptang.funny.BuildConfig
import me.yptang.funny.app.AppInitializer
import me.yptang.funny.net.SerializationConverter
import me.yptang.funny.net.entity.AppRequestInterceptor
import okhttp3.Cache
import java.util.concurrent.TimeUnit

object NetInitializer : AppInitializer {

    private const val host = "http://tools.cretinzp.com/jokes"
    private const val cacheSize: Long = 1024 * 1024 * 128

    override fun init(context: Context) {
        NetConfig.initialize(host = host, context = context) {

            // 超时设置
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)

            // 缓存设置, 当超过maxSize最大值会根据最近最少使用算法清除缓存来限制缓存大小
            cache(Cache(context.cacheDir, cacheSize))

            // LogCat是否输出异常日志, 异常日志可以快速定位网络请求错误
            setDebug(BuildConfig.DEBUG)

            // AndroidStudio OkHttp Profiler 插件输出网络日志
            if (BuildConfig.DEBUG) {
                addInterceptor(LogRecordInterceptor(BuildConfig.DEBUG))
            }

            // 添加请求拦截器, 可配置全局/动态参数
            setRequestInterceptor(AppRequestInterceptor())

            // 数据转换器
            setConverter(SerializationConverter())

            // 自定义全局加载对话框
            setDialogFactory {
                BubbleDialog(it, "加载中....")
            }
        }
    }

}