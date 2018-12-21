package com.example.fragmentconcept

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_bottom_fragment_by_interaction.*

class DraggableWrapContentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_fragment_by_interaction)

        goButton.setOnClickListener {
            if (supportFragmentManager.fragments.isEmpty()) {
                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_bottom, R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_bottom)
                    .add(android.R.id.content, DraggableWrapContentFragment.newInstance(), DraggableWrapContentFragment::class.java.simpleName)
                    .addToBackStack(DraggableWrapContentFragment::class.java.simpleName)
                    .commit()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(0, R.anim.abc_slide_out_top)
    }
}
