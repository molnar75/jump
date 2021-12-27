package hu.unimiskolc.iit.jump.application.logic

import android.view.MotionEvent
import hu.unimiskolc.iit.jump.application.PlayerState
import hu.unimiskolc.iit.jump.application.engine.GameObject

class TouchHandler {
    fun checkInput(event: MotionEvent, gameItem: GameObject, width: Int, height: Int) {
        val x: Float = event.x
        val y: Float = event.y

        if (event.action == MotionEvent.ACTION_DOWN) {
            val oneThird = width / 3
            //check if the touch is jump or move left or right
            if (y < height / 2) {
                if (x < oneThird) {
                    gameItem.state = PlayerState.JUMP_LEFT
                } else if (x >= oneThird && x < 2 * oneThird) {
                    gameItem.state = PlayerState.JUMP
                } else {
                    gameItem.state = PlayerState.JUMP_RIGHT
                }
            } else {
                if (x < oneThird) {
                    if (gameItem.state == PlayerState.FALL) {
                        gameItem.state = PlayerState.FALL_LEFT
                    } else {
                        gameItem.state = PlayerState.LEFT
                    }
                } else if(x >= oneThird && x < 2 * oneThird) {
                    gameItem.state = PlayerState.JUMP
                } else {
                    if (gameItem.state == PlayerState.FALL) {
                        gameItem.state = PlayerState.FALL_RIGHT
                    } else {
                        gameItem.state = PlayerState.RIGHT
                    }
                }
            }
        } else if (event.action == MotionEvent.ACTION_UP) {
            if (gameItem.state != PlayerState.ON_PLATFORM) {
                gameItem.state = PlayerState.FALL
            }
        }
    }
}