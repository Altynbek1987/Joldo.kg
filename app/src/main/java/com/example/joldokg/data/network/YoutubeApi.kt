package com.example.joldokg.data.network

import com.example.joldokg.data.models.PlayList
import com.example.joldokg.data.models.PlayListDetail
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApi {
    @GET("youtube/v3/playlists")
    suspend fun fetchPlayLists(
        @Query("part") part: String,
        @Query("key") key: String,
        @Query("channelId") channelId: String
    ): PlayList

    @GET("youtube/v3/playlistItems")
    suspend fun fetchPlayListsItems(
        @Query("part") part: String,
        @Query("key") key: String,
        @Query("playlistId") playlistId: String,
        @Query("pageToken") pageToken: String?
    ): PlayListDetail
}