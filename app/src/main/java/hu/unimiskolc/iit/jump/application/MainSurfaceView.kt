package hu.unimiskolc.iit.jump.application

import android.content.Context
import android.opengl.GLSurfaceView
import android.view.MotionEvent
import hu.unimiskolc.iit.jump.application.game.MainRenderer
import hu.unimiskolc.iit.jump.application.logic.TouchHandler

class MainSurfaceView(context: Context) : GLSurfaceView(context) {

    private val mainRenderer: MainRenderer

    init {
        // Create an OpenGL ES 3.0 context
        setEGLContextClientVersion(3)

        // Set the Renderer for drawing on the GLSurfaceView
        mainRenderer = MainRenderer(context)
        setRenderer(mainRenderer)

        // Render the view only when there is a change in the drawing data
        //renderMode = GLSurfaceView.RENDERMODE_WHEN_DIRTY
    }

    override fun onTouchEvent(e: MotionEvent): Boolean {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.
        val gameItem = mainRenderer.dummyGame.player
        val touchHandler = TouchHandler()

        touchHandler.checkInput(e, gameItem, width)

        return true
    }
}