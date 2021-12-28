package hu.unimiskolc.iit.jump.application.engine

import hu.unimiskolc.iit.jump.application.PlayerState
import hu.unimiskolc.iit.jump.application.game.MainRenderer

class GameObject(var position: Vector2D, var state: PlayerState = PlayerState.WAIT) {
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
}