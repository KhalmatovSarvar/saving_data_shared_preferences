package com.example.saving_data_shared_preferences

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        initVies()
    }

    private fun initVies() {
        val text = findViewById<TextView>(R.id.tv_details)
        val details =intent.extras?.getParcelable<User>("User")
        Log.d("123", details.toString())
        text.text = details.toString()
    }
}