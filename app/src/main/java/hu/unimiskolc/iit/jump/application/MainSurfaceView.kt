package hu.unimiskolc.iit.jump.application

import android.app.Activity
import android.content.Context
import android.opengl.GLSurfaceView
import android.view.MotionEvent
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import hu.unimiskolc.iit.jump.application.fragment.GameFragment
import hu.unimiskolc.iit.jump.application.game.MainRenderer
import hu.unimiskolc.iit.jump.application.logic.TouchHandler
import hu.unimiskolc.iit.jump.core.domain.Score
import java.util.*

class MainSurfaceView(context: Context, private val gameFragment: GameFragment) : GLSurfaceView(context) {

    private val mainRenderer: MainRenderer

    init {
        // Create an OpenGL ES 3.0 context
        setEGLContextClientVersion(3)

        // Set the Renderer for drawing on the GLSurfaceView
        mainRenderer = MainRenderer(context, this)
        setRenderer(mainRenderer)

        // Render the view only when there is a change in the drawing data
        //renderMode = GLSurfaceView.RENDERMODE_WHEN_DIRTY
    }

    override fun onTouchEvent(e: MotionEvent): Boolean {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.
        val player = mainRenderer.dummyGame.player
        val touchHandler = TouchHandler()

        touchHandler.checkInput(e, player, width, height)

        return true
    }

    fun endGame() {
        mainRenderer.cleanup()
        renderMode = 0
        val score = Score(0, Calendar.getInstance().time, mainRenderer.dummyGame.playerScore.toInt())
        (context as Activity).runOnUiThread() {
            gameFragment.endGame(score)
            val bundle = bundleOf("score" to score.value)
            findNavController().navigate(R.id.action_gameFragment_to_endGameFragment, bundle)
        }
    }

    fun updateScore(score: Int) {
        (context as Activity).runOnUiThread() {
            gameFragment.updateScore(score)
        }
    }
}