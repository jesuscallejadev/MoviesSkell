package com.jesus.moviesskell

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.jesus.moviesskell.databinding.ActivityMainBinding

//TODO: CHECK

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
    }

    //TODO: CHECK
    fun hideProgressBar() {
        binding!!.progressBar.visibility = View.GONE
    }

    //TODO: CHECK
    fun showProgressBar() {
        binding!!.progressBar.visibility = View.VISIBLE
    }
}

