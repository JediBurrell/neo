package io.qwerty.neo.engine.rendering

import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL20.*
import java.nio.FloatBuffer
import java.nio.IntBuffer

class Model(vertices: FloatArray, indices: IntArray, texCoords: FloatArray? = null) {

    private var count: Int = 0
    private var vID:   Int = 0
    private var tID:   Int = 0
    private var iID:   Int = 0

    init {
        count = indices.size
        vID = glGenBuffers()
        glBindBuffer(GL_ARRAY_BUFFER, vID)
        glBufferData(GL_ARRAY_BUFFER, genBuffer(vertices), GL_STATIC_DRAW)

        iID = glGenBuffers()
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, iID)
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, genBuffer(indices), GL_STATIC_DRAW)

        if(texCoords != null) {
            tID = glGenBuffers()
            glBindBuffer(GL_ARRAY_BUFFER, tID)
            glBufferData(GL_ARRAY_BUFFER, genBuffer(texCoords), GL_STATIC_DRAW)
        }

        glBindBuffer(GL_ARRAY_BUFFER, 0)
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0)
    }

    private fun genBuffer(data: FloatArray): FloatBuffer =
        BufferUtils.createFloatBuffer(data.size).apply {
            put(data)
            flip()
        }

    private fun genBuffer(data: IntArray): IntBuffer =
        BufferUtils.createIntBuffer(data.size).apply {
            put(data)
            flip()
        }

    fun render() {
        glEnableVertexAttribArray(0)
        glEnableVertexAttribArray(1)

        // Set up vertices.
        glBindBuffer(GL_ARRAY_BUFFER, vID)
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0)

        // Apply textures.
        if(tID != 0) {
            glBindBuffer(GL_ARRAY_BUFFER, tID)
            glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0)
        }

        // Draw.
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, iID)
        GL11.glDrawElements(GL11.GL_TRIANGLES, count, GL_UNSIGNED_INT, 0)

        //Clean up.
        glBindBuffer(GL_ARRAY_BUFFER, 0)
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0)
        glDisableVertexAttribArray(0)
        glDisableVertexAttribArray(1)
    }

}