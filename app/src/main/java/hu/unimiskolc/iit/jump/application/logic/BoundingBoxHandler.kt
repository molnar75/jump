package hu.unimiskolc.iit.jump.application.logic

import hu.unimiskolc.iit.jump.application.PlayerState
import hu.unimiskolc.iit.jump.application.game.DummyGame

class BoundingBoxHandler {
    fun checkBoundingBox(dummyGame: DummyGame) {
        val playerBox = dummyGame.player.getBoundingBox()
        val playerState = dummyGame.player.state

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
                        dummyGame.player.state = PlayerState.ON_PLATFORM
                        dummyGame.updatePlayerScore(platform)
                    }
                }
            }
            if (playerState == PlayerState.RIGHT || playerState == PlayerState.LEFT) {
                if (isYMatch(playerBox, platformBox)) {
                    if (playerState == PlayerState.RIGHT && ((playerBox.minpoint.x + 20) >= platformBox.maxpoint.x)) {
                        dummyGame.player.state = PlayerState.FALL_RIGHT
                    }
                    if (playerState == PlayerState.LEFT && ((playerBox.maxpoint.x - 20) <= platformBox.minpoint.x)) {
                        dummyGame.player.state = PlayerState.FALL_LEFT
                    }
                }
            }
        }
    }

    //Checks if the players y coordinate is in the platforms given range
    private fun isYMatch(playerBox: hu.unimiskolc.iit.jump.application.engine.BoundingBox2D, platformBox: hu.unimiskolc.iit.jump.application.engine.BoundingBox2D): Boolean {
        if (playerBox.minpoint.y >= (platformBox.maxpoint.y - 10) &&
            playerBox.minpoint.y <= platformBox.maxpoint.y) {
            return true
        }

        return false
    }
}