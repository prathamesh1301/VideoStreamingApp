package com.example.videostreamingapp.Model

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.util.TypedValue
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.MediaController
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.videostreamingapp.R

import com.example.videostreamingapp.databinding.ActivityPlayerBinding
import kotlin.math.roundToInt

class PlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intentData=intent.getSerializableExtra("videos") as Video
        //Log.d("seri",intentData.toString())
        binding.videoTitle.text=intentData.title
        binding.videoDesc.text=intentData.description

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val videoUrl=Uri.parse(intentData.sources.get(0))
        binding.videoView.setVideoURI(videoUrl)
        val mc=MediaController(this)
        binding.videoView.setMediaController(mc)
        binding.videoView.setOnPreparedListener {
            binding.videoView.start()
            binding.progressBar.visibility= View.GONE
        }
        binding.fullScreenImg.setOnClickListener {
            supportActionBar?.hide()
            requestedOrientation=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            binding.frameLayout.layoutParams=ConstraintLayout.LayoutParams(WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT))
            binding.videoView.layoutParams=FrameLayout.LayoutParams(WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT))
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==android.R.id.home){
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onBackPressed(){
        binding.fullScreenImg.visibility=View.GONE
        requestedOrientation=ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        supportActionBar?.show()
        window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val resources = resources
        val heightVal= 220 / (resources.displayMetrics.densityDpi / 160f).roundToInt()
        binding.frameLayout.layoutParams=ConstraintLayout.LayoutParams(WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,heightVal))
        binding.videoView.layoutParams=FrameLayout.LayoutParams(WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,heightVal))

        if(resources.configuration.orientation==Configuration.ORIENTATION_PORTRAIT){
            super.onBackPressed()
        }
    }
}