package com.example.kotlintutorial_8

import android.os.Bundle
import android.util.Log
import android.view.ActionProvider
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

const val YOUTUBE_VIDEO_ID = "ElpitAfkRS4"
const val YOUTUBE_PLAYLIST = "PLXtTjtWmQhg1SsviTmKkWO5n0a_-T0bnD"

class YouTubeActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    private val TAG = "YouTubeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_youtube)
//        val layout = findViewById<ConstraintLayout>(R.id.activity_youtube)
        val layout = layoutInflater.inflate(R.layout.activity_youtube, null)as ConstraintLayout
        setContentView(layout)

//        val button1 = Button(this)
//        button1.layoutParams = ConstraintLayout.LayoutParams(600,100)
//        button1.text = "Buttton added"
//        layout.addView(button1)

        val playerView = YouTubePlayerView(this)
        playerView.layoutParams = ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        layout.addView(playerView)

        playerView.initialize(getString(R.string.GOOGLE_API_KEY), this)
    }

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, youTubePlayer: YouTubePlayer?,
                                         wasRestored: Boolean) {
        Log.d(TAG, "onInitializationSuccess: provider is ${provider?.javaClass}")
        Log.d(TAG, "onInitializationSuccess: youTubePlayer is ${youTubePlayer?.javaClass}")
        Toast.makeText(this, "Initialized YouTube Player successfully", Toast.LENGTH_SHORT).show()

        if (!wasRestored){
            youTubePlayer?.cueVideo(YOUTUBE_VIDEO_ID)
        }
    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider?,
                                         youTubeInitializationResult: YouTubeInitializationResult?) {
        val REQUEST_CODE = 0

        if (youTubeInitializationResult?.isUserRecoverableError == true){
            youTubeInitializationResult.getErrorDialog(this, REQUEST_CODE).show()
        }else{
            val errorManage = "There was an error initizing the YoutubePlayer ($youTubeInitializationResult)"
            Toast.makeText(this, errorManage, Toast.LENGTH_LONG).show()
        }
    }
}
