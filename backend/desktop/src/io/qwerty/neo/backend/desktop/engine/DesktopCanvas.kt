package io.qwerty.neo.backend.desktop.engine

import io.qwerty.neo.engine.Canvas
import io.qwerty.neo.engine.rendering.Model
import io.qwerty.neo.engine.rendering.Texture
import io.qwerty.neo.framework.Resources
import io.qwerty.neo.rendering.Camera
import org.lwjgl.opengl.GL11.*


class DesktopCanvas : Canvas {

    var camera: Camera? = null

    override fun drawImage(texture: Texture, x: Float, y: Float, width: Float, height: Float) {
        val model = Model(
            vertices = floatArrayOf(
                x, y, 0f,
                x+width, y, 0f,
                x+width, y+height, 0f,
                x, y+height, 0f
            ),
            texCoords = floatArrayOf(
                0f, 0f,
                1f, 0f,
                1f, 1f,
                0f, 1f
            ),
            indices = intArrayOf(
                0, 1, 2,
                2, 3, 0
            )
        )

        texture.bind()
        model.render()
        texture.unbind()
    }

    override fun drawImage(texture: String, x: Float, y: Float, width: Float, height: Float) {
        glColor4f(1f, 1f, 1f, 1f)
        val model = Model(
            vertices = floatArrayOf(
                x, y, 0f,
                x+width, y, 0f,
                x+width, y+height, 0f,
                x, y+height, 0f
            ),
            texCoords = floatArrayOf(
                0f, 0f,
                1f, 0f,
                1f, 1f,
                0f, 1f
            ),
            indices = intArrayOf(
                0, 1, 2,
                2, 3, 0
            )
        )

        Resources.get(texture).also {
            it?.bind()
            model.render()
            it?.unbind()
        }
    }

    override fun drawRect(x: Float, y: Float, width: Float, height: Float) {
        val model: Model = Model(
            vertices = floatArrayOf(
                x, y, 0f,
                x+width, y, 0f,
                x+width, y+height, 0f,
                x, y+height, 0f
            ),
            indices = intArrayOf(
                0, 1, 2,
                2, 3, 0
            )
        )

        model.render()
    }

    override fun drawOval(x: Double, y: Double, width: Double, height: Double) {

    }

    override fun setColor(r: Float, g: Float, b: Float, a: Float) {
        glColor4f(r, g, b, a)
    }

    override fun focusCamera(camera: Camera) {
        this.camera = camera
    }

}