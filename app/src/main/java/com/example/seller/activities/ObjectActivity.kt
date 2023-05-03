package com.example.seller.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.seller.R
import com.example.seller.models.SelectedObjectData
import com.squareup.picasso.Picasso
import java.util.*

class ObjectActivity : AppCompatActivity() {
    private lateinit var objectName: TextView
    private lateinit var objectPrice: TextView
    private lateinit var objectDate: TextView
    private lateinit var objectUID: TextView
    private lateinit var objectImageID: TextView
    private lateinit var objectImage: ImageView
    private lateinit var buyButton: Button


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_object)

        objectName = findViewById(R.id.tvObjectName)
        objectPrice = findViewById(R.id.tvObjectPrice)
        objectDate = findViewById(R.id.tvObjectDate)
        objectUID = findViewById(R.id.tvObjectUID)
        objectImageID = findViewById(R.id.tvObjectImageID)
        objectImage = findViewById(R.id.imgObject)
        buyButton = findViewById<Button>(R.id.button)

        objectName.text = SelectedObjectData.objectData.name.replaceFirstChar { it.uppercase() }
        objectPrice.text = SelectedObjectData.objectData.price
        objectDate.text = SelectedObjectData.objectData.created
        objectUID.text = SelectedObjectData.objectData.uid
        objectImageID.text = SelectedObjectData.objectData.imageID.get(0)

        Picasso.get()
            .load(SelectedObjectData.objectData.imageUrlThumbs.get(0))
            .into(objectImage)

        buyButton.setOnClickListener {
            Toast.makeText(this, "Still working on the feature. Stay tuned!", Toast.LENGTH_SHORT)
                .show()
        }

    }


}