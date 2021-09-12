package com.example.videostreamingapp.Model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.videostreamingapp.Adapter.RecyclerAdapter
import com.example.videostreamingapp.databinding.ActivityMainBinding
import com.example.videostreamingapp.network.RetrofitObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var list:List<Video>
    private lateinit var adapter:RecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        list= ArrayList<Video>()
        binding.videoList.layoutManager=LinearLayoutManager(this)
        binding.videoList.setHasFixedSize(true)


        getJsonData()
    }

    private fun getJsonData() {
        RetrofitObject.obj.getVideos().enqueue(object : Callback<VideoData> {
            override fun onResponse(call: Call<VideoData>, response: Response<VideoData>) {
                if(response.isSuccessful){
                    val resp= response.body()?.categories?.get(0)?.videos
                    Log.d("tag",resp.toString())
                    //list= resp?.videos!!

                    adapter= RecyclerAdapter(resp!!,this@MainActivity)



                    binding.videoList.adapter=adapter
                }else{
                    Toast.makeText(applicationContext,"sdsdsd",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<VideoData>, t: Throwable) {
                Toast.makeText(applicationContext,"Error",Toast.LENGTH_SHORT).show()
            }
        })
    }
}