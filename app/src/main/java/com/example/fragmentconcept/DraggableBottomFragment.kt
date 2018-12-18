package com.example.fragmentconcept


import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_bottom.*

/**
 * A simple [Fragment] subclass.
 * Use the [BottomFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class DraggableBottomFragment : Fragment(), View.OnTouchListener, View.OnDragListener {
    private var initialY: Float? = null

    override fun onDrag(v: View?, event: DragEvent?): Boolean {
        Log.i("onDrag", MotionEvent.actionToString(event?.action!!))
        when (event?.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                initialY = view?.y
                Log.i("onDrag", "InitialY = " + initialY)
            }

            DragEvent.ACTION_DROP -> {
                val updatedY = event?.y

        val layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
        layoutParams.setMargins(0, updatedY!!.toInt(), 0, 0)
        view?.layoutParams = layoutParams
            }
        }

        val updatedY = event?.y

//        val layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
//        layoutParams.setMargins(0, updatedY!!.toInt(), 0, 0)
//        view?.layoutParams = layoutParams
        return true
    }

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
        view.setOnDragListener(this)

        closeButton.setOnClickListener {
            activity?.onBackPressed()
        }
        textView.text = this.javaClass.simpleName
    }

    companion object {
        @JvmStatic
        fun newInstance() = DraggableBottomFragment()
    }
}
