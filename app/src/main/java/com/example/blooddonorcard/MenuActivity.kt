package com.example.blooddonorcard

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)




       // var backbtn = findViewById<ImageButton>(R.id.backbtn);

        //backbtn.setOnClickListener {
          //  val intent = Intent(this, ProfileActivity::class.java)
         //   startActivity(intent)

     //   }

        var tobedonor = findViewById<TextView>(R.id.ToBeADonorText);

        tobedonor.setOnClickListener {
            val intent = Intent(this, TobeDonor::class.java)
            startActivity(intent)

        }



        var FortheDonor = findViewById<TextView>(R.id.FortheDonor);

        FortheDonor.setOnClickListener {
            val intent = Intent(this, forthedonor::class.java)
            startActivity(intent)

        }

        var Find = findViewById<TextView>(R.id.Find);

        Find.setOnClickListener {
            val intent = Intent(this, urlshowActivity2::class.java)
            startActivity(intent)

        }

        var Questi = findViewById<TextView>(R.id.Questi);

        Questi.setOnClickListener {
            val intent = Intent(this, Questionnaire_Activity::class.java)
            startActivity(intent)

        }

        
        var progText = findViewById<TextView>(R.id.progText);

        progText.setOnClickListener {
            val intent = Intent(this, urlshowActivity::class.java)
            startActivity(intent)

        }


        var cont = findViewById<TextView>(R.id.ContactText);

        cont.setOnClickListener {
            val intent = Intent(this, ContactActivity::class.java)
            startActivity(intent)

        }
    }
}
