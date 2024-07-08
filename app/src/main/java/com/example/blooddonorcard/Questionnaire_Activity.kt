package com.example.blooddonorcard

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class Questionnaire_Activity : AppCompatActivity() {


    var firebaseStorage: FirebaseStorage? = null
    var storageReference: StorageReference? = null
    var ref: StorageReference? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionnaire_)

         var down = findViewById<Button>(R.id.down)

        down.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                download()
            }
        })
}


    private fun download() {
        storageReference = FirebaseStorage.getInstance().reference
        ref = storageReference!!.child("/quest_donor/quest.pdf")
        ref!!.getDownloadUrl().addOnSuccessListener(OnSuccessListener<Uri> { uri ->
            val url = uri.toString()
            downloadFile(this@Questionnaire_Activity,
                "Question",
                ".pdf",
                Environment.DIRECTORY_DOWNLOADS,
                url)
        }).addOnFailureListener(OnFailureListener { })
    }

    private fun downloadFile(
        context: Context,
        fileName: String,
        fileExtensions: String,
        destinationDirectory: String,
        url: String,
    ) {
        val downloadManager = context.getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        val uri = Uri.parse(url)
        val request = DownloadManager.Request(uri)
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalFilesDir(context,
            destinationDirectory,
            fileName + fileExtensions)
        downloadManager.enqueue(request)
    }


}




