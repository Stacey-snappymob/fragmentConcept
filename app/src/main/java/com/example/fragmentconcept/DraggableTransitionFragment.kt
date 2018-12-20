package com.example.fragmentconcept


import android.os.Bundle
import android.transition.Scene
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_draggable_transition.*


/**
 * A simple [Fragment] subclass.
 *
 */
class DraggableTransitionFragment : Fragment() {
    private lateinit var scene1: Scene
    private lateinit var scene2: Scene

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_draggable_transition, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        closeButton.setOnClickListener {
            activity?.onBackPressed()
        }

        animateButton.setOnClickListener {
            TransitionManager.go(scene2)
        }

        scene1 = Scene.getSceneForLayout(sceneLayout, R.layout.scene_1, context)
        scene2 = Scene.getSceneForLayout(sceneLayout, R.layout.scene_2, context)
    }

    companion object {
        @JvmStatic
        fun newInstance() = DraggableTransitionFragment()
    }

}
