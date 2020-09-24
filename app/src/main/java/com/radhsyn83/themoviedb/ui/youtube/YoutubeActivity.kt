package com.radhsyn83.themoviedb.ui.youtube

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.radhsyn83.themoviedb.BuildConfig
import com.radhsyn83.themoviedb.R
import com.radhsyn83.themoviedb.utils.Tools
import kotlinx.android.synthetic.main.activity_youtube.*
import org.jetbrains.anko.toast

class YoutubeActivity : YouTubeBaseActivity() {

    private lateinit var mOnInitializedListener: YouTubePlayer.OnInitializedListener
    private lateinit var idYoutube: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube)

        idYoutube = intent?.extras?.getString("youtubeId", "").toString()

        iv_close.setOnClickListener {
            onBackPressed()
        }


        if (Build.VERSION.SDK_INT in 19..20) {
            Tools.setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        }
        if (Build.VERSION.SDK_INT >= 21) {
            Tools.setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = resources.getColor(R.color.black)
        }

        mOnInitializedListener = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(provider: YouTubePlayer.Provider, youTubePlayer: YouTubePlayer, b: Boolean) {
                if (idYoutube.isNotEmpty())
                    youTubePlayer.loadVideo(idYoutube)
            }

            override fun onInitializationFailure(provider: YouTubePlayer.Provider, youTubeInitializationResult: YouTubeInitializationResult) {
                toast("Failed to initalized")
            }
        }

        youtbePlay.initialize(BuildConfig.YOUTUBE_API_KEY, mOnInitializedListener)
    }
}
