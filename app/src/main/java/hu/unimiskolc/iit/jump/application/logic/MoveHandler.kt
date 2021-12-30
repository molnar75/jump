package hu.unimiskolc.iit.jump.application.logic

import hu.unimiskolc.iit.jump.application.PlayerState
import hu.unimiskolc.iit.jump.application.engine.GameObject
import hu.unimiskolc.iit.jump.application.engine.Vector2D
import hu.unimiskolc.iit.jump.application.game.DummyGame

class MoveHandler {

    private val gameItemTextureWidth = 61
    private val gameItemTextureHeight = 49
    private val screenWidth = 125
    private val screenHeight = 200
    private val backgroundHeight = 801
    private val groundHeight = 67
    private val platformHeight = 17
    private val platformDistance = 80

    private var speed = 0.5f

    fun updatePlayerPosition(dummyGame: DummyGame) {
        val gameItem = dummyGame.player
        val state = gameItem.state
        val position = gameItem.position

        val speed = 0.7f

        val waitSpriteIndex = 0
        val jumpSpriteIndex = 1
        val leftMoveSpriteIndex = 2
        val rightMoveSpriteIndex = 3

        when(state) {
            PlayerState.JUMP -> {
                dummyGame.gameStarted = true
                if (position.y < (screenHeight - gameItemTextureHeight)) {
                    gameItem.currSprite = jumpSpriteIndex

                    updateSprite(gameItem, 0f, 50f)

                    gameItem.state = PlayerState.FALL
                }
            }
            PlayerState.JUMP_LEFT -> {
                dummyGame.gameStarted = true
                if (position.y < (screenHeight - gameItemTextureHeight) && position.x > -screenWidth) {
                    gameItem.currSprite = jumpSpriteIndex

                    updateSprite(gameItem, -20f, 50f)

                    gameItem.state = PlayerState.FALL
                }
            }
            PlayerState.JUMP_RIGHT -> {
                dummyGame.gameStarted = true
                if (position.y < (screenHeight - gameItemTextureHeight) && position.x < (screenWidth - gameItemTextureWidth)) {
                    gameItem.currSprite = jumpSpriteIndex

                    updateSprite(gameItem, 20f, 50f)

                    gameItem.state = PlayerState.FALL
                }
            }
            PlayerState.LEFT -> {
                if (position.x > -screenWidth) {
                    gameItem.currSprite = leftMoveSpriteIndex

                    updateSprite(gameItem, -speed, 0f)
                }
            }
            PlayerState.RIGHT -> {
                if (position.x < (screenWidth - gameItemTextureWidth)) {
                    gameItem.currSprite = rightMoveSpriteIndex

                    updateSprite(gameItem, speed, 0f)
                }
            }
            PlayerState.FALL -> {
                if (position.y > -screenHeight) {
                    updateSprite(gameItem, 0f, -1f)
                }
            }
            PlayerState.FALL_LEFT -> {
                if (position.x > -screenWidth && position.y > -screenHeight) {
                    gameItem.currSprite = leftMoveSpriteIndex

                    updateSprite(gameItem, -speed, -1f)
                }
            }
            PlayerState.FALL_RIGHT -> {
                if (position.x < (screenWidth - gameItemTextureWidth) && position.y > -screenHeight) {
                    gameItem.currSprite = rightMoveSpriteIndex

                    updateSprite(gameItem, speed, -1f)
                }
            }
            PlayerState.WAIT -> {
                gameItem.currSprite = waitSpriteIndex

                updateSprite(gameItem, 0f, 0f)
            }
        }
    }

    private fun updateSprite(gameItem: GameObject,xSpeed: Float, ySpeed: Float) {
        val sprite = gameItem.mSprites[gameItem.currSprite]
        val pos = Vector2D(gameItem.position.x + xSpeed, gameItem.position.y + ySpeed)

        gameItem.position = pos
        sprite.setPosition(pos)
    }

    fun moveScene(dummyGame: DummyGame) {
        val backgroundShift = 795f
        val platformBottom = -screenHeight - platformHeight

        val layerBackground = dummyGame.layerBackground
        val layerGround = dummyGame.layerGround
        val gameItem = dummyGame.player

        //move background
        val backgroundTexture1 = layerBackground.getTexture(0)
        val backgroundTexture2 = layerBackground.getTexture(1)
        if (backgroundTexture1 != null && backgroundTexture2 != null) {
            val background1Y = backgroundTexture1.position.y
            val background2Y = backgroundTexture2.position.y

            if (background1Y > (-screenHeight - backgroundHeight)) {
                backgroundTexture1.position.y = background1Y - speed
            } else {
                backgroundTexture1.position.y =
                    background2Y + backgroundShift //put background on top of the other
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
        val platformList = dummyGame.layerPlatform.mObjectList
        for (platform in platformList) {
            val platformY = platform.position.y
            val platformSprite = platform.mSprites[platform.currSprite]
            var pos: Vector2D

            if (platformY > platformBottom) {
                pos = Vector2D(platform.position.x, platformY - speed)
                platform.position = pos
                platformSprite.setPosition(pos)
            } else {
                val side = (0..1).random()
                val x = if (side == 0) -110f else 10f //schoose platform side
                pos = Vector2D(x, dummyGame.highestPlatform.position.y + platformDistance)
                dummyGame.highestPlatform = platform
                platform.position = pos
                platformSprite.setPosition(pos)
            }
        }

        //move game item
        val gameItemY = gameItem.position.y
        if (gameItemY > -screenHeight) {
            val pos: Vector2D =
                if (gameItem.state == PlayerState.FALL ||
                    gameItem.state == PlayerState.FALL_LEFT ||
                    gameItem.state == PlayerState.FALL_RIGHT) {
                    Vector2D(gameItem.position.x, gameItemY - (speed + 0.5f))
                } else {
                    Vector2D(gameItem.position.x, gameItemY - speed)
                }
            val gameItemSprite = gameItem.mSprites[gameItem.currSprite]
            gameItem.position = pos
            gameItemSprite.setPosition(pos)
        } else {
            dummyGame.gameStarted = false
            dummyGame.endGame = true
        }
    }

    fun speedUpGame() {
        speed += 0.2f
    }
}