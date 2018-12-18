package com.example.fragmentconcept


import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_bottom.*






/**
 * A simple [Fragment] subclass.
 * Use the [BottomFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class BottomFragment : Fragment(), View.OnTouchListener {
    private var initialX: Float? = null

//    override fun onDrag(v: View?, event: DragEvent?): Boolean {
//        Log.i("onDrag", MotionEvent.actionToString(event?.action!!))
//        when (event?.action) {
//            DragEvent.ACTION_DRAG_STARTED -> {
//                initialX = event.x
//                Log.i("onDrag", "InitialX = " + initialX)
//            }
//        }
//
//        val updatedY = event?.y
//
//        val layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT)
//        layoutParams.setMargins(initialX!!.toInt(), updatedY!!.toInt(), 0, 0)
//        view?.layoutParams = layoutParams
//        return true
//    }

    private lateinit var gestureDetector: GestureDetector
    private var initialY = 0f
    private var scrolledY = 0f

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        gestureDetector.onTouchEvent(event)
        Log.i("OnScroll: OnTouch", MotionEvent.actionToString(event?.action!!))
        when (event?.action) {
            MotionEvent.ACTION_UP -> {
                Log.i("OnScroll: OnTouch", MotionEvent.actionToString(event?.action!!))
                Log.i("OnScroll: OnTouch", "Initial Y = " + initialY)
                Log.i("OnScroll: OnTouch", "Scrolled Y = " + scrolledY)

                view?.animate()!!.translationY(initialY - scrolledY)
//                if (scrolledY - initialY > 0) {
//                    view?.animate()!!.translationY(scrolledY - initialY)
//                } else {
//                    view?.animate()!!.translationY(-(scrolledY - initialY))
//                }

            }
//            MotionEvent.ACTION_DOWN -> {
//                val shadowBuilder = View.DragShadowBuilder(view)
//                view?.startDrag(null, shadowBuilder, view, 0)
//                view?.setVisibility(View.INVISIBLE)
//                return true
//            }
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gestureDetector = GestureDetector(context!!, GestureListener())
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
        //view.setOnDragListener(this)

        closeButton.setOnClickListener {
            activity?.onBackPressed()
        }
        textView.text = this.javaClass.simpleName
    }

    companion object {
        @JvmStatic
        fun newInstance() = BottomFragment()
    }

    inner class GestureListener: GestureDetector.SimpleOnGestureListener() {

        override fun onDown(e: MotionEvent?): Boolean {
            initialX = view?.x!!
            initialY = view?.y!!
            Log.i("OnScroll", "InitialX = " + initialX)
            Log.i("OnScroll", "InitialY = " + initialY)
            return true
        }

        override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
            Log.i("OnScroll", "distanceX = " + distanceX + "distanceY = " + distanceY)
            scrolledY = distanceY
            return true
        }
    }
}
