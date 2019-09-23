package io.qwerty.neo.engine.rendering

import org.joml.Matrix4f

interface Shader {

    fun bind()

    fun setUniform(key: String, value: Int)

    fun setUniform(key: String, translation: Matrix4f)

}