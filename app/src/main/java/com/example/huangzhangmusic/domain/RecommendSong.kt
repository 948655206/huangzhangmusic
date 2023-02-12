package com.example.huangzhangmusic.domain


/**
 * 推荐歌单--data
 */
data class RecommendSong(
    val category: Int,
    val code: Int,
    val hasTaste: Boolean,
    val result: List<Result>
) {
    data class Result(
        val alg: String,
        val canDislike: Boolean,
        val copywriter: String,
        val highQuality: Boolean,
        val id: Long,
        val name: String,
        val picUrl: String,
        val playCount: Int,
        val trackCount: Int,
        val trackNumberUpdateTime: Long,
        val type: Int
    )
}