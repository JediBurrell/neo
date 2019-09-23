package io.qwerty.neo.rendering

import io.qwerty.neo.Settings
import io.qwerty.neo.objects.GameObject
import org.joml.Matrix3f
import org.joml.Matrix4f
import org.joml.Vector3d
import org.joml.Vector3f

class Camera {
    val projection: Matrix4f = Matrix4f()
        .ortho2D(-Settings.SCREEN_WIDTH/2f,
            Settings.SCREEN_WIDTH/2f,
            -Settings.SCREEN_HEIGHT/2f,
            Settings.SCREEN_HEIGHT/2f)

    fun projection(): Matrix4f {
        val target = Matrix4f()
        val position = Vector3f(-posX.toFloat(), posY.toFloat(), posZ.toFloat())
        val applicator = Matrix4f()
            .scale(scaleX.toFloat(), scaleY.toFloat(), 1f)
            .translate(position)
        projection.mul(applicator, target)

        return target
    }

    var posX: Double = 0.0
    var posY: Double = 0.0
    /**
     * We use the third dimension to determine scrolling speed, parallax effects, and lighting.
     * Z will always default to 0, though you may want to adjust it.
     */
    var posZ: Double = 0.0
    var scaleX: Double = 1.0
    var scaleY: Double = 1.0

    /**
     * The speedGap will create a parralax between different layers on the Z axis.
     * This is defaulted to zero, which means anything layered directly behind a layer will remain hidden regardless of camera position.
     */
    val speedGap: Double = 0.0
    
    /**
     * The scale is a unified scale of scaleX, scaleY. Setting this will set both values.
     * Unless both scaleX and scaleY are the same value, scale will always be 1.0.
     */
    var scale: Double = 1.0
        get(){
            if(scaleX == scaleY)
                return scaleX
            return 1.0
        }
        set(value) {
            scaleX = value
            scaleY = value
            field = value
        }
    
    /**
     * The object which the camera follows.
     * If null, the camera will be follow manual positioning.
     */
    private var anchor: GameObject? = null
    private var anchorOptions = AnchorOptions()


    /**
     * Anchors the camera to a non-static object.
     * When an anchor is set, the camera can no longer be positioned manually.
     * 
     * You can optionally apply AnchorOptions to guide the camera around the object.
     */
    fun anchorTo(obj: GameObject, anchorOptions: AnchorOptions = this.anchorOptions) {
        anchor = obj
        this@Camera.anchorOptions = anchorOptions
    }

    /**
     * Releases the anchor, and frees up the positions for manual control.
     */
    fun releaseAnchor(){
        anchor = null
    }

    /**
     * AnchorOptions tells the camera how it should react to the objects movements.
     * This can allow the object to freely move a little before the camera begins to follow.
     */
    class AnchorOptions {
        /**
         * The furthest the anchored object can go right before the camera begins to move.
         * This is a negative position relatively.
         * 
         * For example, if the screen is 1920x1080, and furthestX is set to 100,
         * the camera will begin moving when the object is 100 pixels away from
         * the end of the screen.
         * 
         * If you wanted the camera to start moving once the object is 1/3 away from
         * the end of the screen, furthestX should be screenWidth/3.
         * 
         * If furthestX and closestX are the same value, the object will never
         * move absolutely on the X axis. This is good if you want the sorroundings
         * to move around the object.
         */
        var furthestX:Int = 0

        /**
         * The furthest left the anchored object can go before the camera follows.
         * 
         * If you wanted the object to stop moving absolutely left at 1/3 of the screen,
         * closestX should be screenWidth/3.
         * 
         * If furthestX and closestX are the same value, the object will never
         * move absolutely on the X axis. This is good if you want the sorroundings
         * to move around the object.
         */
        var closestX: Int = 0

        /**
         * The highest the anchored object can go before the camera begins to go up.
         * 
         * If highestY and deepestY are the same value, the object will never
         * move absolutely on the Y axis. This is good if you want the sorroundings
         * to move around the object.
         */
        var highestY: Int = 0

        /**
         * The lowest the object can go before the camera follows.
         * This is a negative relative position.
         * 
         * For example, if the screen is 1920x1080, and the deepestY is set to 100,
         * the camera will begin moving when the object is 100 pixels away from
         * the bottom of the screen.
         * 
         * If highestY and deepestY are the same value, the object will never
         * move absolutely on the Y axis. This is good if you want the sorroundings
         * to move around the object.
         */
        var deepestY: Int = 0

        // TODO: Supply documentation.
        var smoothing: Double = 3.0
    }
}