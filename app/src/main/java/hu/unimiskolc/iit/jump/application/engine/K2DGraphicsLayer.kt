package hu.unimiskolc.iit.jump.application.engine

import hu.unimiskolc.iit.jump.application.game.MainRenderer

class K2DGraphicsLayer(id: Int) {
    var mObjectList = mutableListOf<GameObject>()
    private var mTextures: ArrayList<Texture2D> = ArrayList()

    private var mVisible: Boolean = true

    private var mCamera: Camera2D? = null

    init {
        mCamera = null
    }

    fun addGameObject(gameObject: GameObject){
        mObjectList.add(gameObject)
    }

    fun addTexture(texture: Texture2D){
        mTextures.add(texture)
    }

    fun getTexture(index: Int): Texture2D?{
        if (index > -1 && index <  mTextures.size ) {
            return mTextures[index]
        }

        return null
    }

    fun render(renderer: MainRenderer) {
        if (!mVisible) {
            return
        }

        if (mCamera != null){
           // mCamera!!.setViewMatrix(viewMatrix)
        }

        for(texture in mTextures){
            texture.render(renderer)
        }

        for(gameObject in mObjectList){
            if (!gameObject.visible){
                continue
            }
            gameObject.render(renderer)
        }
    }

    fun setCamera(camera: Camera2D){
        mCamera = camera
    }

    fun clear() {
        mObjectList.clear()
        mTextures.clear()
    }

}