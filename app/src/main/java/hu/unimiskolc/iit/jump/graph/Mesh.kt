package hu.unimiskolc.iit.jump.graph

import android.opengl.GLES30.*
import hu.unimiskolc.iit.jump.engine.Texture2D
import hu.unimiskolc.iit.jump.engine.Utils
import java.nio.FloatBuffer

class Mesh(positions: FloatArray, textCoords: FloatArray, texture: Texture2D, numOfVertices: Int)  {

    private val vaoId: Int

    private var vboIdList= mutableListOf<Int>()

    private var vertexCount: Int = numOfVertices

    private val textureForRender: Texture2D = texture

    private val utils = Utils()

    init {
        val vboIndexes = IntArray(1)
        val vaoIndexes = IntArray(1)

        glGenVertexArrays(1, vaoIndexes, 0 )

        vaoId = vaoIndexes[0]
        glBindVertexArray(vaoId)

        // Position VBO
        glGenBuffers(1, vboIndexes, 0)
        var vboId: Int = vboIndexes[0]

        vboIdList.add(vboId)

        val posBuffer: FloatBuffer = utils.createFloatBuffer(positions)

        glBindBuffer(GL_ARRAY_BUFFER, vboId)
        glBufferData(GL_ARRAY_BUFFER,  posBuffer.capacity() * 4, posBuffer, GL_STATIC_DRAW)
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0)

        // Texture coordinates VBO
        glGenBuffers(1, vboIndexes, 0 )
        vboId = vboIndexes[0]
        vboIdList.add(vboId)

        val textCoordsBuffer: FloatBuffer = utils.createFloatBuffer(textCoords)

        glBindBuffer(GL_ARRAY_BUFFER, vboId)
        glBufferData(GL_ARRAY_BUFFER, textCoordsBuffer.capacity() * 4, textCoordsBuffer, GL_STATIC_DRAW)
        glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0)

        glBindVertexArray(0)
    }

    fun render(shaderProgram: ShaderProgram) {
        val programId = shaderProgram.getId()
        val positionHandle = glGetAttribLocation(programId, "vPosition")
        val texHandle = glGetAttribLocation(programId, "a_TexCoord")

        // Activate first texture unit
        glActiveTexture(GL_TEXTURE0)
        // Bind the texture
        glBindTexture(GL_TEXTURE_2D, textureForRender.getId())

        // Draw the mesh
        glBindVertexArray(vaoId)
        glEnableVertexAttribArray(0)
        glEnableVertexAttribArray(1)

        glDrawArrays(GL_TRIANGLES, 0, vertexCount)

        // Restore state
        glDisableVertexAttribArray(positionHandle)
        glDisableVertexAttribArray(texHandle)
        glBindVertexArray(0)
    }
}