package com.example.joldokg.main

import com.example.joldokg.data.network.RetrofitClient
import com.example.joldokg.data.repository.YouTubeRepository
import com.example.joldokg.ui.pdd.PDDViewModel
import com.example.joldokg.ui.signs.RoadSignsViewModel
import com.example.joldokg.ui.fines.FinesViewModel
import com.example.joldokg.ui.video.detailvideo.DetailVideoViewModel
import com.example.joldokg.ui.video.playlist.VideoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

var mainModule: Module = module {
    single { RetrofitClient() }
    single { RetrofitClient().instanceRetrofit() }
    single { YouTubeRepository(get()) }
}


var viewModelModule = module {
    viewModel { VideoViewModel(get()) }
    viewModel { PDDViewModel() }
    viewModel { FinesViewModel() }
    viewModel { RoadSignsViewModel() }
    viewModel { DetailVideoViewModel(get()) }
}
