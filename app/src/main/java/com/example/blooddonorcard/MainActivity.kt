package com.example.blooddonorcard

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //set carousel images/names
    var sampleImages = intArrayOf(
        R.drawable.carousel1,
        R.drawable.carousel2,
        R.drawable.carousel3,
        R.drawable.carousel4

    )
    var messagies = arrayOf(
        "Think!",
        "Be a Donor",
        "Save a Life",
        "Save Your Life"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var skipbtn = findViewById<Button>(R.id.skipbtn);

        skipbtn.setOnClickListener {
            val intent = Intent(this, LogActivity::class.java)
            startActivity(intent)

        }

        carouselView.pageCount = messagies.size
        carouselView.setImageListener { position, imageView ->
            imageView.setImageResource(sampleImages[position])
        }

        carouselView.setImageClickListener{position ->
            Toast.makeText(applicationContext, messagies[position], Toast.LENGTH_SHORT).show()
        }



 }
}
