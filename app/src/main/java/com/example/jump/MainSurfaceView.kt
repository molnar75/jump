package com.example.jump

import android.content.Context
import android.opengl.GLSurfaceView
import android.view.MotionEvent
import com.example.jump.game.MainRenderer

private const val TOUCH_SCALE_FACTOR: Float = 180.0f / 320f

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
        val x: Float = e.x
        val gameItem = mainRenderer.dummyGame.gameItem

        if(e.action == MotionEvent.ACTION_DOWN) {
                val oneThird = width / 3
                if (x < oneThird) {
                    if (gameItem.state == PlayerState.FALL) {
                        gameItem.state = PlayerState.FALL_LEFT
                    } else {
                        if (gameItem.state == PlayerState.ON_PLATFORM) {
                            gameItem.state = PlayerState.LEFT
                        }
                    }
                } else if (x >= oneThird && x < 2 * oneThird) {
                    gameItem.state = PlayerState.JUMP
                } else if(gameItem.state == PlayerState.FALL) {
                    gameItem.state = PlayerState.FALL_RIGHT
                } else {
                    if (gameItem.state == PlayerState.ON_PLATFORM) {
                        gameItem.state = PlayerState.RIGHT
                    }
                }
            } else if (e.action == MotionEvent.ACTION_UP) {
                if (gameItem.state != PlayerState.FALL) {
                    gameItem.state = PlayerState.WAIT
                }
            }
        return true
    }
}