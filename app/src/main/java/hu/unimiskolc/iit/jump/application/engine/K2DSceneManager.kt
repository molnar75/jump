package hu.unimiskolc.iit.jump.application.engine

import hu.unimiskolc.iit.jump.application.game.MainRenderer

class K2DSceneManager {

    private val mScenes: ArrayList<K2DScene> = ArrayList()

    fun registerScene(scene: K2DScene){
        mScenes.add(scene)
    }

    fun render(renderer: MainRenderer) {
        for (scene in mScenes) {
            scene.render(renderer)
        }
    }

    fun cleanup() {
        for ( i in mScenes) {
            mScenes.remove(i)
        }
    }
}