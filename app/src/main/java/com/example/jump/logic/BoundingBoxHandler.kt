package com.example.jump.logic

import com.example.jump.PlayerState
import com.example.jump.engine.BoundingBox2D
import com.example.jump.game.DummyGame

class BoundingBoxHandler {
    fun checkBoundingBox(dummyGame: DummyGame) {
        val playerBox = dummyGame.gameItem.getBoundingBox()
        val playerState = dummyGame.gameItem.state

        for (platform in dummyGame.layerPlatform.mObjectList) {
            val platformBox = platform.getBoundingBox()

            if (playerState != PlayerState.ON_PLATFORM &&
                playerState != PlayerState.RIGHT &&
                playerState != PlayerState.LEFT) {
                if (isYMatch(playerBox, platformBox)
                ) {
                    if (((playerBox.maxpoint.x - 20) >= platformBox.minpoint.x && (playerBox.maxpoint.x - 20) <= platformBox.maxpoint.x) ||
                        ((playerBox.minpoint.x + 20) >= platformBox.minpoint.x && (playerBox.minpoint.x + 20) <= platformBox.maxpoint.x)
                    ) {
                        dummyGame.gameItem.state = PlayerState.ON_PLATFORM
                    }
                }
            }
            if (playerState == PlayerState.RIGHT || playerState == PlayerState.LEFT) {
                if (isYMatch(playerBox, platformBox)) {
                    if (playerState == PlayerState.RIGHT && ((playerBox.minpoint.x + 20) >= platformBox.maxpoint.x)) {
                        dummyGame.gameItem.state = PlayerState.FALL_RIGHT
                    }
                    if (playerState == PlayerState.LEFT && ((playerBox.maxpoint.x - 20) <= platformBox.minpoint.x)) {
                        dummyGame.gameItem.state = PlayerState.FALL_LEFT
                    }
                }
            }
        }
    }

    //Checks if the players y coordinate is in the platforms given range
    private fun isYMatch(playerBox: BoundingBox2D, platformBox: BoundingBox2D): Boolean {
        if (playerBox.minpoint.y >= (platformBox.maxpoint.y - 10) &&
            playerBox.minpoint.y <= platformBox.maxpoint.y) {
            return true
        }

        return false
    }
}