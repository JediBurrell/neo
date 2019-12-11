package io.qwerty.neo.backend.desktop.engine

import io.qwerty.neo.Game
import io.qwerty.neo.backend.desktop.DesktopNeo
import io.qwerty.neo.backend.desktop.rendering.DesktopShader
import io.qwerty.neo.engine.Canvas
import io.qwerty.neo.engine.rendering.Model
import io.qwerty.neo.engine.rendering.Shader
import io.qwerty.neo.engine.rendering.Texture
import io.qwerty.neo.framework.Resources
import io.qwerty.neo.rendering.Camera
import org.lwjgl.opengl.GL11.*
import java.lang.RuntimeException


class DesktopCanvas(val game: Game?) : Canvas {

    var camera: Camera? = null

    override fun drawImage(texture: Texture, x: Float, y: Float, z: Float, width: Float, height: Float) {
        val model = Model(
            vertices = floatArrayOf(
                x, y, z,
                x+width, y, z,
                x+width, y+height, z,
                x, y+height, z
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

    override fun drawImage(texture: String, x: Float, y: Float, z: Float, width: Float, height: Float) {
        glColor4f(0f, 0f, 0f, 0f)
        val model = Model(
            vertices = floatArrayOf(
                x, y, z,
                x+width, y, z,
                x+width, y+height, z,
                x, y+height, z
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

    override fun drawRect(x: Float, y: Float, z: Float, width: Float, height: Float) {
        val model = Model(
            vertices = floatArrayOf(
                x, y, z,
                x+width, y, z,
                x+width, y+height, z,
                x, y+height, z
            ),
            indices = intArrayOf(
                0, 1, 2,
                2, 3, 0
            )
        )

        model.render()
    }

    /**
     * Render with custom shaders.
     *
     * @param shader - The file name of your shaders (without extension).
     * @param passthrough - Parameters to pass to the shader.
     * @param rendering - The callback which allows you use your shaders.
     */
    override fun withShader(shader: String, passthrough: ((Shader)->Unit), rendering: (()->Unit)) {
        if(game == null)
            throw(RuntimeException("This instance of DesktopCanvas cannot use withShader."))

        var shade = DesktopShader(shader)
        shade.bind()
        shade.setUniform("sampler", 0)
        shade.setUniform("projection", game.scene.camera.projection())
        passthrough.invoke(shade)

        rendering.invoke()

        // Reset the shader.
        shade = DesktopShader()
        shade.bind()
        shade.setUniform("sampler", 0)
        shade.setUniform("projection", game.scene.camera.projection())
    }

    override fun drawOval(x: Double, y: Double, z: Float, width: Double, height: Double) {

    }

    override fun setColor(r: Float, g: Float, b: Float, a: Float) {
        glColor4f(r, g, b, a)
    }

    override fun focusCamera(camera: Camera) {
        this.camera = camera
    }

}