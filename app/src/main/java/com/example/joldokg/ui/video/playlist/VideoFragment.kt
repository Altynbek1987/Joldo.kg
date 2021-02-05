package com.example.joldokg.ui.video.playlist

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.joldokg.R
import com.example.joldokg.`interface`.OnItemClickListener
import com.example.joldokg.data.models.DetailVideo
import com.example.joldokg.data.models.PlaylistItems
import com.example.joldokg.ui.video.detailvideo.DetailVideoFragment
import com.example.joldokg.ui.video.playlist.adapter.PlayListAdapter
import kotlinx.android.synthetic.main.fragment_video.*
import org.koin.android.ext.android.inject

class VideoFragment : Fragment(),OnItemClickListener {
    private var listUrlMA: MutableList<PlaylistItems> = mutableListOf()
    private lateinit var adapter: PlayListAdapter
    private val videoViewModel by inject<VideoViewModel>()
    private var navController: NavController? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_video, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        setAdapter()
        fetchPlaylists()
    }
    private fun setAdapter() {
        adapter = PlayListAdapter(this)
        play_list_recyclerView.adapter = adapter
        val snap = LinearSnapHelper()
        snap.attachToRecyclerView(play_list_recyclerView)
    }
    private fun fetchPlaylists() {
        videoViewModel.fetchPlaylists()
        videoViewModel.playlistItems.observe(requireActivity(), Observer {
            adapter.addItems(it)
            listUrlMA = it
        })
    }

    override fun itemClick(position: Int) {
        val bundle = Bundle()
        bundle.putInt("position", position)
        bundle.putSerializable("id", listUrlMA[position].id)
        //DetailVideoFragment.instanceFragment(requireActivity(),listt, position)
        navController?.navigate(R.id.detailVideoFragment,bundle)

    }

    override fun itemClick(model: DetailVideo) {
        TODO("Not yet implemented")
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        super.onCreateOptionsMenu(menu, inflater)
    }
}