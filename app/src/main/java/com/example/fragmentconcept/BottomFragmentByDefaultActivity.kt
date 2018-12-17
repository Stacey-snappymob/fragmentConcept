package com.example.fragmentconcept

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_bottom_fragment_by_default.*

class BottomFragmentByDefaultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_fragment_by_default)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_bottom, R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_bottom)
                .add(bottomFragmentContainer.id, BottomFragment.newInstance(), BottomFragment::class.java.simpleName)
                .addToBackStack(BottomFragment::class.java.simpleName)
                .commit()
        }
    }
}
