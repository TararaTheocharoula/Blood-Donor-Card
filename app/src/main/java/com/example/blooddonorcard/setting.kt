package com.example.blooddonorcard

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.blooddonorcard.databinding.ActivitySettingBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_setting.*

class setting : AppCompatActivity() {


    private lateinit var binding: ActivitySettingBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var dialog : Dialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        val uid = auth.currentUser?.uid
        databaseReference = FirebaseDatabase.getInstance().getReference("profile")
         SvButton.setOnClickListener {

            showProgressBar()
            val firstlastname = FirtLastNameInput.text.toString()
            val email =usernameInput.text.toString()
            val amka =AmkaInput.text.toString()
            val password =passwordInput.text.toString()

            val user = User(firstlastname, email, amka, password)
            if (uid !=null) {

                databaseReference.child(uid).setValue(user).addOnCompleteListener {

                    if (it.isSuccessful)
                    {
                        hideProgressBar()

                    } else {

                        hideProgressBar()
                        Toast.makeText(this@setting, "Failed to update profile", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
        var backbtn = findViewById<ImageButton>(R.id.backbtn);

        backbtn.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)

        }
    }

    private fun showProgressBar(){

        dialog = Dialog(this@setting)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_wait)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()

    }

   private fun hideProgressBar() {
       dialog.dismiss()
   }



}
