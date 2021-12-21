package hu.unimiskolc.iit.jump.application.logic

import android.view.MotionEvent
import hu.unimiskolc.iit.jump.application.PlayerState
import hu.unimiskolc.iit.jump.application.engine.GameObject

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
            if (gameItem.state != PlayerState.ON_PLATFORM) {
                gameItem.state = PlayerState.FALL
            }
        }
    }
}