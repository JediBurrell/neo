package io.qwerty.neo.engine

import io.qwerty.neo.engine.rendering.Texture
import io.qwerty.neo.rendering.Camera

/*
 * Wrap the native graphics API for future comparability.
 */
interface Canvas {

    fun drawImage(texture: Texture, x: Float, y: Float, width: Float, height: Float) =
        drawImage(texture, x, y, 0f, width, height)
    fun drawImage(texture: String, x: Float, y: Float, width: Float, height: Float) =
        drawImage(texture, x, y, 0f, width, height)
    fun drawImage(texture: Texture, x: Float, y: Float, z: Float, width: Float, height: Float)
    fun drawImage(texture: String, x: Float, y: Float, z: Float, width: Float, height: Float)

    fun drawRect(x: Float, y: Float, width: Float, height: Float) =
        drawRect(x, y, 0f, width, height)
    fun drawRect(x: Float, y: Float, z: Float, width: Float, height: Float)

    fun drawOval(x: Double, y: Double, width: Double, height: Double) = drawOval(x, y, 0f, width, height)
    fun drawOval(x: Double, y: Double, z: Float, width: Double, height: Double)

    fun setColor(r: Float, g: Float, b: Float, a: Float = 1f)

    fun focusCamera(camera: Camera)

}