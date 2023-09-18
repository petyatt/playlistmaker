package com.practicum.playlistmaker.data.dto

interface PlayerDataSource {

    fun startPlayer()

    fun pausePlayer()

    fun prepareAsync()

    fun releasePlayer()

    fun isPlaing(): Boolean

    fun getCurrentPosition(): Int

    fun seekPlayer(position: Int)

    fun setDataSource(url: String)

    fun setOnPreparedListener(onPreparedListener : () -> Unit)

    fun setOnCompletionListener(onCompletionListener : () -> Unit)

}