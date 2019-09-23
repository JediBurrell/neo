package io.qwerty.neo.framework

import io.qwerty.neo.engine.Canvas
import io.qwerty.neo.objects.GameObject
import java.util.*

/**
 * The Handler is a utility for storing and updating objects en masse.
 * The Handler is provided in all scenes, and will automatically tick and render anything in it.
 */
class Handler {

    private val objects = LinkedList<GameObject>()
    private val remove = LinkedList<GameObject>()

    /**
     * Ticks each item.
     */
    fun tick() {
        objects.forEach{
            it.tick()
        }
    }

    /**
     * Loops through all items, rendering each.
     *
     * @param canvas - The drawing API.
     */
    fun render(canvas: Canvas) {
        objects.forEach{
            it.render(canvas)
        }

        // It is now safe to remove these objects as items in the handler are no longer being looped through.
        objects.removeAll(remove)
    }

    /**
     * Adds an object to the handler.
     *
     * @param obj - Any extension on GameObject.
     */
    fun addObject(obj: GameObject) = objects.add(obj)
    /**
     * Queues an object for safe removal.
     * This will essentially delete the object from the scene.
     *
     * @param obj - The existing object you wish to remove.
     */
    fun removeObject(obj: GameObject) = remove.add(obj)

}