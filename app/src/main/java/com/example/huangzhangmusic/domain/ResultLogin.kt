package com.example.huangzhangmusic.domain

data class ResultLogin(
    val account: Account,
    val bindings: List<Binding>,
    val code: Int,
    val cookie: String,
    val loginType: Int,
    val token: String
)

data class Account(
    val anonimousUser: Boolean,
    val ban: Int,
    val baoyueVersion: Int,
    val createTime: Long,
    val donateVersion: Int,
    val id: Long,
    val salt: String,
    val status: Int,
    val tokenVersion: Int,
    val type: Int,
    val uninitialized: Boolean,
    val userName: String,
    val vipType: Int,
    val viptypeVersion: Int,
    val whitelistAuthority: Int
)

data class Binding(
    val bindingTime: Int,
    val expired: Boolean,
    val expiresIn: Int,
    val id: Long,
    val refreshTime: Int,
    val tokenJsonStr: String,
    val type: Int,
    val url: String,
    val userId: Long
)