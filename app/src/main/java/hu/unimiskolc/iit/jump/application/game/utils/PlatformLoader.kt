package hu.unimiskolc.iit.jump.application.game.utils

import android.content.Context
import hu.unimiskolc.iit.jump.application.engine.*

class PlatformLoader(context: Context) {

    private val layerPlatform = K2DGraphicsLayer(2)
    private val layerPlatformCamera = Camera2D(0f, 0f, 0)

    private val platform1 = GameObject(Vector2D(10f, -65f))
    private val platform2 = GameObject(Vector2D(-110f, 15f))
    private val platform3 = GameObject(Vector2D(10f, 95f))
    private val platform4 = GameObject(Vector2D(-110f, 175f))
    private val platform5 = GameObject(Vector2D(10f, 255f))
    private val platform6 = GameObject(Vector2D(-110f, 335f))

    private val platformSprite1 = Sprite(context, "textures/platforms/platform_test", 1, Vector2D(10f, -65f), 6)
    private val platformSprite2 = Sprite(context, "textures/platforms/platform_test", 1, Vector2D(-110f, 15f), 6)
    private val platformSprite3 = Sprite(context, "textures/platforms/platform_test", 1, Vector2D(10f, 95f), 6)
    private val platformSprite4 = Sprite(context, "textures/platforms/platform_test", 1, Vector2D(-110f, 175f), 6)
    private val platformSprite5 = Sprite(context, "textures/platforms/platform_test", 1, Vector2D(10f, 255f), 6)
    private val platformSprite6 = Sprite(context, "textures/platforms/platform_test", 1, Vector2D(-110f, 335f), 6)

    fun loadPlatforms(): K2DGraphicsLayer{
        platform1.addSprite(platformSprite1)
        platform2.addSprite(platformSprite2)
        platform3.addSprite(platformSprite3)
        platform4.addSprite(platformSprite4)
        platform5.addSprite(platformSprite5)
        platform6.addSprite(platformSprite6)

        layerPlatform.addGameObject(platform1)
        layerPlatform.addGameObject(platform2)
        layerPlatform.addGameObject(platform3)
        layerPlatform.addGameObject(platform4)
        layerPlatform.addGameObject(platform5)
        layerPlatform.addGameObject(platform6)
        layerPlatform.setCamera(layerPlatformCamera)

        return layerPlatform
    }
}