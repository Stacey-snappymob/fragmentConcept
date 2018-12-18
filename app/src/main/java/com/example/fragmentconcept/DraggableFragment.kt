package com.example.fragmentconcept


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_bottom.*

/**
 * A simple [Fragment] subclass.
 * Use the [BottomFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class DraggableFragment : Fragment(), View.OnTouchListener {
    private var initialY: Float? = null

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        Log.i("OnScroll: OnTouch", MotionEvent.actionToString(event?.action!!))
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                val shadowBuilder = View.DragShadowBuilder()
                view?.startDrag(null, shadowBuilder, view, 0)
                view?.setVisibility(View.VISIBLE)
                return true
            }
        }
        return true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_bottom, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setOnTouchListener(this)

        closeButton.setOnClickListener {
            activity?.onBackPressed()
        }
        textView.text = this.javaClass.simpleName
    }

    companion object {
        @JvmStatic
        fun newInstance() = DraggableFragment()
    }
}
