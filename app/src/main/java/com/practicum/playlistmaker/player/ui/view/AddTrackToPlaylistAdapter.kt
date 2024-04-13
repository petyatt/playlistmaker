package com.practicum.playlistmaker.player.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.practicum.playlistmaker.R
import com.practicum.playlistmaker.media.domain.model.Playlist
import com.practicum.playlistmaker.search.domain.models.Track

class AddTrackToPlaylistAdapter(
    private var playlists: List<Playlist>,
    private var tracks: Track,
    private val clickListener: OnPlaylistClickListener
): RecyclerView.Adapter<AddTrackToPlaylistAdapter.AddTrackToPlaylistViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AddTrackToPlaylistViewHolder = AddTrackToPlaylistViewHolder(parent)

    override fun getItemCount() = playlists.size

    override fun onBindViewHolder(holder: AddTrackToPlaylistViewHolder, position: Int) {
        holder.bind(playlists[position])
    }

    fun interface OnPlaylistClickListener {
        fun onPlaylistClick(playlist: Playlist, track: Track)
    }

    inner class AddTrackToPlaylistViewHolder(
        parent: ViewGroup
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_add_track_in_playlist, parent, false)
    ) {

        private val playlistAlbum: ImageView = itemView.findViewById(R.id.playlist_cover)
        private val playlistTitle: TextView = itemView.findViewById(R.id.playlist_name)
        private val playlistCountTrack: TextView = itemView.findViewById(R.id.count_track)

        fun bind(playlist: Playlist) {

            playlistTitle.text = playlist.title
            playlistCountTrack.text = playlist.trackCount.toString()

            Glide.with(itemView)
                .load(playlist.imagePath)
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .transform(RoundedCorners(10))
                .into(playlistAlbum)

            itemView.setOnClickListener { clickListener.onPlaylistClick(playlist, tracks) }
        }
    }

    fun setPlaylists(playlists: List<Playlist>) {
        this.playlists = playlists
        notifyDataSetChanged()
    }
}