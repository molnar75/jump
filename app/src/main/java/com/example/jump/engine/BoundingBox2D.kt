package com.example.jump.engine

import android.opengl.GLES20.*
import android.opengl.Matrix.*
import com.example.jump.graph.ShaderProgram

class BoundingBox2D(newMinPoint: Vector2D, newMaxPoint: Vector2D) {
    private val aabbPoints2D = 4
    var minpoint: Vector2D
    var maxpoint: Vector2D

    private var bbPoints: Array<Vector2D>
    private var boxHalfWidth: Float
    private var boxHalfHeight: Float

    private val transformationMatrix: FloatArray
    private val rotationMatrix: FloatArray

    var mEnabled: Boolean

    private val coordsPerVertex = 3
    private val vertexStride = coordsPerVertex * 4 // 4 bytes per vertex

    init {
        boxHalfWidth = 0f
        boxHalfHeight = 0f
        mEnabled = false
        bbPoints = Array(aabbPoints2D) {Vector2D(0f, 0f)}
        transformationMatrix = FloatArray(16)
        rotationMatrix = FloatArray(16)
        minpoint = Vector2D(newMinPoint.x, newMinPoint.y)
        maxpoint = Vector2D(newMaxPoint.x, newMaxPoint.y)
        setUpBBPoints()
        searchMinMax()
        boxHalfWidth = (maxpoint.x - minpoint.x) / 2.0f
        boxHalfHeight = (maxpoint.y - minpoint.y) / 2.0f
    }

    fun setPoints(min: Vector2D, max: Vector2D){
        minpoint.set(min.x, min.y)
        maxpoint.set(max.x, max.y)

        setUpBBPoints()
        searchMinMax()

        boxHalfWidth = (maxpoint.x - minpoint.x) / 2.0f
        boxHalfHeight = (maxpoint.y - minpoint.y) / 2.0f
    }

    private fun setUpBBPoints() {
        bbPoints[0].set(minpoint.x, minpoint.y)

        bbPoints[1].set(maxpoint.x, minpoint.y)

        bbPoints[2].set(maxpoint.x, maxpoint.y)

        bbPoints[3].set(minpoint.x, maxpoint.y)
    }

    private fun searchMinMax() {
        val min = Vector2D(bbPoints[0].x, bbPoints[0].y)
        val max = Vector2D(bbPoints[0].x, bbPoints[0].y)

        for (i in 0 until aabbPoints2D){
            if (bbPoints[i].x < min.x) {
                min.x = bbPoints[i].x
            }
            if (bbPoints[i].y < min.y) {
                min.y = bbPoints[i].y
            }

            if (bbPoints[i].x > max.x) {
                max.x = bbPoints[i].x
            }
            if (bbPoints[i].y > max.y) {
                max.y = bbPoints[i].y
            }
        }

        minpoint.set(min.x, min.y)

        maxpoint.set(max.x, max.y)
    }

    fun transformByScale(scale: Float) {
        setIdentityM(transformationMatrix, 0)
        scaleM(transformationMatrix, 0, scale, scale, scale)

        for (i in 0 until aabbPoints2D){
            transformPoint(bbPoints[i])
        }
        searchMinMax()
        setUpBBPoints()
    }

    private fun transformPoint(vec: Vector2D){
        val x = vec.x
        val y = vec.y

        vec.x = x * transformationMatrix[0] + y * transformationMatrix[4] + transformationMatrix[12]
        vec.y = x * transformationMatrix[1] + y * transformationMatrix[5] + transformationMatrix[13]
    }

    fun transformByTranslate(translateVector: Vector2D) {
        setIdentityM(transformationMatrix, 0)
        translateM(transformationMatrix, 0, translateVector.x, translateVector.y, 0f)

        for (i in 0 until aabbPoints2D){
            transformPoint(bbPoints[i])
        }
        searchMinMax()
        setUpBBPoints()

    }

    fun transformByRotate(rotationAngle: Float) {
        setIdentityM(transformationMatrix, 0)
        val x = boxHalfWidth
        val y = boxHalfHeight
        translateM(transformationMatrix, 0, x, y, 0f)
        setRotateM(rotationMatrix, 0, rotationAngle, 0f, 0f, -1.0f)
        multiplyMM(transformationMatrix, 0, transformationMatrix, 0, rotationMatrix, 0)
        translateM(transformationMatrix, 0, -x, -y, 0f)

        for (i in 0 until aabbPoints2D){
            transformPoint(bbPoints[i])
        }
        searchMinMax()
        setUpBBPoints()
    }

    fun render(shaderProgram: ShaderProgram) {

        val util = Utils()

        if (!mEnabled){
            return
        }

        val vertices = floatArrayOf(
            minpoint.x, minpoint.y, 0.0f,
            minpoint.x, maxpoint.y, 0.0f,
            maxpoint.x, maxpoint.y, 0.0f,
            maxpoint.x, minpoint.y, 0.0f,
            minpoint.x, minpoint.y, 0.0f
        )

        shaderProgram.bind()

        val posAttrib = glGetAttribLocation(shaderProgram.getId(), "vPosition")
        glEnableVertexAttribArray(posAttrib)
        val positionBuffer = util.createFloatBuffer(vertices)
        glVertexAttribPointer(
            posAttrib, coordsPerVertex,
            GL_FLOAT, false,
            vertexStride, positionBuffer
        )

        glEnableVertexAttribArray(posAttrib)
        glLineWidth(4.0f)
        glDrawArrays(GL_LINE_STRIP, 0, 5)
        glDisableVertexAttribArray(posAttrib)

        shaderProgram.unbind()
    }
}