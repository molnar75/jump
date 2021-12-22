package hu.unimiskolc.iit.jump.application.fragment

import android.opengl.GLSurfaceView
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.widget.LinearLayoutCompat
import hu.unimiskolc.iit.jump.application.MainSurfaceView
import hu.unimiskolc.iit.jump.application.R

class GameFragment : Fragment() {

    private lateinit var gLView: GLSurfaceView

    companion object {
        fun newInstance() = GameFragment()
    }

    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Create a GLSurfaceView instance and set it
        // as the View for this Fragment.
        gLView = MainSurfaceView(requireContext())

        /*
        //Set the score layout on top of the surface view
        val lp = RelativeLayout.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT)
        val secondLayerView: View =
            LayoutInflater.from(requireContext()).inflate(R.layout.test, null, false)
        addContentView(secondLayerView, lp)*/

        return gLView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        // TODO: Use the ViewModel
    }

}