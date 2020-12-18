package com.kodless.kotlinnews.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kodless.kotlinnews.R
import com.kodless.kotlinnews.adapter.RecylerAdapter
import com.kodless.kotlinnews.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var feedViewModel: FeedViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var layoutManager = LinearLayoutManager(this)
        recylerView.layoutManager = layoutManager

        feedViewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        feedViewModel.getDataFromAPI()
        feedViewModel.haberlerim.observe(this, Observer {

            //RecylerView'i bağladık
            val adapter = RecylerAdapter(it.articles)
            recylerView.adapter = adapter
            adapter.notifyDataSetChanged()
        }
        )
    }

}