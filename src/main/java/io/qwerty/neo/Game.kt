package io.qwerty.neo

import io.qwerty.neo.framework.Scene

/**
 * This is your main class, and must be passed through to Neo to get your game working.
 * You should never need more than one of these in a project.
 */
abstract class Game {

    /**
     * This is the running scene.
     * To render anything on the screen, you'll need to set this to a Scene object.
     */
    lateinit var scene: Scene
    var fps: Int = 0

    /**
     * This function should handle non-graphical functionality, such as routine movements.
     * The tick is called 24 times a second by default, though the rate can be changed with Settings.
     */
    abstract fun tick()

    /**
     * This is called right after everything is set up and ready to go.
     * This is where you'll want to set the CLASS_LOADER, and your Scene.
     */
    abstract fun start()

}