package com.example.blooddonorcard

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_contact.*

class ContactActivity : AppCompatActivity() {

    val phoneNumber1 = "2132146700"
    val phoneNumber2= "2107474274"
    val REQOUEST_PHONE_CALL = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        var babtn = findViewById<ImageButton>(R.id.babtn);

        babtn.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)

        }

        var Ekea = findViewById<TextView>(R.id.national);

        Ekea.setOnClickListener {
            var uri = Uri.parse("https://ekea.gr/")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }

        var EkeaMap = findViewById<TextView>(R.id.Ekeamap);

        EkeaMap.setOnClickListener {
            var uri =
                Uri.parse("https://www.google.com/maps/place/%CE%95%CE%98%CE%9D%CE%99%CE%9A%CE%9F+%CE%9A%CE%95%CE%9D%CE%A4%CE%A1%CE%9F+%CE%91%CE%99%CE%9C%CE%9F%CE%94%CE%9F%CE%A3%CE%99%CE%91%CE%A3+%22E.KE.A.%22/@38.0921998,23.7818682,17z/data=!3m1!4b1!4m5!3m4!1s0x14a1a1933fee8191:0x262b92fa5f97242e!8m2!3d38.0921998!4d23.7840569")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }


        Ekeacall.setOnClickListener {

            if (ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.CALL_PHONE),
                    REQOUEST_PHONE_CALL
                )
            } else {
                startCall()
            }
        }


        var Eema = findViewById<TextView>(R.id.EMA);

        Eema.setOnClickListener {
            var uri = Uri.parse("https://blooddonorregistry.gr/")
            startActivity(Intent(Intent.ACTION_VIEW,uri))
        }

        var EemaMap = findViewById<TextView>(R.id.Emamap);

        EemaMap.setOnClickListener {
            var uri = Uri.parse("https://www.google.com/maps/place/%CE%9B%CE%B5%CF%89%CF%86.+%CE%9A%CE%B7%CF%86%CE%B9%CF%83%CE%AF%CE%B1%CF%82+7,+%CE%91%CE%B8%CE%AE%CE%BD%CE%B1+115+23/@37.9874456,23.7595544,17z/data=!3m1!4b1!4m5!3m4!1s0x14a1a2aabf18b731:0xf7f31b19c177d5b6!8m2!3d37.9874456!4d23.7617431")
            startActivity(Intent(Intent.ACTION_VIEW,uri))
        }

        Emacall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:" +phoneNumber2)
            startActivity(intent)
        }

    }

    private fun startCall() {
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = (Uri.parse("tel:" + phoneNumber1))
        startActivity(callIntent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == REQOUEST_PHONE_CALL)startCall()
    }
    }



