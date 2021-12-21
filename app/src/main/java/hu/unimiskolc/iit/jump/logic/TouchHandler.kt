package hu.unimiskolc.iit.jump.logic

import android.view.MotionEvent
import hu.unimiskolc.iit.jump.engine.GameObject

class TouchHandler {
    fun checkInput(event: MotionEvent, gameItem: GameObject, width: Int) {
        val x: Float = event.x

        if (event.action == MotionEvent.ACTION_DOWN) {
            val oneThird = width / 3
            if (x < oneThird) {
                if (gameItem.state == hu.unimiskolc.iit.jump.PlayerState.FALL) {
                    gameItem.state = hu.unimiskolc.iit.jump.PlayerState.FALL_LEFT
                } else {
                    gameItem.state = hu.unimiskolc.iit.jump.PlayerState.LEFT
                }
            } else if (x >= oneThird && x < 2 * oneThird) {
                gameItem.state = hu.unimiskolc.iit.jump.PlayerState.JUMP
            } else if (gameItem.state == hu.unimiskolc.iit.jump.PlayerState.FALL) {
                gameItem.state = hu.unimiskolc.iit.jump.PlayerState.FALL_RIGHT
            } else {
                gameItem.state = hu.unimiskolc.iit.jump.PlayerState.RIGHT
            }
        } else if (event.action == MotionEvent.ACTION_UP) {
            if (gameItem.state != hu.unimiskolc.iit.jump.PlayerState.ON_PLATFORM) {
                gameItem.state = hu.unimiskolc.iit.jump.PlayerState.FALL
            }
        }
    }
}