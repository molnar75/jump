package hu.unimiskolc.iit.jump.application.game.utils

import android.content.Context
import hu.unimiskolc.iit.jump.application.engine.K2DScene

class SceneLoader(context: Context) {
    //TODO load parameters to layers from file
    val layerBackground = BackgroundLoader(context).loadBackgroundLayer()
    val layerPlatform = PlatformLoader(context).loadPlatforms()
    val layerPlayer = PlayerLoader(context).loadPlayer()
    val layerGround = GroundLoader(context).loadGround()

    fun loadScene(): K2DScene {
        val scene = K2DScene()

        scene.registerLayer(layerBackground)
        scene.registerLayer(layerPlatform)
        scene.registerLayer(layerPlayer)
        scene.registerLayer(layerGround)

        return scene
    }
}