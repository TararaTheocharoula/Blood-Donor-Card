package com.example.blooddonorcard

import android.content.DialogInterface
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_log.*
import java.util.*

class    LogActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadLocale()
        setContentView(R.layout.activity_log)

        auth = FirebaseAuth.getInstance()

        var registerbtn = findViewById<Button>(R.id.registerTxt);

        registerbtn.setOnClickListener {
            val intent = Intent(this, RegActivity::class.java)
            startActivity(intent)

        }

        val currentuser = auth.currentUser
        if(currentuser != null) {
            startActivity(Intent(this@LogActivity, ProfileActivity::class.java))
            finish()
        }
        login()



        FogPasButton.setOnClickListener {
        val builder = AlertDialog.Builder(this)
            builder.setTitle("Forgot Password")
            val view = layoutInflater.inflate(R.layout.dialog_forgot_password, null)
            val username = view.findViewById<EditText>(R.id.et_username)
            builder.setView(view)
            builder.setPositiveButton("Reset", DialogInterface.OnClickListener { _, _ ->
                forgotPassword(username)
            })

            builder.setNegativeButton("Close", DialogInterface.OnClickListener { _, _ -> })
            builder.show()
        }

        val changeLang = findViewById<Button>(R.id.ChangeLang)
        changeLang.setOnClickListener { //show AlertDialog to display list of Languages, one must be selected
            showChangeLanguageDialog()
        }


    }

    //create seperate strings for each language
    private fun showChangeLanguageDialog() {
        //array of languages
        val listItems = arrayOf("Greek","English", "Deutsche")
        val mBuilder = AlertDialog.Builder(this@LogActivity)
        mBuilder.setTitle("Choose The Language you want")
        mBuilder.setSingleChoiceItems(listItems, -1
        ) { dialogInterface, i ->
            if (i == 0) {
                //Greek
                setLocale("el")
                recreate()
            } else if (i == 1) {
                //English
                setLocale("en")
                recreate()
            }else if (i == 2) {
                //English
                setLocale("de")
                recreate()
            }

            //dismiss alert dialog when language selected
            dialogInterface.dismiss()
        }
        val mDialog = mBuilder.create()
        //show alert dialog
        mDialog.show()
    }

    private fun setLocale(lang: String?) {
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
        // save to shared preferences
        val editor = getSharedPreferences("SettingsActivity", MODE_PRIVATE).edit()
        editor.putString("My Lang", lang)
        editor.apply()
    }

    //load languages saved in shared preferences
    fun loadLocale() {
        val prefs = getSharedPreferences("SettingsActivity", MODE_PRIVATE)
        val language = prefs.getString("My Lang", "")
        setLocale(language)
    }


    private fun forgotPassword(username: EditText){

        if(TextUtils.isEmpty(username.text.toString())){
            return
        }
        auth.sendPasswordResetEmail(username.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    Toast.makeText(this, "Email sent.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun login() {

        loginButton.setOnClickListener {

            if(TextUtils.isEmpty(usernameInput.text.toString())){
                usernameInput.setError("Please enter username")
                return@setOnClickListener
            }
            else if(TextUtils.isEmpty(passwordInput.text.toString())){
                passwordInput.setError("Please enter password")
                return@setOnClickListener
            }
            auth.signInWithEmailAndPassword(usernameInput.text.toString(),
                passwordInput.text.toString())
                .addOnCompleteListener {
                    if(it.isSuccessful) {
                        startActivity(Intent(this@LogActivity, ProfileActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this@LogActivity,
                            "Login failed, please try again! ",
                            Toast.LENGTH_LONG).show()
                    }
                }

        }


    }



}

