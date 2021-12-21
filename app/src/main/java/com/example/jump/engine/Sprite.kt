package com.example.jump.engine

import android.content.Context
import com.example.jump.game.MainRenderer

class Sprite(private val context: Context, filenames: String, numOfFrames: Int, var mvSpritePosition: Vector2D, private var miFps: Int) {
    private val mvFrames: ArrayList<SpriteFrame> = ArrayList()

    // Actual frame
    private var miActualFrame = 0

    // The last time the animation was update
    private var miLastUpdate: Long = System.currentTimeMillis()

    init {
        loadTextures(filenames, numOfFrames)
    }

    private fun loadTextures(filenames: String, numOfFrames: Int) {
        if (numOfFrames == 1) {
            val tex = Texture2D(context)
            tex.position = Vector2D(mvSpritePosition.x, mvSpritePosition.y)
            tex.createTexture("$filenames.png")


            val newFrame = SpriteFrame(tex)
            newFrame.addBoundingBox(Vector2D(0.0f, 0.0f), Vector2D(tex.mWidth.toFloat(), tex.mHeight.toFloat()))
            mvFrames.add(newFrame)
        } else {
            for (i in 0 until numOfFrames) {
                val tex = Texture2D(context)
                tex.position = Vector2D(mvSpritePosition.x, mvSpritePosition.y)
                tex.createTexture(filenames + (i + 1) + ".png")

                val newFrame = SpriteFrame(tex)
                newFrame.addBoundingBox(Vector2D(0.0f, 0.0f), Vector2D(tex.mWidth.toFloat(), tex.mHeight.toFloat()))
                mvFrames.add(newFrame)

                mvFrames.add(newFrame)
            }
        }
    }

    fun render(renderer: MainRenderer) {
        val tex = mvFrames[miActualFrame].mFrame
        tex.render(renderer)
        update()
    }
    private fun update() {
        if (1000.0f / miFps < (System.currentTimeMillis() - miLastUpdate)) {
            miLastUpdate = System.currentTimeMillis()
            if(++miActualFrame == mvFrames.size){
                miActualFrame = 0
            }
        }
    }

    fun cleanup(){
        for(frame in mvFrames) {
            frame.mFrame.cleanup()
        }
    }

    fun getCurrentFrameTransformedBoundingBox(): BoundingBox2D {
        val currentFrame = mvFrames[miActualFrame]

        val original: BoundingBox2D = currentFrame.mBBoxOriginal
        val transformed: BoundingBox2D = currentFrame.mBBoxTransformed
        transformed.setPoints(original.minpoint, original.maxpoint)

        transformed.transformByRotate(0f)
        transformed.transformByScale(1f)
        transformed.transformByTranslate(mvSpritePosition)

        return transformed
    }

    fun setPosition(pos: Vector2D) {
        mvSpritePosition = pos

        for(frame in mvFrames) {
            frame.mFrame.position = pos
        }
    }

}