package com.kodless.kotlinnews.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kodless.kotlinnews.R
import com.kodless.kotlinnews.databinding.ActivityMain2Binding
import com.kodless.kotlinnews.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {

    lateinit var newsViewModel : NewsViewModel
    private lateinit var dataBinding : ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        dataBinding = DataBindingUtil.setContentView(this,R.layout.activity_main2)
        //dataBinding.setLifecycleOwner(this)

        val intent = intent
        var gelenUuid =  intent.getIntExtra("veri",0)


        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        newsViewModel.getDataFromRoom(gelenUuid)
        newsViewModel.newsLiveData.observe(this, Observer {
            //tvHaberDetay.text = it.title
            //tvHaberIcerik.text = it.description

            dataBinding.news = it
        })
    }
}