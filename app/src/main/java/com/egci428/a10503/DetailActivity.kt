package com.egci428.a10503

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.nameText
import kotlinx.android.synthetic.main.activity_main.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val name = intent.getStringExtra("name")
        val description = intent.getStringExtra("description")
        val part = intent.getStringExtra("part")
        val image = intent.getStringExtra("image")

        nameText.text = name
        descriptionText.text = description
        partText.text = "Part: $part"

        if (image == "bangkok")
            imgCity.setImageResource(R.drawable.bangkok)
        if (image == "pattaya")
            imgCity.setImageResource(R.drawable.pattaya)
        if (image == "chiangmai")
            imgCity.setImageResource(R.drawable.chiangmai)
        if (image == "phuket")
            imgCity.setImageResource(R.drawable.phuket)

        backBtn.setOnClickListener {
            displayMain()
        }

    }

    private fun displayMain(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
