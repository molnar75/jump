package hu.unimiskolc.iit.jump.application.game

import android.content.Context
import hu.unimiskolc.iit.jump.application.MainSurfaceView
import hu.unimiskolc.iit.jump.application.engine.*
import hu.unimiskolc.iit.jump.application.game.utils.SceneLoader
import hu.unimiskolc.iit.jump.application.logic.BoundingBoxHandler
import hu.unimiskolc.iit.jump.application.logic.MoveHandler

class DummyGame(context: Context) {
    private val moveHandler = MoveHandler()
    private val boundingBoxHandler = BoundingBoxHandler()

    private val sceneManager = K2DSceneManager()
    private val sceneLoader = SceneLoader(context)

    var gameStarted: Boolean = false
    var endGame: Boolean = false
    var highestPlatform = sceneLoader.layerPlatform.mObjectList[5]
    var playerScore = 0f

    private var previousPlatform: GameObject? = null
    private lateinit var  mainSurfaceView: MainSurfaceView

    val player = sceneLoader.layerPlayer.mObjectList[0]
    val layerPlatform = sceneLoader.layerPlatform
    val layerBackground = sceneLoader.layerBackground
    val layerGround = sceneLoader.layerGround

    init {
        //register scene
        sceneManager.registerScene(sceneLoader.loadScene())
    }

    fun render(renderer: MainRenderer, surfaceView: MainSurfaceView) {
        mainSurfaceView = surfaceView
        sceneManager.render(renderer)
        moveHandler.updatePlayerPosition(this)
        if (gameStarted) {
            moveHandler.moveScene(this)
            boundingBoxHandler.checkBoundingBox(this)
        }
        if (endGame) {
            surfaceView.endGame()
        }
    }

    fun cleanup() {
        sceneManager.cleanup()
    }

    fun updatePlayerScore(platform: GameObject) {
        if (previousPlatform != null) {
            val previousY = previousPlatform!!.position.y
            val y = platform.position.y
            if (previousY < y) {
                val intermediatePlatformNumber = getIntermediatePlatformNumber(previousY, y)
                playerScore += intermediatePlatformNumber * 10
                previousPlatform = platform
            }
        } else {
            playerScore += 10
            previousPlatform = platform
        }
        mainSurfaceView.updateScore(playerScore.toInt())
    }

    private fun getIntermediatePlatformNumber(previousPosition: Float, position: Float): Int {
        var intermediatePlatformNumber = 1

        for (platform in layerPlatform.mObjectList) {
            val y = platform.position.y
            if(y > previousPosition && y < position) {
                intermediatePlatformNumber += 1
            }
        }

        return intermediatePlatformNumber
    }
}