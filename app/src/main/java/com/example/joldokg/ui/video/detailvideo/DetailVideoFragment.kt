package com.example.joldokg.ui.video.detailvideo

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.joldokg.R
import com.example.joldokg.`interface`.OnItemClickListener
import com.example.joldokg.data.models.DetailVideo
import com.example.joldokg.data.network.Status
import com.example.joldokg.ui.video.detailvideo.adapter.DetailPlayListAdapter
import kotlinx.android.synthetic.main.detail_video_fragment.*
import org.koin.android.ext.android.inject

class DetailVideoFragment : Fragment(), OnItemClickListener {
    private var listDetail: MutableList<DetailVideo> = mutableListOf()
    private lateinit var adapter: DetailPlayListAdapter
    private val detailVideoViewModel by inject<DetailVideoViewModel>()
    private var navController: NavController? = null
    private var data: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_video_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab.setOnClickListener { view ->
            //navController?.navigate(R.id.videoFragment)
            close()
            Log.e("ololo", "fab.setOnClickListener" )
        }
        setDetailAdapter()
        getNavData()
        fetchPlayListsItems()

    }
    private fun close() {
        //здес закрываю DetailVideoFragment и захожу в хом фрагмент
        val controller = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        controller.popBackStack()
    }
    fun setDetailAdapter() {
        adapter =
            DetailPlayListAdapter(this)
        recyclerViewDetailPlayList.adapter = adapter
        val snap = LinearSnapHelper()
        snap.attachToRecyclerView(recyclerViewDetailPlayList)
    }
    fun getNavData(){
        data = arguments?.getSerializable("id") as String?
        Log.e("ololo", "OK" )
    }

    private fun fetchPlayListsItems() {
        data?.let { data ->
            detailVideoViewModel.fetchPlayListsItems(data, null).observe(requireActivity(), Observer { it ->
                when (it.status) {
                    Status.SUCCESS -> {
                        it.data?.items?.let { adapter.detailItems(it) }
                        listDetail = it.data?.items!!
                        Log.e("ololo", "SUCCESS" )
                    }
                    Status.ERROR -> {
                        Log.e("ololo", "ERROR " )

                    }
                }
            })
        }
    }

    companion object {
        var listDetail: MutableList<DetailVideo>? = null
        var data: String? = null

        fun instanceFragment(
                context: Context,
                listDetail: MutableList<DetailVideo>,
                position: Int
        ) {
           this.data = data
            val bundle = Bundle()
            this.listDetail = listDetail


        }
    }

    override fun itemClick(position: Int) {
        TODO("Not yet implemented")
    }

    override fun itemClick(model: DetailVideo) {
        TODO("Not yet implemented")
    }


}