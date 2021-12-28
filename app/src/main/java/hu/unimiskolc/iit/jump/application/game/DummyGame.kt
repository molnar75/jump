package hu.unimiskolc.iit.jump.application.game

import android.content.Context
import hu.unimiskolc.iit.jump.application.MainSurfaceView
import hu.unimiskolc.iit.jump.application.engine.*
import hu.unimiskolc.iit.jump.application.game.utils.SceneLoader
import hu.unimiskolc.iit.jump.application.logic.BoundingBoxHandler
import hu.unimiskolc.iit.jump.application.logic.MoveHandler

class DummyGame(private val context: Context) {
    private val moveHandler = MoveHandler()
    private val boundingBoxHandler = BoundingBoxHandler()

    private val sceneManager = K2DSceneManager()
    private val sceneLoader = SceneLoader(context)

    var gameStarted: Boolean = false
    var endGame: Boolean = false
    var highestPlatform = sceneLoader.layerPlatform.mObjectList[5]
    var playerScore = 0f
    private var previousYPosition: Float = -133f

    val player = sceneLoader.layerPlayer.mObjectList[0]
    val layerPlatform = sceneLoader.layerPlatform
    val layerBackground = sceneLoader.layerBackground
    val layerGround = sceneLoader.layerGround

    init {
        //register scene
        sceneManager.registerScene(sceneLoader.loadScene())
    }

    fun render(renderer: MainRenderer, surfaceView: MainSurfaceView) {
        sceneManager.render(renderer)
        moveHandler.updatePlayerPosition(this)
        if (gameStarted) {
            moveHandler.moveScene(this)
            boundingBoxHandler.checkBoundingBox(this)
        }
        updatePlayerScore(surfaceView)
        if (endGame) {
            surfaceView.endGame()
        }
    }

    fun cleanup() {
        sceneManager.cleanup()
    }

    private fun updatePlayerScore(surfaceView: MainSurfaceView) {
        val y = player.position.y
        if (y > previousYPosition) {
            previousYPosition = y
            playerScore = (y + 133f) / 10
            surfaceView.updateScore(playerScore.toInt())
        }
    }
}