package com.example.fragmentconcept

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_bottom_fragment_by_interaction.*

class BottomFragmentByInteractionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_fragment_by_interaction)

        goButton.setOnClickListener {
            if (supportFragmentManager.fragments.isEmpty()) {
                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_bottom, R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_bottom)
                    .add(android.R.id.content, BottomFragment.newInstance(), BottomFragment::class.java.simpleName)
                    .addToBackStack(BottomFragment::class.java.simpleName)
                    .commit()
            }
        }
    }

}
