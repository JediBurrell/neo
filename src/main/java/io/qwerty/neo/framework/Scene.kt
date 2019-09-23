package io.qwerty.neo.framework

import io.qwerty.neo.engine.Canvas
import io.qwerty.neo.rendering.Camera

/**
 * The Scene class allows for quick and easy organization.
 * It's also the only way to begin rendering.
 *
 * You should extend the Scene class for anything that would constitute the word Scene.
 * This includes Settings, the Main Menu, and the game itself.
 */
open class Scene{

    /**
     * This holds all of your items. Anything put in this will be updated automatically by the tick and render function.
     * You should add your initial items either in an init block, or the constructor.
     */
    val handler: Handler = Handler()

    /**
     * This gives you full control over the view of your content including translation and scaling.
     */
    val camera: Camera = Camera()

    /**
     * This function handles non-graphical functionality, and updates all objects in the handler.
     *
     * There is never a reason to call this manually. If the items in your Scene are not rendering,
     * check your Game class to make sure you've set the scene to a new instance of your Scene.
     *
     * If overridden, you should call super, or objects may stop working.
     * The tick is called 24 times a second by default, though the rate can be changed with Settings.
     */
    open fun tick() {
        handler.tick()
    }

    /**
     * This function is called every frame, and passes a Canvas which can be used to draw things to the screen.
     * It renders all items in the handler.
     *
     * There is never a reason to call this manually. If the items in your Scene are not rendering,
     * check your Game class to make sure you've set the scene to a new instance of your Scene.
     *
     * If overridden, you should call super, or objects may stop working.
     * The tick is called 24 times a second by default, though the rate can be changed with Settings.
     *
     * @param canvas - The drawing API.
     */
    open fun render(canvas: Canvas) {
        handler.render(canvas)
    }

}