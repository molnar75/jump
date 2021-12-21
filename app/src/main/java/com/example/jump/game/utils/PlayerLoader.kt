package com.example.jump.game.utils

import android.content.Context
import com.example.jump.engine.*

class PlayerLoader(context: Context) {
    private val layerPlayer = K2DGraphicsLayer(3)
    private val layerPlayerCamera = Camera2D(0f, 0f, 0)
    val player = GameObject(Vector2D(-30.5f, -133f))

    private val waiting: Sprite = Sprite(context, "textures/waiting/waiting_", 4, Vector2D(-30.5f, -133f), 6)
    private val jump: Sprite = Sprite(context, "textures/jump/jump_", 6, Vector2D(-30.5f, -133f), 6)
    private val left: Sprite = Sprite(context, "textures/left", 1, Vector2D(-30.5f, -133f), 6)
    private val right: Sprite = Sprite(context, "textures/right", 1, Vector2D(-30.5f, -133f), 6)

    fun loadPlayer(): K2DGraphicsLayer {
        //add sprites to game item
        player.addSprite(waiting)
        player.addSprite(jump)
        player.addSprite(left)
        player.addSprite(right)

        layerPlayer.addGameObject(player)
        layerPlayer.setCamera(layerPlayerCamera)

        return  layerPlayer
    }
}