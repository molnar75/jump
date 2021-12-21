package hu.unimiskolc.iit.jump.logic

import hu.unimiskolc.iit.jump.game.DummyGame

class BoundingBoxHandler {
    fun checkBoundingBox(dummyGame: DummyGame) {
        val playerBox = dummyGame.player.getBoundingBox()
        val playerState = dummyGame.player.state

        for (platform in dummyGame.layerPlatform.mObjectList) {
            val platformBox = platform.getBoundingBox()

            if (playerState != hu.unimiskolc.iit.jump.PlayerState.ON_PLATFORM &&
                playerState != hu.unimiskolc.iit.jump.PlayerState.RIGHT &&
                playerState != hu.unimiskolc.iit.jump.PlayerState.LEFT) {
                if (isYMatch(playerBox, platformBox)
                ) {
                    if (((playerBox.maxpoint.x - 20) >= platformBox.minpoint.x && (playerBox.maxpoint.x - 20) <= platformBox.maxpoint.x) ||
                        ((playerBox.minpoint.x + 20) >= platformBox.minpoint.x && (playerBox.minpoint.x + 20) <= platformBox.maxpoint.x)
                    ) {
                        dummyGame.player.state = hu.unimiskolc.iit.jump.PlayerState.ON_PLATFORM
                    }
                }
            }
            if (playerState == hu.unimiskolc.iit.jump.PlayerState.RIGHT || playerState == hu.unimiskolc.iit.jump.PlayerState.LEFT) {
                if (isYMatch(playerBox, platformBox)) {
                    if (playerState == hu.unimiskolc.iit.jump.PlayerState.RIGHT && ((playerBox.minpoint.x + 20) >= platformBox.maxpoint.x)) {
                        dummyGame.player.state = hu.unimiskolc.iit.jump.PlayerState.FALL_RIGHT
                    }
                    if (playerState == hu.unimiskolc.iit.jump.PlayerState.LEFT && ((playerBox.maxpoint.x - 20) <= platformBox.minpoint.x)) {
                        dummyGame.player.state = hu.unimiskolc.iit.jump.PlayerState.FALL_LEFT
                    }
                }
            }
        }
    }

    //Checks if the players y coordinate is in the platforms given range
    private fun isYMatch(playerBox: hu.unimiskolc.iit.jump.engine.BoundingBox2D, platformBox: hu.unimiskolc.iit.jump.engine.BoundingBox2D): Boolean {
        if (playerBox.minpoint.y >= (platformBox.maxpoint.y - 10) &&
            playerBox.minpoint.y <= platformBox.maxpoint.y) {
            return true
        }

        return false
    }
}