package hu.unimiskolc.iit.jump.application.logic

import android.app.Activity
import android.content.Context
import android.view.MotionEvent
import android.view.View
import androidx.navigation.Navigation
import hu.unimiskolc.iit.jump.application.PlayerState
import hu.unimiskolc.iit.jump.application.R
import hu.unimiskolc.iit.jump.application.engine.GameObject
import hu.unimiskolc.iit.jump.application.game.DummyGame

class TouchHandler {
    fun checkInput(event: MotionEvent, player: GameObject, width: Int, height: Int) {
        val x: Float = event.x
        val y: Float = event.y

        if (event.action == MotionEvent.ACTION_DOWN) {
            val oneThird = width / 3
            //check if the touch is jump or move left or right
            if (y < height / 2) {
                if (x < oneThird) {
                    player.state = PlayerState.JUMP_LEFT
                } else if (x >= oneThird && x < 2 * oneThird) {
                    player.state = PlayerState.JUMP
                } else {
                    player.state = PlayerState.JUMP_RIGHT
                }
            } else {
                if (x < oneThird) {
                    if (player.state == PlayerState.FALL) {
                        player.state = PlayerState.FALL_LEFT
                    } else {
                        player.state = PlayerState.LEFT
                    }
                } else if(x >= oneThird && x < 2 * oneThird) {
                    player.state = PlayerState.JUMP
                } else {
                    if (player.state == PlayerState.FALL) {
                        player.state = PlayerState.FALL_RIGHT
                    } else {
                        player.state = PlayerState.RIGHT
                    }
                }
            }
        } else if (event.action == MotionEvent.ACTION_UP) {
            if (player.state != PlayerState.ON_PLATFORM) {
                player.state = PlayerState.FALL
            }
        }
    }
}