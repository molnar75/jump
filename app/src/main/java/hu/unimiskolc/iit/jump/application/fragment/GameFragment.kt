package hu.unimiskolc.iit.jump.application.fragment

import android.app.Activity
import android.opengl.GLSurfaceView
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import hu.unimiskolc.iit.jump.application.MainSurfaceView
import hu.unimiskolc.iit.jump.application.R
import hu.unimiskolc.iit.jump.core.domain.Score
import org.koin.android.ext.android.inject

class GameFragment : Fragment() {

    private lateinit var gLView: GLSurfaceView
    private lateinit var secondLayerView: View

    private val viewModel: GameViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Create a GLSurfaceView instance and set it
        // as the View for this Fragment.
        gLView = MainSurfaceView(requireContext(), this)

        //Set the score layout on top of the surface view
        secondLayerView =
            LayoutInflater.from(requireContext()).inflate(R.layout.score_layer, null, false)

        val lp = RelativeLayout.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT)

        (context as Activity).addContentView(secondLayerView, lp)

        val scoreText = secondLayerView.findViewById(R.id.score) as TextView
        scoreText.text = "0"

        return gLView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun endGame(score: Score) {
        (secondLayerView.parent as ViewGroup).removeView(secondLayerView)
        viewModel.endGame(score)
    }

    fun updateScore(score: Int) {
        val scoreText = secondLayerView.findViewById(R.id.score) as TextView
        scoreText.text = score.toString()
    }
}