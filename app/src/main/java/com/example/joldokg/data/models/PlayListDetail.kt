package com.example.joldokg.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PlayListDetail(

    @SerializedName("id")
    var id: Long? = null,

    @SerializedName("nextPageToken")
    var nextPageToken: String? = null,

    @SerializedName("items")
    var items: MutableList<DetailVideo>? = null

) : Serializable

data class DetailVideo(

    @SerializedName("id")
    var id: String? = null,

    @SerializedName("snippet")
    var snippet: Snippet? = null

) : Serializable

data class ResourceId(

    @SerializedName("videoId")
    var videoId: String? = null

) : Serializable