package io.qwerty.neo.objects

import io.qwerty.neo.engine.Canvas

abstract class GameObject {

    var x = 0.0
    var y = 0.0
    var width = 0.0
    var height = 0.0

    abstract fun tick()

    abstract fun render(canvas: Canvas)

}