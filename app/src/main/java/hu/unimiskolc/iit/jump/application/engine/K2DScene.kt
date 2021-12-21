package hu.unimiskolc.iit.jump.application.engine

import hu.unimiskolc.iit.jump.application.game.MainRenderer

class K2DScene {
    private var mLayers: ArrayList<K2DGraphicsLayer> = ArrayList()
    private var mName: String = ""
    private var mVisible: Boolean

    init{
        mName = "Sample Scene"
        mVisible = true
    }

    fun registerLayer(layer: K2DGraphicsLayer){
        mLayers.add(layer)
    }

    fun render(renderer: MainRenderer) {
        if (!mVisible || mLayers.size == 0){
            return
        }

        for(layer in mLayers){
            layer.render(renderer)
        }
    }
}