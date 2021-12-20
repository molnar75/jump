package com.example.jump.engine

import com.example.jump.game.MainRenderer
import com.example.jump.graph.ShaderProgram

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

    fun getSceneById(Id: Int): K2DScene?{
        if (Id < mScenes.size) {
            return mScenes[Id]
        }
        return null
    }
}