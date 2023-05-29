package com.example.marvelappx.ui

import com.google.android.youtube.player.YouTubeBaseActivity
import android.os.Bundle
import com.example.marvelappx.R
import com.google.android.youtube.player.YouTubePlayerView
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubeInitializationResult
import android.widget.Toast
import java.util.*

class VideoViewActivity : YouTubeBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.video_view_activity)
        //
//        MediaController mediaController;
//        mediaController = new MediaController(getApplicationContext());
//        int [] array = {R.raw.miranha, R.raw.miranha3, R.raw.video4};

//       String [] arrayURL = {"https://www.youtube.com/watch?v=Jrpv1C0WHFI", "https://www.youtube.com/watch?v=2Lba2qhWBe8"
//       , "https://www.youtube.com/watch?v=d81p3CFa9oM", "https://www.youtube.com/watch?v=uYUd54FpOIE",
//               "https://www.youtube.com/watch?v=dJU7io6neHk"};
////
//        VideoView mVideoView = findViewById(R.id.video_view);
//        Uri localUri = Uri.parse("android.resource://" + getPackageName() + "/" + array[1]);
//        mVideoView.setVideoURI(localUri);
//        mVideoView.setMediaController(mediaController);
//        mVideoView.stopPlayback();
//        mVideoView.start();

        // WebView myWebView = (WebView) findViewById(R.id.video_view);

//        Random random = new Random();
//        for (int i = 0; i < arrayURL.length; i++) {
//            int j = random.nextInt(arrayURL.length);
//            System.out.println("ZZZ " + j);
//            WebView myWebView = (WebView) findViewById(R.id.video_view);
//            myWebView.loadUrl(arrayURL[j]);
//            break;
//
//        }
        //myWebView.loadUrl(array[x]);


        //myWebView.loadUrl(arrayURL[0]);
        // myWebView.loadUrl("https://drive.google.com/file/d/158M1F7Tsl8iarwLfKAT8hWaaFf3elGp7/view?usp=sharing");
        val youTubePlayerView: YouTubePlayerView
        youTubePlayerView = findViewById(R.id.video_view)
        val listener: YouTubePlayer.OnInitializedListener =
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider,
                    youTubePlayer: YouTubePlayer,
                    b: Boolean
                ) {
                    val arrayURL = arrayOf(
                        "Jrpv1C0WHFI",
                        "2Lba2qhWBe8",
                        "d81p3CFa9oM",
                        "uYUd54FpOIE",
                        "dJU7io6neHk"
                    )
                    val random = Random()
                    for (i in arrayURL.indices) {
                        val j = random.nextInt(arrayURL.size)
                        val string = arrayURL[j]
                        youTubePlayer.loadVideo(string)
                        youTubePlayer.play()
                    }
                }

                override fun onInitializationFailure(
                    provider: YouTubePlayer.Provider,
                    youTubeInitializationResult: YouTubeInitializationResult
                ) {
                    Toast.makeText(applicationContext, "errou feio", Toast.LENGTH_SHORT).show()
                }
            }
        youTubePlayerView.initialize("AIzaSyCknnR8i1B7UZ6eCz2i2dd0hGnBJV4pWqw", listener)
    }
}