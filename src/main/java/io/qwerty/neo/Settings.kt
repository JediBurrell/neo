package io.qwerty.neo

import io.qwerty.neo.framework.Resources
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

class Settings {

    companion object {

        /**
         * By setting the CLASS_LOADER, your settings and map (settings.json, map.json) will automatically be applied.
         */
        var CLASS_LOADER: ClassLoader = Settings::class.java.classLoader
        set(loader) {
            val json = Json(JsonConfiguration.Stable)
            val resources = loader.getResource("map.json")?.readText()
            if(!resources.isNullOrEmpty()) {
                val map = json.parse(ResourceMap.serializer(), resources)
                map.textures?.forEach { k, v ->
                    Resources.addImage(k, v)
                }
            }

            val settings = loader.getResource("settings.json")?.readText()
            if(!settings.isNullOrEmpty()) {
                val map = json.parse(Settings.serializer(), settings)
                USE_LINEAR = map.USE_LINEAR
                    println("Set USE_LINEAR to $USE_LINEAR")
                FRAMERATE_CAP = map.FRAMERATE_CAP
                    println("Set FRAMERATE_CAP to $FRAMERATE_CAP")
                TICK_RATE = map.TICK_RATE
                    println("Set TICK_RATE to $TICK_RATE")
                SCREEN_WIDTH = map.SCREEN_WIDTH
                    println("Set SCREEN_WIDTH to $SCREEN_WIDTH")
                SCREEN_HEIGHT = map.SCREEN_HEIGHT
                    println("Set SCREEN_HEIGHT to $SCREEN_HEIGHT")
            }

            field = loader
        }

        /**
         * Whether images should be rendered linearly, or using (default) nearest neighbor.
         */
        var USE_LINEAR: Boolean = false
            private set

        /**
         * Not currently applied.
         */
        var FRAMERATE_CAP: Int = 120
            private set

        /**
         * The amount of times `tick` will be called each second.
         */
        var TICK_RATE: Int = 24
            private set

        var SCREEN_WIDTH: Int = 1280
        var SCREEN_HEIGHT: Int = 720

    }

    @Serializable
    private data class ResourceMap(val textures: HashMap<String, String>? = null,
                           val animations: HashMap<String, Animation>? = null)

    @Serializable
    private data class Animation(val frames: Array<String>? = null,
                         val flipped_x: String = "",
                         val flipped_y: String = "") {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Animation

            if (frames != null) {
                if (other.frames == null) return false
                if (!frames.contentEquals(other.frames)) return false
            } else if (other.frames != null) return false

            return true
        }

        override fun hashCode(): Int {
            return frames?.contentHashCode() ?: 0
        }
    }

    @Serializable
    private data class Settings(
        val USE_LINEAR: Boolean = false,
        val FRAMERATE_CAP: Int = 120,
        val TICK_RATE: Int = 24,
        val SCREEN_WIDTH: Int = 1280,
        val SCREEN_HEIGHT: Int = 720
    )

}