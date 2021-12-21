package com.example.jump.engine

import com.example.jump.game.MainRenderer

class K2DSceneManager {

    private val mScenes: ArrayList<K2DScene> = ArrayList()

    fun registerScene(scene: K2DScene){
        mScenes.add(scene)
    }

    fun render(renderer: MainRenderer) {
        for(scene in mScenes){
            scene.render(renderer)
        }
    }
}