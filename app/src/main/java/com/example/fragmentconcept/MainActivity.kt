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

//        draggableBottomFragmentWithInteractionButton.setOnClickListener {
//            val intent = Intent(this, DraggableBottomFragmentByInteractionActivity::class.java)
//            startActivity(intent)
//        }

        draggableFragmentButton.setOnClickListener {
            val intent = Intent(this, DraggableFragmentActivity::class.java)
            startActivity(intent)
        }

        draggableWrapContentFragmentButton.setOnClickListener {
            val intent = Intent(this, DraggableWrapContentActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.abc_slide_in_top, 0)
        }

        draggableFragmentWithTransitionButton.setOnClickListener {
            val intent = Intent(this, DraggableTransitionActivity::class.java)
            startActivity(intent)
        }
    }
}
