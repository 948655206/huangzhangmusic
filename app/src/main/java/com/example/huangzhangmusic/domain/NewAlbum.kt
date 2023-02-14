package com.example.huangzhangmusic.domain

data class NewAlbum(
    val albums: List<Album>,
    val code: Int
) {
    data class Album(
        val alias: List<Any>,
        val artist: Artist,
        val artists: List<Artist>,
        val blurPicUrl: String,
        val briefDesc: String,
        val commentThreadId: String,
        val company: String,
        val companyId: Int,
        val copyrightId: Int,
        val description: String,
        val id: Int,
        val name: String,
        val onSale: Boolean,
        val paid: Boolean,
        val pic: Long,
        val picId: Long,
        val picId_str: String,
        val picUrl: String,
        val publishTime: Long,
        val size: Int,
        val songs: Any,
        val status: Int,
        val tags: String,
        val type: String
    ) {
        data class Artist(
            val albumSize: Int,
            val alias: List<String>,
            val briefDesc: String,
            val id: Int,
            val img1v1Id: Long,
            val img1v1Id_str: String,
            val img1v1Url: String,
            val musicSize: Int,
            val name: String,
            val picId: Long,
            val picId_str: String,
            val picUrl: String,
            val topicPerson: Int,
            val trans: String,
            val transNames: List<String>
        )
    }
}