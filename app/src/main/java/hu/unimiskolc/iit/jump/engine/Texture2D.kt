package hu.unimiskolc.iit.jump.engine

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.opengl.GLES30.*
import android.opengl.GLUtils
import android.opengl.Matrix.*
import hu.unimiskolc.iit.jump.game.MainRenderer
import hu.unimiskolc.iit.jump.graph.Mesh

class Texture2D(
    private val context: Context,
    private var mesh: Mesh? = null,
    private var textureID: Int = -1,
    var position: Vector2D = Vector2D(0f, 0f)
) {

    var mWidth = 0
    var mHeight = 0

    private val transformationMatrix = FloatArray(16)
    private val rotationMatrix = FloatArray(16)
    private val scale = 1f
    private val rotationAngle = 0f
    private val color = floatArrayOf(1.0f, 1.0f, 1.0f, 1.0f)


    fun render(renderer: MainRenderer) {
        val shaderProgram = renderer.shaderProgram

        shaderProgram.bind()

        shaderProgram.setUniform("projectionMatrix", renderer.projectionMatrix)
        shaderProgram.setUniform("texture_sampler", 0)
        shaderProgram.setUniform4fv("vColor", color)
        shaderProgram.setUniform("worldMatrix", getWorldMatrix())
        shaderProgram.setUniform("viewMatrix", renderer.viewMatrix)

        mesh?.render(shaderProgram)

        shaderProgram.unbind()
    }

    fun createTexture(filename: String): Boolean {
        loadTexture(filename)

        val positions = floatArrayOf(
            0.0f, mHeight.toFloat(), 0.0f,
            mWidth.toFloat(), mHeight.toFloat(), 0.0f,
            mWidth.toFloat(), 0.0f, 0.0f,
            mWidth.toFloat(), 0.0f, 0.0f,
            0.0f, 0.0f, 0.0f,
            0.0f, mHeight.toFloat(), 0.0f
        )

        val textCoords =
            floatArrayOf(
                0.0f, 1.0f, 1.0f,
                1.0f, 1.0f, 0.0f,
                1.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 1.0f)
        mesh = Mesh(positions, textCoords, this, 6)
        return true
    }

    private fun loadTexture(fileName: String) {
        val bitmap = loadBitmap(fileName)
        val textures = IntArray(1)

        mWidth = bitmap.width
        mHeight = bitmap.height

        // Create a new OpenGL texture
        glGenTextures(1, textures, 0)
        textureID = textures[0]

        // Bind the texture
        glBindTexture(GL_TEXTURE_2D, textureID)

        // Tell OpenGL how to unpack the RGBA bytes. Each component is 1 byte
        // size
        glPixelStorei(GL_UNPACK_ALIGNMENT, 1)

        // Enable alpha
        glEnable(GL_BLEND)
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA)

        // Set filtering
        glTexParameteri(
            GL_TEXTURE_2D,
            GL_TEXTURE_MIN_FILTER,
            GL_NEAREST
        )
        glTexParameteri(
            GL_TEXTURE_2D,
            GL_TEXTURE_MAG_FILTER,
            GL_NEAREST
        )

        // Upload the texture data
        GLUtils.texImage2D(
            GL_TEXTURE_2D,
            0,
            bitmap,
            0
        )

        glGenerateMipmap(GL_TEXTURE_2D)
        bitmap.recycle()
    }

    private fun loadBitmap(fileName: String): Bitmap{
        val ins = context.assets.open(fileName)
        val bitmap = BitmapFactory.decodeStream(ins)

        val flip = Matrix()
        flip.postScale(1f, -1f)

        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, flip, true)
    }

    fun getId(): Int {
        return textureID
    }

    private fun getWorldMatrix(): FloatArray {

        setIdentityM(transformationMatrix, 0)
        translateM(transformationMatrix, 0, position.x, position.y, 0f)
        scaleM(transformationMatrix, 0, scale, scale, scale)

        val x = 0.5f * mWidth
        val y = 0.5f * mHeight
        translateM(transformationMatrix, 0, x, y, 0f)

        setRotateM(rotationMatrix, 0, rotationAngle, 0f, 0f, -1.0f)
        multiplyMM(transformationMatrix, 0, transformationMatrix, 0, rotationMatrix, 0)
        translateM(transformationMatrix, 0, -x, -y, 0f)

        return transformationMatrix
    }

    fun cleanup() {
        val names = IntArray(1)
        names[0] = textureID
        glDeleteTextures(1, names, 0)
    }
}