package hu.unimiskolc.iit.jump.application.engine

class SpriteFrame(frameTexture: Texture2D) {
    var mFrame: Texture2D = frameTexture

    lateinit var mBBoxOriginal: hu.unimiskolc.iit.jump.application.engine.BoundingBox2D
    lateinit var mBBoxTransformed: hu.unimiskolc.iit.jump.application.engine.BoundingBox2D

    fun addBoundingBox(minPoint: Vector2D, maxPoint: Vector2D){
        mBBoxOriginal = hu.unimiskolc.iit.jump.application.engine.BoundingBox2D(minPoint, maxPoint)
        mBBoxTransformed = hu.unimiskolc.iit.jump.application.engine.BoundingBox2D(minPoint, maxPoint)
    }
}