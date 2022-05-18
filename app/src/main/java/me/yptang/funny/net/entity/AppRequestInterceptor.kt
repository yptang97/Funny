package me.yptang.funny.net.entity

import android.os.Build
import com.drake.net.interceptor.RequestInterceptor
import com.drake.net.request.BaseRequest
import me.yptang.funny.BuildConfig
import me.yptang.funny.app.AppConfig

/**
 * 添加公共请求头
 */
class AppRequestInterceptor : RequestInterceptor {
    override fun interceptor(request: BaseRequest) = with(request) {
        addHeader("token", AppConfig.token ?: "")
        addHeader("project_token", "B3B6EFC5CA1F4F2FA145B4D7885043E5")
        addHeader("uk", "yptang-funny")
        addHeader("channel", "cretin_open_api")
        addHeader("app", BuildConfig.VERSION_NAME)
        addHeader("device", Build.BRAND + Build.FINGERPRINT)
    }
}