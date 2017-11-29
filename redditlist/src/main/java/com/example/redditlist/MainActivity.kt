package com.example.redditlist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.redditlist.dagger.component.DaggerRedditComponent

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    DaggerRedditComponent.builder()
        .bindContext(this)
        .build()
        .inject(this)

    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)


  }
}
