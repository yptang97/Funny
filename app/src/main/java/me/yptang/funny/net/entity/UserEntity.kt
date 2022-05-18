package me.yptang.funny.net.entity

import kotlinx.serialization.Serializable


@Serializable
data class UserEntity(
    val avatar: String,
    val fansNum: String = "",
    val isAttention: Boolean,
    val jokesNum: String,
    val nickname: String,
    val userId: Int
)