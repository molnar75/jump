package com.example.jump.logic

import com.example.jump.PlayerState
import com.example.jump.game.DummyGame

class BoundingBoxHandler {
    fun checkBoundingBox(dummyGame: DummyGame) {
        val playerBox = dummyGame.gameItem.getBoundingBox()
        val playerState = dummyGame.gameItem.state

        for (platform in dummyGame.layerPlatform.mObjectList) {
            val platformBox = platform.getBoundingBox()

            if (playerState != PlayerState.ON_PLATFORM &&
                playerState != PlayerState.RIGHT &&
                playerState != PlayerState.LEFT &&
                playerBox.minpoint.y >= (platformBox.maxpoint.y - 10) &&
                playerBox.minpoint.y <= platformBox.maxpoint.y) {
                if (((playerBox.maxpoint.x - 20) >= platformBox.minpoint.x && (playerBox.maxpoint.x - 20) <= platformBox.maxpoint.x) ||
                    ((playerBox.minpoint.x + 20) >= platformBox.minpoint.x && (playerBox.minpoint.x + 20) <= platformBox.maxpoint.x)) {
                    dummyGame.gameItem.state = PlayerState.ON_PLATFORM
                }
            } else {
                if (((playerBox.maxpoint.x - 20) <= platformBox.minpoint.x && (playerBox.maxpoint.x - 20) >= platformBox.maxpoint.x) ||
                    ((playerBox.minpoint.x + 20) <= platformBox.minpoint.x && (playerBox.minpoint.x + 20) >= platformBox.maxpoint.x)) {
                    when (playerState) {
                        PlayerState.LEFT -> {
                            dummyGame.gameItem.state = PlayerState.FALL_LEFT
                        }
                        PlayerState.RIGHT -> {
                            dummyGame.gameItem.state = PlayerState.FALL_RIGHT
                        }
                        else -> {
                            dummyGame.gameItem.state = PlayerState.FALL
                        }
                    }
                }
            }
        }
    }
}