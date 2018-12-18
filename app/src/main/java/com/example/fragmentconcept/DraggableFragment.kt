package com.example.fragmentconcept


import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_bottom.*


/**
 * A simple [Fragment] subclass.
 * Use the [DraggableFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class DraggableFragment : Fragment(), View.OnTouchListener {
    private var _yDelta: Int? = null
    private var windowHeight: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_bottom, container, false)
        // Modify fragment width
        val layoutParams = view.layoutParams as FrameLayout.LayoutParams
        val deviceWidth = Resources.getSystem().displayMetrics.widthPixels
        val desiredDisplayWidth = deviceWidth * 0.6
        val margin = ((deviceWidth - desiredDisplayWidth) / 2).toInt()
        layoutParams.setMargins(margin, 0, margin, 0)
        view.layoutParams = layoutParams
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setOnTouchListener(this)

        closeButton.setOnClickListener {
            activity?.onBackPressed()
        }
        textView.text = this.javaClass.simpleName

        view.post {
            windowHeight = view.height //height is ready
        }
    }

    override fun onTouch(view: View, event: MotionEvent): Boolean {
        val Y = event.rawY.toInt()
        val layoutParams = view.layoutParams as FrameLayout.LayoutParams

        when (event.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_DOWN -> {
                // _yDelta record how far inside the view we have touched. These
                // values are used to compute new margins when the view is moved.
                _yDelta = Y - view.top
            }

            MotionEvent.ACTION_MOVE -> {
                if (Y - _yDelta!! < 0) {
                    layoutParams.topMargin = 0
                } else {
                    layoutParams.topMargin = Y - _yDelta!!
                }

                Log.i("Top Margin", "Top Margin: " + layoutParams.topMargin)
                Log.i("Top Margin", "View height: " + windowHeight)
                view.layoutParams = layoutParams
            }

            // When user remove their finger from screen
            MotionEvent.ACTION_UP -> {
                // Dismiss the fragment if it is less than half of the height of screen
                if (Y - _yDelta!! > (windowHeight!! * 0.5)){
                    activity?.onBackPressed()
                }
            }
        }
        return true
    }

    companion object {
        @JvmStatic
        fun newInstance() = DraggableFragment()
    }
}
