package com.example.jump.logic

import android.view.MotionEvent
import com.example.jump.PlayerState
import com.example.jump.engine.GameObject

class TouchHandler {
    fun checkInput(event: MotionEvent, gameItem: GameObject, width: Int) {

        val x: Float = event.x

        if (event.action == MotionEvent.ACTION_DOWN) {
            val oneThird = width / 3
            if (x < oneThird) {
                if (gameItem.state == PlayerState.FALL) {
                    gameItem.state = PlayerState.FALL_LEFT
                } else {
                    gameItem.state = PlayerState.LEFT
                }
            } else if (x >= oneThird && x < 2 * oneThird) {
                gameItem.state = PlayerState.JUMP
            } else if (gameItem.state == PlayerState.FALL) {
                gameItem.state = PlayerState.FALL_RIGHT
            } else {
                gameItem.state = PlayerState.RIGHT
            }
        } else if (event.action == MotionEvent.ACTION_UP) {
            if (gameItem.state == PlayerState.ON_PLATFORM) {
                gameItem.state = PlayerState.WAIT
            } else {
                gameItem.state = PlayerState.FALL
            }
        }
    }
}