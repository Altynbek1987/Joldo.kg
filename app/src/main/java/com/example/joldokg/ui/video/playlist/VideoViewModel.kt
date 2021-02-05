package com.example.joldokg.ui.video.playlist
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.joldokg.data.models.PlaylistItems
import com.example.joldokg.data.network.Status
import com.example.joldokg.data.repository.YouTubeRepository

class VideoViewModel (var repository: YouTubeRepository): ViewModel() {
    var playlistItems = MutableLiveData<MutableList<PlaylistItems>>()
    var errorMessage = MutableLiveData<String>()

    fun fetchPlaylists() {
        repository.fetchPlayLists().observeForever {
            when (it.status) {
                Status.SUCCESS -> playlistItems.postValue(it.data?.items)
                Status.ERROR -> {
                    errorMessage.postValue(it.message.toString())
                }
            }
        }
    }

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is video Fragment"
//    }
//    val text: LiveData<String> = _text
}