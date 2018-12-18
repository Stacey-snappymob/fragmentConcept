package com.example.fragmentconcept

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentByDefaultButton.setOnClickListener {
            val intent = Intent(this, BottomFragmentByDefaultActivity::class.java)
            startActivity(intent)
        }

        fragmentWithInteractionButton.setOnClickListener {
            val intent = Intent(this, BottomFragmentByInteractionActivity::class.java)
            startActivity(intent)
        }

        draggableFragmentWithInteractionButton.setOnClickListener {
            val intent = Intent(this, DraggableBottomFragmentByInteractionActivity::class.java)
            startActivity(intent)
        }
    }
}
