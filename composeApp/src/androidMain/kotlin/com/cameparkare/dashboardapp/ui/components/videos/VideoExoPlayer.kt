package com.cameparkare.dashboardapp.ui.components.videos

import android.util.Log
import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.Player.REPEAT_MODE_ALL
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.cameparkare.dashboardapp.ui.screens.main.MainViewModel
import org.koin.androidx.compose.koinViewModel


@OptIn(UnstableApi::class) @Composable
fun VideoExoPlayer(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = koinViewModel()
){
    Log.i("DASHBOARD_LOG", "Entra en VideoExoPlayer")
    val context = LocalContext.current
    val state by viewModel.videoState.collectAsState()

    val mPlayer = remember {
        (ExoPlayer.Builder(context).build())
    }

    val uri = state.videoRoutes
    val index = remember {
        mutableStateOf(0)
    }

    if (uri.isEmpty()) {
        if (viewModel.showVideoFrame()) EmptyVideoFrame(modifier)
        return
    }

    if (mPlayer.mediaItemCount == 0){
        mPlayer.addMediaItems( uri.map { MediaItem.fromUri(it) } )
    }else{
        mPlayer.replaceMediaItem(0, MediaItem.fromUri(uri[index.value]))
    }

    val playerView = PlayerView(context)

    playerView.player = mPlayer
    playerView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL

    LaunchedEffect(mPlayer) {
        Log.i("PLAY_NEXT_VIDEO", "mediaItemCount: - ${mPlayer.mediaItemCount}")

        mPlayer.prepare()
        mPlayer.volume = 0f
        mPlayer.play()
        mPlayer.repeatMode = REPEAT_MODE_ALL

    }

    DisposableEffect(Unit) {
        val listener = object : Player.Listener {
            override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                super.onMediaItemTransition(mediaItem, reason)
                index.value = (index.value + 1).mod(uri.size)
            }
        }

        mPlayer.addListener(listener)

        onDispose {
            mPlayer.removeListener(listener)
            mPlayer.release()
        }
    }
    ExoVideosContainer(modifier, playerView)
}