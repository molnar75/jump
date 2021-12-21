package com.example.jump.game

import android.content.Context
import com.example.jump.engine.*
import com.example.jump.game.utils.SceneLoader
import com.example.jump.logic.BoundingBoxHandler
import com.example.jump.logic.MoveHandler

class DummyGame(context: Context) {
    private val moveHandler = MoveHandler()
    private val boundingBoxHandler = BoundingBoxHandler()

    private val sceneManager = K2DSceneManager()
    private val sceneLoader = SceneLoader(context)

    var gameStarted: Boolean = false
    var highestPlatform = sceneLoader.layerPlatform.mObjectList[5]

    val player = sceneLoader.layerPlayer.mObjectList[0]
    val layerPlatform = sceneLoader.layerPlatform
    val layerBackground = sceneLoader.layerBackground
    val layerGround = sceneLoader.layerGround

    init {
        //register scene
        sceneManager.registerScene(sceneLoader.loadScene())
    }

    fun render(renderer: MainRenderer) {
        sceneManager.render(renderer)
        moveHandler.updatePlayerPosition(this)
        if (gameStarted) {
            moveHandler.moveScene(this)
            boundingBoxHandler.checkBoundingBox(this)
        }
    }
}