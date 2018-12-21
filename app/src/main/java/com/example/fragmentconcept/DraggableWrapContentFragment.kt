package com.example.fragmentconcept


import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_bottom.*


/**
 * A simple [Fragment] subclass.
 * Use the [DraggableWrapContentFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class DraggableWrapContentFragment : Fragment(), View.OnTouchListener, ViewTreeObserver.OnGlobalLayoutListener {
    private var viewMeasuredHeight: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_draggable_wrap_content, container, false)
        view.viewTreeObserver.addOnGlobalLayoutListener(this)

        // Modify fragment width needs to be done here, before the view was drawn
        val layoutParams = view.layoutParams as FrameLayout.LayoutParams
        val deviceWidth = Resources.getSystem().displayMetrics.widthPixels
        val desiredDisplayWidth = deviceWidth * 0.6
        val widthMargin = ((deviceWidth - desiredDisplayWidth) / 2).toInt()
        layoutParams.setMargins(widthMargin, 0, widthMargin, 0)
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
    }

    override fun onTouch(view: View, event: MotionEvent): Boolean {
        val visibleContentHeight = (Resources.getSystem().displayMetrics.heightPixels - toolbarHeight() - statusBarHeight())

        when (event.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_DOWN -> {
                // This is called when user put their finger on screen for dragging
                Log.i("DOWN","eventY:DOWN: " + event.y)
                Log.i("DOWN","rawY:DOWN: " + event.rawY)
                Log.i("DOWN","viewY:DOWN: " + view.top)
            }

            MotionEvent.ACTION_MOVE -> {
                
                Log.i("MOVE","eventY:MOVE: " + event.y)
                Log.i("MOVE","rawY:MOVE: " + event.rawY)
                Log.i("MOVE","viewY:MOVE: " + view.top)
                val maxYPosition = visibleContentHeight - viewMeasuredHeight!!
                // event.y refers to the changes of y position
                val calculatedYPosition = (view.top + event.y).toInt()

                if (calculatedYPosition <= maxYPosition) {
                    view.top = maxYPosition
                } else {
                    view.top = calculatedYPosition
                }

                view.top = event.rawY.toInt()

            }

            // When user remove their finger from screen
            MotionEvent.ACTION_UP -> {
                Log.i("UP","eventY:UP: " + event.y)
                Log.i("UP","rawY:UP: " + event.rawY)
                Log.i("UP","viewY:UP: " + view.top)
                // Dismiss the fragment if it is less than half of the height of view
                if (visibleContentHeight - view.top < (viewMeasuredHeight!! * 0.5)){
                    activity?.onBackPressed()
                }
            }
        }
        return true
    }

    // This is called before onViewCreated()
    override fun onGlobalLayout() {
        view?.let {view ->
            view.viewTreeObserver.removeOnGlobalLayoutListener(this)
            viewMeasuredHeight = view.measuredHeight
            // Add off set to the view, in this case, only show 50% of the fragment
            view.offsetTopAndBottom(viewMeasuredHeight!! / 2)
        }
    }

    fun toolbarHeight(): Int {
        val attrs = intArrayOf(R.attr.actionBarSize)
        val typedArray = context!!.obtainStyledAttributes(attrs)
        val toolBarHeight = typedArray.getDimensionPixelSize(0, -1)
        typedArray.recycle()
        return toolBarHeight
    }

    fun statusBarHeight(): Int {
        var result = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    companion object {
        @JvmStatic
        fun newInstance() = DraggableWrapContentFragment()
    }
}
