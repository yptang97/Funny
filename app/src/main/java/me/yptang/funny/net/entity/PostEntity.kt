package me.yptang.funny.net.entity

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames
import me.yptang.funny.tools.AESCrypt


@Serializable
data class PostEntity(
    val info: Info,
    @JsonNames("joke")
    val post: Joke,
    @JsonNames("user")
    val author: User
) {
    @Serializable
    data class Info(
        val commentNum: Int,
        val disLikeNum: Int,
        val isAttention: Boolean,
        val isLike: Boolean,
        val isUnlike: Boolean,
        val likeNum: Int,
        val shareNum: Int
    )

    @Serializable
    data class Joke(
        val addTime: String,
        @JsonNames("audit_msg")
        val auditMsg: String,
        val content: String,
        val hot: Boolean,
        val imageSize: String,
        val imageUrl: String, // type=2 此值有数据，多图用,分割，最多9张图
        val jokesId: Int,
        val latitudeLongitude: String,
        val showAddress: String,
        private val thumbUrl: String,
        val type: Int, // 段子类型 1 文本 2 图片 >=3 视频
        val userId: Int,
        val videoSize: String,
        val videoTime: Int,
        private val videoUrl: String
    ) {
        fun getThumbUrl() = thumbUrl.replace("ftp://", "").decrypt()
        fun getVideoUrl() = videoUrl.replace("ftp://", "").decrypt()
    }

    @Serializable
    data class User(
        val avatar: String,
        val nickName: String,
        val signature: String,
        val userId: Int
    )
}

private fun String.decrypt() = AESCrypt.decrypt(this)