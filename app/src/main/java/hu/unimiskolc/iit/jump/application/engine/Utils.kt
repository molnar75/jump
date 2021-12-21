package hu.unimiskolc.iit.jump.application.engine

import android.content.Context
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

class Utils {
    fun createFloatBuffer(inputArray: FloatArray): FloatBuffer {
        return ByteBuffer.allocateDirect(inputArray.size * 4).run {
            order(ByteOrder.nativeOrder())
            asFloatBuffer().apply {
                put(inputArray).flip()
                position(0)
            }
        }
    }

    fun loadFile(context: Context, fileName: String): String = context.assets.open(fileName).bufferedReader().use { it.readText()}
}