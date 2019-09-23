package io.qwerty.neo.backend.desktop

import io.qwerty.neo.Game
import io.qwerty.neo.Neo
import io.qwerty.neo.Settings
import io.qwerty.neo.backend.desktop.engine.DesktopCanvas
import io.qwerty.neo.backend.desktop.rendering.DesktopShader
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11.*
import org.lwjgl.stb.STBImage.*
import org.lwjgl.system.MemoryUtil.NULL


/**
 * The DesktopNeo wraps the Game into the JVM.
 * This is required for Windows, MacOS, Linux; and optional for Chrome OS.
 *
 * @param game - Your main class.
 * @param config - Configuration for the window, giving ou the ability to set the size, title, or make it fullscreen.
 */
class DesktopNeo(val game: Game, private val config: DesktopNeoConfig = DesktopNeoConfig()) : Neo {

    private var run: Boolean = false
    private var window: Long = 0L

    /**
     * This begins the game. It'll set up the window and immediately begin the Game instance provided.
     */
    override fun run() {
        init()
        game.start()
        loop()

        dispose()
    }

    private fun init() {
        // Make sure the function has not been called previously.
        if (run) throw IllegalAccessException("Neo has already been initialized. Remove duplicate calls to DesktopNeo#init")
        run = true

        // Route GLFW errors.
        GLFWErrorCallback.createPrint(System.err).set()

        // Initialize GLFW.
        if(!glfwInit())
            error("GLFW failed to initialize.\nThis means either your device does not meet requirements, or or drivers need updated.")

        createWindow()

        GL.createCapabilities()
        stbi_set_flip_vertically_on_load(true)
    }

    private fun loop() {
        glEnable(GL_TEXTURE_2D)
        glEnable(GL_BLEND)
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA)
        glClearColor(1f, 1f, 1f, 1f)

        val shader = DesktopShader()

        val rate: Double = 1.0 / Settings.TICK_RATE
        var pTime: Double = glfwGetTime()
        var gap = 0.0
        while(!glfwWindowShouldClose(window)) {
            glfwPollEvents()

            glClear(GL_COLOR_BUFFER_BIT)

            // TICK LOOP //

            val time = glfwGetTime()
            val elapsed = time - pTime
            pTime = time
            gap += elapsed

            while(gap > rate) {
                game.tick()
                game.scene.tick()
                gap -= rate
            }

            // TICK LOOP //

            // RENDERING //

            shader.bind()
            shader.setUniform("sampler", 0)
            shader.setUniform("projection", game.scene.camera.projection())

            game.scene.render(DesktopCanvas())

            // RENDERING //

            glfwSwapBuffers(window)
        }
    }

    private fun dispose() {
        glfwTerminate()
    }

    // ---

    private fun createWindow() {
        // Apply window options.
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
        glfwWindowHint(GLFW_RESIZABLE, if(config.resizable) GLFW_TRUE else GLFW_FALSE)

        // Create the window.
        window = glfwCreateWindow(config.width, config.height, config.title,
            if(config.fullscreen) glfwGetPrimaryMonitor() else NULL,
            NULL)
        if(window == NULL)
            error("Failed to create window.")

        // Center the window.
        val vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor())
        glfwSetWindowPos(
            window, (vidmode!!.width() - config.width) / 2, (vidmode.height() - config.height) / 2
        )

        // Apply vsync.
        glfwMakeContextCurrent(window)
        if(config.vsync)
            glfwSwapInterval(1)

        // Show the window.
        glfwShowWindow(window)
    }

}

data class DesktopNeoConfig(val title: String = "NEO Game",
                            val width: Int = 1280,
                            val height: Int = 720,
                            val fullscreen: Boolean = false,
                            val resizable: Boolean = true,
                            val vsync: Boolean = true)