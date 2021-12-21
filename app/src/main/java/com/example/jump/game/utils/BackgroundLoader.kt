package com.example.jump.game.utils

import android.content.Context
import com.example.jump.engine.Camera2D
import com.example.jump.engine.K2DGraphicsLayer
import com.example.jump.engine.Texture2D
import com.example.jump.engine.Vector2D

class BackgroundLoader(context: Context) {

    private val layerBackground = K2DGraphicsLayer(0)
    private val layerBackgroundCamera = Camera2D(0f, 0f, 0)

    private val background1 = Texture2D(context)
    private val background2 = Texture2D(context)

    fun loadBackgroundLayer(): K2DGraphicsLayer {
        background1.position = Vector2D(-124.5f, -133f)
        background2.position = Vector2D(-124.5f, 668f)//background image height is 801px

        //Create textures
        background1.createTexture("textures/background.png")
        background2.createTexture("textures/background.png")

        //Add textures to the layer
        layerBackground.addTexture(background1)
        layerBackground.addTexture(background2)
        layerBackground.setCamera(layerBackgroundCamera)

        return layerBackground
    }
}