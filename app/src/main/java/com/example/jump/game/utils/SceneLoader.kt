package com.example.jump.game.utils

import android.content.Context
import com.example.jump.engine.K2DScene
import com.example.jump.game.utils.BackgroundLoader
import com.example.jump.game.utils.GroundLoader
import com.example.jump.game.utils.PlatformLoader
import com.example.jump.game.utils.PlayerLoader

class SceneLoader(context: Context) {
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