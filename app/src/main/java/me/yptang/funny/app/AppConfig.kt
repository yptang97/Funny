package me.yptang.funny.app

import com.drake.serialize.serialize.serial

/**
 * APP配置信息
 */
object AppConfig {

    /** 登录信息token */
    var token: String? by serial()

}