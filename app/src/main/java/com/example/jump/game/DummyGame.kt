package com.example.jump.game

import android.content.Context
import com.example.jump.PlayerState
import com.example.jump.engine.*

class DummyGame(private val context: Context) {
    private val gameItemTextureWidth = 61
    private val gameItemTextureHeight = 49
    private val screenWidth = 125
    private val screenHeight = 200
    private val backgroundHeight = 801
    private val groundHeight = 67
    private val platformHeight = 17
    private val platformDistance = 80

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

    private val layerBackground = K2DGraphicsLayer("background", 0, 0.05f)
    private val layerGround = K2DGraphicsLayer("ground", 1, 0f)
    private val layerPlatform = K2DGraphicsLayer("platform", 2, 0.05f)
    private val layerPlayer = K2DGraphicsLayer("player", 3, 0.05f)

    private val layerBackgroundCamera = Camera2D(0f, 0f, 0)
    private val layerPlayerCamera = Camera2D(0f, 0f, 0)
    private val layerPlatformCamera = Camera2D(0f, 0f, 0)

    private var gameStarted: Boolean = false
    private var highestPlatform: GameObject

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
        updatePlayerPosition()
        if (gameStarted) {
            moveScene()
            checkBoundingBox()
        }
    }

    private fun updatePlayerPosition() {
        val state = gameItem.state
        val position = gameItem.position

        val speed = 0.7f

        val jumpSpriteIndex = 1
        val leftMoveSpriteIndex = 2
        val rightMoveSpriteIndex = 3

        when(state) {
            PlayerState.JUMP -> {
                gameStarted = true
                if(position.y < (screenHeight - gameItemTextureHeight)) {
                    gameItem.currSprite = jumpSpriteIndex
                    val sprite = gameItem.mSprites[gameItem.currSprite]
                    val pos = Vector2D(gameItem.position.x, gameItem.position.y + 50f)
                    gameItem.position = pos
                    sprite.setPosition(pos)
                    gameItem.state = PlayerState.FALL
                }
            }
            PlayerState.LEFT -> {
                if(position.x > -screenWidth) {
                    gameItem.currSprite = leftMoveSpriteIndex

                    val sprite = gameItem.mSprites[gameItem.currSprite]
                    val pos = Vector2D(gameItem.position.x - speed, gameItem.position.y)

                    gameItem.position = pos
                    sprite.setPosition(pos)
                }
            }
            PlayerState.RIGHT -> {
                if(position.x < (screenWidth - gameItemTextureWidth)) {
                    gameItem.currSprite = rightMoveSpriteIndex

                    val sprite = gameItem.mSprites[gameItem.currSprite]
                    val pos = Vector2D(gameItem.position.x + speed, gameItem.position.y)

                    gameItem.position = pos
                    sprite.setPosition(pos)
                }
            }
            PlayerState.FALL -> {
                if(position.y > -screenHeight) {
                    gameItem.currSprite = rightMoveSpriteIndex

                    val sprite = gameItem.mSprites[gameItem.currSprite]
                    val pos = Vector2D(gameItem.position.x, gameItem.position.y - 1f)

                    gameItem.position = pos
                    sprite.setPosition(pos)
                }
            }
            PlayerState.FALL_LEFT -> {
                if(position.x > -screenWidth) {
                    gameItem.currSprite = leftMoveSpriteIndex

                    val sprite = gameItem.mSprites[gameItem.currSprite]
                    val pos = Vector2D(gameItem.position.x - speed, gameItem.position.y - 1f)

                    gameItem.position = pos
                    sprite.setPosition(pos)
                }
            }
            PlayerState.FALL_RIGHT -> {
                if(position.x < (screenWidth - gameItemTextureWidth)) {
                    gameItem.currSprite = rightMoveSpriteIndex

                    val sprite = gameItem.mSprites[gameItem.currSprite]
                    val pos = Vector2D(gameItem.position.x + speed, gameItem.position.y - 1f)

                    gameItem.position = pos
                    sprite.setPosition(pos)
                }
            }
        }
    }

    private fun checkBoundingBox() {
        val playerBox = gameItem.getBoundingBox()
        val playerState = gameItem.state

        for (platform in layerPlatform.mObjectList) {
            val platformBox = platform.getBoundingBox()

            if (playerState != PlayerState.ON_PLATFORM) {
               if(playerBox.minpoint.y == platformBox.maxpoint.y &&
                       playerBox.maxpoint.x >= platformBox.minpoint.x &&
                       playerBox.minpoint.x <= platformBox.maxpoint.x) {
                   gameItem.state = PlayerState.ON_PLATFORM
               }
            }
        }
    }

    private fun moveScene() {
        val speed = 0.5f

        val backgroundShift = 795f

        val platformBottom = -screenHeight - platformHeight

        //move background
        val backgroundTexture1 = layerBackground.getTexture(0)
        val backgroundTexture2 = layerBackground.getTexture(1)
        if (backgroundTexture1 != null && backgroundTexture2 != null) {
            val background1Y = backgroundTexture1.position.y
            val background2Y = backgroundTexture2.position.y

            if (background1Y > (-screenHeight - backgroundHeight)) {
                backgroundTexture1.position.y = background1Y - speed
            } else {
                backgroundTexture1.position.y = background2Y + backgroundShift //put background on top of the other
            }

            if (background2Y > (-screenHeight - backgroundHeight)) {
                backgroundTexture2.position.y = background2Y - speed
            } else {
                backgroundTexture2.position.y = background1Y + backgroundShift
            }
        }

        //move ground
        val groundTexture = layerGround.getTexture(0)
        if (groundTexture != null) {
            val groundY = groundTexture.position.y
            if (groundY > (-screenHeight - groundHeight)) {
                groundTexture.position.y = groundTexture.position.y - speed
            } else {
                layerGround.clear()
            }
        }

        //move platforms
        val platformList = layerPlatform.mObjectList
        for (platform in platformList) {
            val platformY = platform.position.y
            val platformSprite = platform.mSprites[platform.currSprite]
            var pos: Vector2D

            if (platformY > platformBottom) {
                pos = Vector2D(platform.position.x, platformY - speed)
                platform.position = pos
                platformSprite.setPosition(pos)
            } else {
                pos = Vector2D(platform.position.x, highestPlatform.position.y + platformDistance)
                highestPlatform = platform
                platform.position = pos
                platformSprite.setPosition(pos)
            }
        }

        //move game item
        val gameItemY = gameItem.position.y
        if (gameItemY > -screenHeight) {
            val pos = Vector2D(gameItem.position.x, gameItemY - speed)
            val gameItemSprite = gameItem.mSprites[gameItem.currSprite]
            gameItem.position = pos
            gameItemSprite.setPosition(pos)
        } else {
            gameStarted = false
            //TODO endgame
        }
    }
}