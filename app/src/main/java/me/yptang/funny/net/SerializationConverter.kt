package me.yptang.funny.net

import com.drake.net.NetConfig
import com.drake.net.convert.NetConverter
import com.drake.net.exception.ConvertException
import com.drake.net.exception.RequestParamsException
import com.drake.net.exception.ResponseException
import com.drake.net.exception.ServerResponseException
import com.drake.net.request.kType
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject
import java.lang.reflect.Type
import kotlin.reflect.KType

/**
 * 返回结果解析 使用KS
 */
class SerializationConverter(
    private val success: Int = 200,
    private val code: String = "code",
    private val msg: String = "msg"
) : NetConverter {

    companion object {
        val jsonDecoder = Json {
            ignoreUnknownKeys = true // JSON和数据模型字段可以不匹配
            coerceInputValues = true // 如果JSON字段是Null则使用默认值
        }
    }

    override fun <R> onConvert(succeed: Type, response: Response): R? {
        try {
            return NetConverter.onConvert<R>(succeed, response)
        } catch (e: ConvertException) {
            val code = response.code
            when {
                code in 200..299 -> {
                    val bodyString = response.body?.string() ?: return null
                    val ktType = response.request.kType
                        ?: throw ConvertException(response, "Request does not contain KType")
                    return try {
                        val json = JSONObject(bodyString) // 获取JSON中后端定义的错误码和错误信息
                        val srvCode = json.getInt(this.code)
                        if (srvCode == success) {
                            json.getString("data").parseBody<R>(ktType)
                        } else {
                            // 错误码匹配失败, 写入错误异常
                            val errorMessage = json.optString(
                                msg,
                                NetConfig.app.getString(com.drake.net.R.string.no_error_message)
                            )
                            // 将业务错误码作为tag传递
                            throw ResponseException(response, errorMessage, tag = srvCode)
                        }
                    } catch (e: JSONException) { // 固定格式JSON分析失败直接解析JSON
                        bodyString.parseBody<R>(ktType)
                    }
                }
                code in 400..499 -> throw RequestParamsException(
                    response,
                    code.toString()
                ) // 请求参数错误
                code >= 500 -> throw ServerResponseException(response, code.toString()) // 服务器异常错误
                else -> throw ConvertException(response)
            }
        }
    }

    /**
     * json转成entity
     */
    private fun <R> String.parseBody(succeed: KType): R? {
        return jsonDecoder.decodeFromString(Json.serializersModule.serializer(succeed), this) as R
    }
}