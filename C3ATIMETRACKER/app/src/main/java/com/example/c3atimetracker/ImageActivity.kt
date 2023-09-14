package com.example.c3atimetracker

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

lateinit var imageView: ImageView

lateinit var button: Button

lateinit var button2: Button

val REQUEST_IMAGE_CAPTURE = 100

@Suppress("DEPRECATION")
class ImageActivity : AppCompatActivity() {

    companion object
    {
        const val IMAGE_REQUEST_CODE = 100
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_image)

        imageView = findViewById(R.id.imageSaved)

        button = findViewById(R.id.btnTakePicture)

        button2 = findViewById(R.id.btn_pick_img)


        button2.setOnClickListener {
            pickImageGallery()
            }


        button.setOnClickListener{
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            try {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            catch (e: ActivityNotFoundException)
               {
                Toast.makeText(this,"Error " + e.localizedMessage, Toast.LENGTH_SHORT).show()
               }
        }
    }



    private fun pickImageGallery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //Saving photo from gallery to imageview (No works)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK)
        {
            imageView.setImageURI(data?.data)

            val imageBitmap = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(imageBitmap)
        }


        //Saving photo from camera to imageview (Works)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            val imageBitmap = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(imageBitmap)
        }
    }



}