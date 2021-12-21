package hu.unimiskolc.iit.jump.game.utils

import android.content.Context
import hu.unimiskolc.iit.jump.engine.K2DGraphicsLayer
import hu.unimiskolc.iit.jump.engine.Texture2D
import hu.unimiskolc.iit.jump.engine.Vector2D

class GroundLoader(context: Context) {
    private val ground = Texture2D(context)
    private val layerGround = K2DGraphicsLayer(1)

    fun loadGround():  K2DGraphicsLayer{
        ground.position = Vector2D(-124.5f, -200f)
        ground.createTexture("textures/base_platform.png")
        layerGround.addTexture(ground)

        return layerGround
    }
}