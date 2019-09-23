package io.qwerty.neo.framework

import io.qwerty.neo.engine.rendering.Texture

/**
 * The Resources class organizes textures, and will also hold other resource types in a future release.
 */
class Resources {

    companion object {
        private val textureMap = HashMap<String, Texture>()

        /**
         * A blank texture.
         * Useful for comparing, or as a placeholder.
         */
        val noTexture = Texture()

        /**
         * Creates a texture, with a given reference name.
         *
         * This should not be used outside of the library, instead create a map.json file in your resource folder
         * mapping your textures to reference names; and pass your Context or a ClassLoader to your settings file.
         */
        fun addImage(name: String, path: String) {
            val tex = Texture(path)
            if(tex.equals(Resources.noTexture)) {
                error("Failed to create resource $name")
            } else {
                textureMap.putIfAbsent(name, tex)
                println("Resource added: $name")
            }
        }

        /**
         * Disposes of your texture, and removes it from the list.
         * There's probably not a good reason to use this, and is subject for deprecation.
         */
        fun removeImage(name: String) {
            textureMap[name]?.dispose()
            if (textureMap.containsKey(name))
                textureMap.remove(name)
        }

        /**
         * Grabs an existing texture from the texture map.
         * This is used internally, and though can be used, there's no reason to use this over a string.
         */
        fun get(name: String): Texture? {
            if(textureMap.getOrDefault(name, Resources.noTexture) == Resources.noTexture)
                println("There was no resources under name $name")
            return textureMap.getOrDefault(name, Resources.noTexture)
        }
    }

}