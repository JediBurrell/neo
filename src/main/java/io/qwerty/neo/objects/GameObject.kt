package io.qwerty.neo.objects

import io.qwerty.neo.engine.Canvas

abstract class GameObject {

    var x = 0f
    var y = 0f
    var z = 0f
    var width = 0f
    var height = 0f

    abstract fun tick()

    abstract fun render(canvas: Canvas)

    fun shouldRender(): Boolean = true

}