package com.example.jump.game

import android.content.Context
import com.example.jump.engine.*
import com.example.jump.logic.BoundingBoxHandler
import com.example.jump.logic.MoveHandler

class DummyGame(context: Context) {
    private val moveHandler = MoveHandler()
    private val boundingBoxHandler = BoundingBoxHandler()

    private val waiting: Sprite = Sprite(context, "textures/waiting/waiting_", 4, Vector2D(-30.5f, -133f), 6)
    private val jump: Sprite = Sprite(context, "textures/jump/jump_", 6, Vector2D(-30.5f, -133f), 6)
    private val left: Sprite = Sprite(context, "textures/left", 1, Vector2D(-30.5f, -133f), 6)
    private val right: Sprite = Sprite(context, "textures/right", 1, Vector2D(-30.5f, -133f), 6)

    val gameItem = GameObject(context, Vector2D(-30.5f, -133f))
    private val sceneManager = K2DSceneManager()
    private val scene = K2DScene()

    private val background1 = Texture2D(context)
    private val background2 = Texture2D(context)
    private val ground = Texture2D(context)

    private val platform1 = GameObject(context, Vector2D(10f, -65f))
    private val platform2 = GameObject(context, Vector2D(-110f, 15f))
    private val platform3 = GameObject(context, Vector2D(10f, 95f))
    private val platform4 = GameObject(context, Vector2D(-110f, 175f))
    private val platform5 = GameObject(context, Vector2D(10f, 255f))
    private val platform6 = GameObject(context, Vector2D(-110f, 335f))

    private val platformSprite1 = Sprite(context, "textures/platforms/platform_test", 1, Vector2D(10f, -65f), 6)
    private val platformSprite2 = Sprite(context, "textures/platforms/platform_test", 1, Vector2D(-110f, 15f), 6)
    private val platformSprite3 = Sprite(context, "textures/platforms/platform_test", 1, Vector2D(10f, 95f), 6)
    private val platformSprite4 = Sprite(context, "textures/platforms/platform_test", 1, Vector2D(-110f, 175f), 6)
    private val platformSprite5 = Sprite(context, "textures/platforms/platform_test", 1, Vector2D(10f, 255f), 6)
    private val platformSprite6 = Sprite(context, "textures/platforms/platform_test", 1, Vector2D(-110f, 335f), 6)

    val layerBackground = K2DGraphicsLayer("background", 0, 0.05f)
    val layerGround = K2DGraphicsLayer("ground", 1, 0f)
    val layerPlatform = K2DGraphicsLayer("platform", 2, 0.05f)
    private val layerPlayer = K2DGraphicsLayer("player", 3, 0.05f)

    private val layerBackgroundCamera = Camera2D(0f, 0f, 0)
    private val layerPlayerCamera = Camera2D(0f, 0f, 0)
    private val layerPlatformCamera = Camera2D(0f, 0f, 0)

    var gameStarted: Boolean = false
    var highestPlatform: GameObject

    init {
        //set the positions of the textures
        background1.position =  Vector2D(-124.5f, -133f)
        background2.position =  Vector2D(-124.5f, 668f) //background image height is 801px
        ground.position = Vector2D(-124.5f, -200f)

        //add sprites to game item
        gameItem.addSprite(waiting)
        gameItem.addSprite(jump)
        gameItem.addSprite(left)
        gameItem.addSprite(right)

        //Create textures
        background1.createTexture("textures/background.png")
        background2.createTexture("textures/background.png")
        ground.createTexture("textures/base_platform.png")

        platform1.addSprite(platformSprite1)
        platform2.addSprite(platformSprite2)
        platform3.addSprite(platformSprite3)
        platform4.addSprite(platformSprite4)
        platform5.addSprite(platformSprite5)
        platform6.addSprite(platformSprite6)

        //Add textures to the appropriate layer
        layerBackground.addTexture(background1)
        layerBackground.addTexture(background2)
        layerBackground.setCamera(layerBackgroundCamera)

        layerGround.addTexture(ground)

        layerPlatform.addGameObject(platform1)
        layerPlatform.addGameObject(platform2)
        layerPlatform.addGameObject(platform3)
        layerPlatform.addGameObject(platform4)
        layerPlatform.addGameObject(platform5)
        layerPlatform.addGameObject(platform6)
        layerPlatform.setCamera(layerPlatformCamera)

        highestPlatform = platform6

        layerPlayer.addGameObject(gameItem)
        layerPlayer.setCamera(layerPlayerCamera)

        //register layers
        scene.registerLayer(layerBackground)
        scene.registerLayer(layerGround)
        scene.registerLayer(layerPlatform)
        scene.registerLayer(layerPlayer)

        //register scene
        sceneManager.registerScene(scene)
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