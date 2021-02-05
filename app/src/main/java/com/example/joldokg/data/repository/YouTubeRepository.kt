package com.example.joldokg.data.repository

import androidx.lifecycle.liveData
import com.example.joldokg.data.models.PlayListDetail
import com.example.joldokg.data.network.Resource
import com.example.joldokg.data.network.YoutubeApi
import kotlinx.coroutines.Dispatchers

class YouTubeRepository(private var api: YoutubeApi) {
    val part = "snippet,contentDetails"
    val key = "AIzaSyAHFfInYFsMdLO5h8GDO2wm3m84I5_7lyo"
    val channelId = "UCbSFAcyBtP3hp--0PmKF95Q"

    fun fetchPlayLists() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val result = api.fetchPlayLists(part, key, channelId)
            emit(Resource.success(data = result))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error"))
        }
    }

    fun fetchPlayListsItems(playlistId: String, pageToken: String?) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val dataPlayL = fetchDetail(playlistId, pageToken, null)
            emit(Resource.success(data = dataPlayL))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error"))
        }
    }

    suspend fun fetchDetail(
        playlistId: String,
        pageToken: String?,
        data: PlayListDetail?
    ): PlayListDetail? {
        val newData = api.fetchPlayListsItems(part, key, playlistId, pageToken)
        if (data != null) data.items?.let { newData.items?.addAll(it) }
        return if (newData.nextPageToken != null) fetchDetail(playlistId, newData.nextPageToken, data = newData)
        else newData
    }

//    fun loadPlaylist(): PlayList? {
//        return dao.getAll()
//    }
//
//    fun loadDetailPlayList(): PlayListDetail? {
//        return dao.getDetail()
//    }
}