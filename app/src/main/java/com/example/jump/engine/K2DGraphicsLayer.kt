package com.example.jump.engine

import com.example.jump.game.MainRenderer

class K2DGraphicsLayer(name: String, id: Int, var cameraSpeed: Float) {
    var mObjectList = mutableListOf<GameObject>()
    private var mTextures: ArrayList<Texture2D> = ArrayList()

    private var mVisible: Boolean = true

    var mName: String = name
    var mID: Int = id

    var mCamera: Camera2D? = null

    init {
        mCamera = null
    }

    fun addGameObject(gameObject: GameObject){
        mObjectList.add(gameObject)
    }

    fun addTexture(texture: Texture2D){
        mTextures.add(texture)
    }

    fun getTextureByID(targetId: Int): Texture2D? {
        for (i in mTextures.indices) {
            if (mTextures[i].getId() === targetId) {
                return mTextures[i]
            }
        }
        return null
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
           // mCamera!!.setViewMatrix(viewMatrix) //TODO
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