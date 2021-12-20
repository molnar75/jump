package com.example.jump.graph

import android.opengl.GLES20.*
import android.opengl.GLES30
import java.lang.Exception
import com.example.jump.engine.Utils
import java.nio.FloatBuffer

class ShaderProgram {

    private val programId: Int = GLES30.glCreateProgram().also {
        GLES30.glLinkProgram(it)
    }
    private var vertexShaderId: Int = 0
    private var fragmentShaderId: Int = 0

    private var uniforms = HashMap<String, Int>()

    init {
        if (programId == 0) {
            throw Exception("Could not create shader program!")
        }
    }

    fun createVertexShader(shaderCode: String) {
        vertexShaderId = loadShader(GL_VERTEX_SHADER, shaderCode)
    }

    fun createFragmentShader(shaderCode: String) {
        fragmentShaderId = loadShader(GL_FRAGMENT_SHADER, shaderCode)
    }

    private fun loadShader(shaderType: Int, shaderCode: String): Int {
        return GLES30.glCreateShader(shaderType).also { shaderId ->
            if (shaderId == 0) {
                throw Exception("Could not create shader!")
            }
            // add the source code to the shader and compile it
            GLES30.glShaderSource(shaderId, shaderCode)
            GLES30.glCompileShader(shaderId)

            val compileStatus = IntArray(1)
            glGetShaderiv(shaderId, GL_COMPILE_STATUS, compileStatus, 0)
            if(compileStatus[0] == 0){
                throw Exception("Error compiling shader code: ${glGetProgramInfoLog(shaderId)}")
            }

            GLES30.glAttachShader(programId, shaderId)
        }
    }

    fun getId(): Int {
        return programId
    }

    fun createUniform(uniformName: String) {
        val uniformLocation = glGetUniformLocation(programId, uniformName)
        if (uniformLocation < 0) {
            throw Exception("Could not find uniform:$uniformName")
        }
        uniforms[uniformName] = uniformLocation
    }

    fun setUniform(uniformName: String, value: Int) {
        glUniform1i(uniforms[uniformName]!!, value)
    }

    fun setUniform(uniformName: String, value: FloatArray) {
        glUniformMatrix4fv(uniforms[uniformName]!!,1, false, value, 0)
    }

    fun setUniform4fv(uniformName: String, value: FloatArray){
        glUniform4fv(uniforms[uniformName]!!, 1, value, 0)
    }

    fun bind() {
        glUseProgram(programId)
    }

    fun unbind() {
        glUseProgram(0)
    }

    fun link() {
        glLinkProgram(programId)

        val linkStatus = IntArray(1)
        glGetProgramiv(programId, GL_LINK_STATUS, linkStatus, 0)
        if(linkStatus[0] == 0){
            throw Exception("Error in linking shader code: ${glGetProgramInfoLog(programId)}")
        }

        if (vertexShaderId != 0) {
            glDetachShader(programId, vertexShaderId)
        }

        if (fragmentShaderId != 0) {
            glDetachShader(programId, fragmentShaderId)
        }

        glValidateProgram(programId)

       val validateStatus = IntArray(1)
        glGetProgramiv(programId, GL_VALIDATE_STATUS, validateStatus, 0)
        if(validateStatus[0] == 0){
            throw Exception("Error in validating shader code: ${glGetProgramInfoLog(programId)}")
        }
    }
}