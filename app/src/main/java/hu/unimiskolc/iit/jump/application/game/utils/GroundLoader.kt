package hu.unimiskolc.iit.jump.application.game.utils

import android.content.Context
import hu.unimiskolc.iit.jump.application.engine.K2DGraphicsLayer
import hu.unimiskolc.iit.jump.application.engine.Texture2D
import hu.unimiskolc.iit.jump.application.engine.Vector2D

class GroundLoader(context: Context) {
    private val texturePath = "textures/base_platform.png"

    private val ground = Texture2D(context)
    private val layerGround = K2DGraphicsLayer(1)

    fun loadGround():  K2DGraphicsLayer{
        ground.position = Vector2D(-124.5f, -200f)
        ground.createTexture(texturePath)
        layerGround.addTexture(ground)

        return layerGround
    }
}