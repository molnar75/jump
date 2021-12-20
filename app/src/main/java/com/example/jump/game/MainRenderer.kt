package com.example.jump.game

import android.content.Context
import android.opengl.GLES30
import android.opengl.GLSurfaceView
import android.opengl.Matrix
import com.example.jump.engine.Texture2D
import com.example.jump.engine.Utils
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10
import com.example.jump.graph.ShaderProgram

class MainRenderer(private val context: Context) : GLSurfaceView.Renderer {

     val projectionMatrix = FloatArray(16)
     val viewMatrix = FloatArray(16)

    lateinit var shaderProgram: ShaderProgram
    private lateinit var lineShader: ShaderProgram
    lateinit var dummyGame: DummyGame

    private val utils = Utils()

    // The system calls this method on each redraw of the GLSurfaceView.
    // Use this method as the primary execution point for drawing (and re-drawing) graphic objects.
    override fun onDrawFrame(unused: GL10) {
        // Redraw background color
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT)

        // Set the camera position (View matrix)
        Matrix.setLookAtM(viewMatrix, 0, 0f, 0f, 200f, 0f, 0f, 0f, 0f, 1.0f, 0.0f)

        dummyGame.render(this)
    }

    // The system calls this method once, when creating the GLSurfaceView.
    // Use this method to perform actions that need to happen only once, such as setting OpenGL environment parameters or initializing OpenGL graphic objects.
    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        // Set the background frame color
        GLES30.glClearColor(0.0f, 0.0f, 0.0f, 1.0f)

        shaderProgram = ShaderProgram()
        shaderProgram.createFragmentShader(utils.loadFile(context, "shaders/fragment.fs"))
        shaderProgram.createVertexShader(utils.loadFile(context, "shaders/vertex.vs"))
        shaderProgram.link()
        shaderProgram.createUniform("projectionMatrix")
        shaderProgram.createUniform("worldMatrix")
        shaderProgram.createUniform("viewMatrix")
        shaderProgram.createUniform("texture_sampler")
        shaderProgram.createUniform("vColor")

        lineShader = ShaderProgram()
        lineShader.createVertexShader(utils.loadFile(context, "shaders/line.vs"))
        lineShader.createFragmentShader(utils.loadFile(context, "shaders/line.fs"))
        lineShader.link()
        lineShader.createUniform("projectionMatrix")
        lineShader.createUniform("modelMatrix")
        lineShader.createUniform("vColor")

        dummyGame = DummyGame(context)
    }

    // The system calls this method when the GLSurfaceView geometry changes, including changes in size of the GLSurfaceView or orientation of the device screen.
    // For example, the system calls this method when the device changes from portrait to landscape orientation.
    // Use this method to respond to changes in the GLSurfaceView container.
    override fun onSurfaceChanged(unused: GL10, width: Int, height: Int) {
        GLES30.glViewport(0, 0, width, height)

        val ratio: Float = width.toFloat() / height.toFloat()

        // this projection matrix is applied to object coordinates
        // in the onDrawFrame() method
        Matrix.frustumM(projectionMatrix, 0, -ratio, ratio, -1f, 1f, 1f, 10000f)
    }
}