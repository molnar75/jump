package com.example.jump.engine

import android.opengl.Matrix.setLookAtM
import android.opengl.Matrix.translateM

class Camera2D(x: Float, y: Float, id: Int) {
    var mPosition: Vector2D = Vector2D(x, y)

    fun moveLeft(value: Float){
        mPosition.x += value
    }

    fun moveRight(value: Float){
        mPosition.x -= value
    }

    fun moveUp(value: Float){
        mPosition.y += value
    }

    fun moveDown(value: Float){
        mPosition.x -= value
    }

    fun moveRelative(valueX: Float, valueY: Float){
        mPosition.x += valueX
        mPosition.y += valueY
    }

    fun setPosition(x: Float, y: Float){
        mPosition.set(x, y)
    }
}