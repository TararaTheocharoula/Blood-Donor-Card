package com.example.blooddonorcard

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_reg.*


class RegActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
     var databaseReference: DatabaseReference? = null
     var database : FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)

        var FindAmkaButton = findViewById<Button>(R.id.FindAmkaButton);

        FindAmkaButton.setOnClickListener {
            var uri = Uri.parse("https://www.amka.gr/AMKAGR/")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
        
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("profile")

        register()

    }
    private fun register () {

        registerButton.setOnClickListener {

           if(TextUtils.isEmpty(FirtLastNameInput.text.toString())) {
                  FirtLastNameInput.setError("Please enter first name")
               return@setOnClickListener
           } else if(TextUtils.isEmpty(usernameInput.text.toString())) {
               usernameInput.setError("Please enter email")
               return@setOnClickListener
           }else if(TextUtils.isEmpty(passwordInput.text.toString())) {
               passwordInput.setError("Please enter password")
               return@setOnClickListener
           }else if(TextUtils.isEmpty(AmkaInput.text.toString())) {
               AmkaInput.setError("Please enter Amka")
               return@setOnClickListener
           }

            auth.createUserWithEmailAndPassword(usernameInput.text.toString(),passwordInput.text.toString())
                .addOnCompleteListener {
                    if(it.isSuccessful) {
                       val currentUser =  auth.currentUser
                        val currentUserDb = databaseReference?.child((currentUser?.uid!!))
                        currentUserDb?.child("FirstnameLastname")?.setValue(FirtLastNameInput.text.toString())
                        currentUserDb?.child("amka")?.setValue(AmkaInput.text.toString())
                        currentUserDb?.child("Type of blodd")?.setValue(spBlood.text.toString())
                        currentUserDb?.child("Email")?.setValue(usernameInput.text.toString())
                        Toast.makeText(this@RegActivity, "Registration Success!", Toast.LENGTH_LONG).show()

                        finish()

                    }else {
                      Toast.makeText(this@RegActivity, "Registration Failed, pleas try again!", Toast.LENGTH_LONG).show()
                    }
                }

           }
        }
    }


