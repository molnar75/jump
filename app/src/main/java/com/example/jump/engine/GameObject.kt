package com.example.jump.engine

import android.content.Context
import com.example.jump.PlayerState
import com.example.jump.game.MainRenderer
import com.example.jump.graph.ShaderProgram

class GameObject(private val context: Context, var position: Vector2D, var state: PlayerState = PlayerState.WAIT) {
    var mSprites: ArrayList<Sprite> = ArrayList()
    var currSprite: Int = 0

    var visible: Boolean = true

    fun addSprite(sprite: Sprite) {
        mSprites.add(sprite)
    }

    fun getBoundingBox(): BoundingBox2D {
        return mSprites[currSprite].getCurrentFrameTransformedBoundingBox()
    }

    fun render(renderer: MainRenderer) {
        val sprite = mSprites[currSprite]
        sprite.mvSpritePosition = position

        sprite.render(renderer)

        sprite.getCurrentFrameTransformedBoundingBox()
    }

    fun cleanup(){
        for(sprite in mSprites){
            sprite.cleanup()
        }
    }
}