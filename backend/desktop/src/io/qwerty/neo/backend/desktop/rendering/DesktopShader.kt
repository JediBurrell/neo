package io.qwerty.neo.backend.desktop.rendering

import io.qwerty.neo.Neo
import io.qwerty.neo.Settings
import io.qwerty.neo.engine.rendering.Shader
import org.joml.Matrix4f
import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL20.*
import kotlin.system.exitProcess

class DesktopShader: Shader {

    var vs: Int      = 0
    var fs: Int      = 0
    var program: Int = 0

    constructor(vertex: String, fragment: String = vertex): super() {
        val vertexFile = Settings.CLASS_LOADER.getResource("$vertex.vs")?.readText()
            ?: error("Failed to load shader ${vertex}.vs\nPerhaps you've not set the CLASS_LOADER?")

        val fragmentFile = Settings.CLASS_LOADER.getResource("$fragment.fs")?.readText()
            ?: error("Failed to load shader ${fragment}.fs\nPerhaps you've not set the CLASS_LOADER?")

        setup(vertexFile, fragmentFile)
    }

    constructor() {
        val vertexFile = Neo::class.java.classLoader.getResource("default.vs")?.readText()
            ?: error("Something went wrong with loading the default vertex shader.")

        val fragmentFile = Neo::class.java.classLoader.getResource("default.fs")?.readText()
            ?: error("Something went wrong with loading the default fragment shader.")

        setup(vertexFile, fragmentFile)
    }

    fun setup(vertexFile: String, fragmentFile: String) {
        program = glCreateProgram()

        // Create vertex shader.
        vs = glCreateShader(GL_VERTEX_SHADER)
        glShaderSource(vs, vertexFile)
        glCompileShader(vs)

        // Catch vertex compilation errors.
        if(glGetShaderi(vs, GL_COMPILE_STATUS) != 1) {
            error(glGetShaderInfoLog(vs))
            exitProcess(1)
        }

        // Create fragment shader.
        fs = glCreateShader(GL_FRAGMENT_SHADER)
        glShaderSource(fs, fragmentFile)
        glCompileShader(fs)

        // Catch fragment compilation errors.
        if(glGetShaderi(fs, GL_COMPILE_STATUS) != 1) {
            error(glGetShaderInfoLog(fs))
            exitProcess(1)
        }

        glAttachShader(program, vs)
        glAttachShader(program, fs)

        // Pass vertices to the vertex shader.
        glBindAttribLocation(program, 0, "vertices")
        glBindAttribLocation(program, 1, "textures")

        // Link and validate shader program.
        glLinkProgram(program)
        if(glGetProgrami(program, GL_LINK_STATUS) != 1) {
            error(glGetProgramInfoLog(program))
            exitProcess(1)
        }

        glValidateProgram(program)
        if(glGetProgrami(program, GL_VALIDATE_STATUS) != 1) {
            error(glGetProgramInfoLog(program))
            exitProcess(1)
        }
    }

    override fun setUniform(key: String, value: Int) {
        val location = glGetUniformLocation(program, key)
        if(location != -1)
            glUniform1i(location, value)
    }

    override fun setUniform(key: String, translation: Matrix4f) {
        val location = glGetUniformLocation(program, key)
        val buffer = BufferUtils.createFloatBuffer(16)
        translation.get(buffer)
        if(location != -1)
            glUniformMatrix4fv(location, false, buffer)
    }

    override fun bind() {
        glUseProgram(program)
    }

}