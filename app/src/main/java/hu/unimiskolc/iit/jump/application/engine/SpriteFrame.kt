package hu.unimiskolc.iit.jump.application.engine

class SpriteFrame(frameTexture: Texture2D) {
    var mFrame: Texture2D = frameTexture

    lateinit var mBBoxOriginal: BoundingBox2D
    lateinit var mBBoxTransformed: BoundingBox2D

    fun addBoundingBox(minPoint: Vector2D, maxPoint: Vector2D){
        mBBoxOriginal = BoundingBox2D(minPoint, maxPoint)
        mBBoxTransformed = BoundingBox2D(minPoint, maxPoint)
    }
}