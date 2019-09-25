package io.qwerty.neo.engine.rendering

import io.qwerty.neo.Settings
import io.qwerty.neo.utils.IOUtil.ioResourceToByteBuffer
import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL11.*
import org.lwjgl.opengl.GL13.GL_TEXTURE0
import org.lwjgl.opengl.GL13.glActiveTexture
import org.lwjgl.stb.STBImage.STBI_rgb_alpha
import org.lwjgl.stb.STBImage.stbi_load_from_memory
import org.lwjgl.stb.STBImage.stbi_image_free
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.IntBuffer

/**
 * The Texture class is used for rendering images.
 * They should not be manually created, instead use the Resources class.
 */
class Texture {
    private var width = BufferUtils.createIntBuffer(1)
    private var height = BufferUtils.createIntBuffer(1)
    private val channel: IntBuffer = BufferUtils.createIntBuffer(1)
    private var id: Int = 0

    constructor(path: String) {
        val data: ByteBuffer?

        try {
            data = stbi_load_from_memory(
                ioResourceToByteBuffer(path, 1024),
                width,
                height,
                channel,
                STBI_rgb_alpha
            ) ?: error("File $path not found.")
        } catch (e: IOException) {
            return e.printStackTrace()
        }

        id = glGenTextures()
        val w = width.get()
        val h = height.get()

        glBindTexture(GL_TEXTURE_2D, id)

        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, if(Settings.USE_LINEAR) GL_LINEAR.toFloat() else GL_NEAREST.toFloat())
        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, if(Settings.USE_LINEAR) GL_LINEAR.toFloat() else GL_NEAREST.toFloat())
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, w, h, 0, GL_RGBA, GL_UNSIGNED_BYTE, data)

        println("$w x $h")
        stbi_image_free(data)
    }

    constructor() {
        id = 0
    }

    fun bind() {
        glActiveTexture(GL_TEXTURE0)
        glBindTexture(GL_TEXTURE_2D, id)
    }

    fun unbind() {
        glBindTexture(GL_TEXTURE_2D, 0)
    }

    fun dispose() {
        glDeleteTextures(id)
    }

    override fun equals(other: Any?): Boolean {
        if(other !is Texture)
            return false
        return other.id == id
    }

    override fun hashCode(): Int = id

}