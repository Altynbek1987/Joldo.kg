package com.example.joldokg.ui.video.detailvideo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.joldokg.data.models.DetailVideo
import com.example.joldokg.data.models.PlayListDetail
import com.example.joldokg.data.network.Resource
import com.example.joldokg.data.repository.YouTubeRepository

class DetailVideoViewModel (var repository: YouTubeRepository): ViewModel() {
    fun fetchPlayListsItems(
        playlistId: String,
        pageToken: String?
    ): LiveData<Resource<PlayListDetail?>> {
        return repository.fetchPlayListsItems(playlistId, pageToken)
    }
}