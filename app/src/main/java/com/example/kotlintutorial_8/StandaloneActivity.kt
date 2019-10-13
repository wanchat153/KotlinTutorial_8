package com.example.kotlintutorial_8

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.youtube.player.YouTubeStandalonePlayer
import com.google.android.youtube.player.internal.v
import kotlinx.android.synthetic.main.activity_standalone.*
import java.lang.IllegalArgumentException

class StandaloneActivity : AppCompatActivity(), View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standalone)

        btnPlayVideo.setOnClickListener(this)
        btnPlaylist.setOnClickListener(this)

//        btnPlayVideo.setOnClickListener(object : View.OnClickListener{
//            override fun onClick(p0: View?) {
//            }
//        })
//        btnPlayVideo.setOnClickListener(object : View.OnClickListener { v ->
//        })
//
//        val listenerProxy =  View.OnClickListener { v ->
//        }
//        btnPlayVideo.setOnClickListener(listener)
//        btnPlaylist.setOnClickListener(listener)
    }

    override fun onClick(view: View?) {
        val intent = when (view?.id){
            R.id.btnPlayVideo -> YouTubeStandalonePlayer.createVideoIntent(
                this, "AIzaSyCbiVioHtj_nlDFNfUz6TKYzliucIOGHXs", YOUTUBE_VIDEO_ID, 0, true, false)
            R.id.btnPlaylist -> YouTubeStandalonePlayer.createPlaylistIntent(
                this, "AIzaSyCbiVioHtj_nlDFNfUz6TKYzliucIOGHXs", YOUTUBE_PLAYLIST, 0, 0, true, true)
            else -> throw IllegalArgumentException("Undefind button clicked")
        }
        startActivity(intent)
    }
}