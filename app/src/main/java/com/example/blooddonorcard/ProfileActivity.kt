package com.example.blooddonorcard

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {


    lateinit var auth: FirebaseAuth
    var databaseReference :  DatabaseReference? = null
    var database: FirebaseDatabase? = null
    private lateinit var uid :String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        uid = auth.currentUser?.uid.toString()
        databaseReference = database?.reference!!.child("profile")

        loadProfile()

        var mnbtn = findViewById<ImageButton>(R.id.mnbtn);

        mnbtn.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)

        }
    }

    private fun loadProfile() {

        val user = auth.currentUser
        val userreference = databaseReference?.child(user?.uid!!)


        FirtLastNameInput.text = "Firstname & Lastname----->" +user.toString()
        usernameInput.text = "Email ----> " +user?.email
        AmkaInput.text = "Amka---->" +user?.toString()
        spBlood.text = "Type of Blood---->" +user.toString()
        Ypoloipo.text = "Ypoloipo ----->" +user.toString()


        userreference?.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                FirtLastNameInput.text = " Firstname & Lastname:        " +snapshot.child("FirstnameLastname").value.toString()
                usernameInput.text = "Email - -> "+snapshot.child("email").value.toString()
                AmkaInput.text = "Amka - - >" +snapshot.child("amka").value.toString()
                spBlood.text = "Type of Blood -- >" +snapshot.child("type of blodd").value.toString()
                Ypoloipo.text = "Ypoloipo ----->" +snapshot.child("Ypoloipo").value.toString()

                           }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })


        logoutButton.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this@ProfileActivity, LogActivity::class.java))
            finish()
        }




    }


}

