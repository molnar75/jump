package hu.unimiskolc.iit.jump.application.game.utils

import android.content.Context
import hu.unimiskolc.iit.jump.application.engine.Camera2D
import hu.unimiskolc.iit.jump.application.engine.K2DGraphicsLayer
import hu.unimiskolc.iit.jump.application.engine.Texture2D
import hu.unimiskolc.iit.jump.application.engine.Vector2D

class BackgroundLoader(context: Context) {
    private val texturePath = "textures/background.png"

    private val layerBackground = K2DGraphicsLayer(0)
    private val layerBackgroundCamera = Camera2D(0f, 0f, 0)

    private val background1 = Texture2D(context)
    private val background2 = Texture2D(context)

    fun loadBackgroundLayer(): K2DGraphicsLayer {
        background1.position = Vector2D(-124.5f, -133f)
        background2.position = Vector2D(-124.5f, 668f)//background image height is 801px

        //Create textures
        background1.createTexture(texturePath)
        background2.createTexture(texturePath)

        //Add textures to the layer
        layerBackground.addTexture(background1)
        layerBackground.addTexture(background2)
        layerBackground.setCamera(layerBackgroundCamera)

        return layerBackground
    }
}