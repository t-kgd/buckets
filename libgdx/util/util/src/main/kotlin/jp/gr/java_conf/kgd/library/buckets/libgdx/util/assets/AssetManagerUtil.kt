/*
 * The MIT License
 *
 * Copyright (c) 2015 Misakura.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package jp.gr.java_conf.kgd.library.buckets.libgdx.util.assets

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import jp.gr.java_conf.kgd.library.buckets.libgdx.util.config.BaseConfigProvider

object AssetManagerUtil {

    @JvmStatic
    inline fun load<reified T : Any>(assetManager: AssetManager, path: String) {
        assetManager.load(path, T::class.java)
    }

    @JvmStatic
    inline fun getOrNull<reified T : Any>(assetManager: AssetManager, path: String): T? {
        return assetManager.get(path, T::class.java)
    }

    @JvmStatic
    fun loadSync<T>(assetManager: AssetManager, path: String, clazz: Class<T>): T {
        if (!assetManager.isLoaded(path)) {
            assetManager.load(path, clazz)
            assetManager.finishLoadingAsset(path)
        }
        return assetManager.get(path, clazz)
    }

    @JvmStatic
    inline fun loadSync<reified T : Any>(assetManager: AssetManager, path: String): T {
        return loadSync(assetManager, path, T::class.java)
    }


    @JvmStatic
    fun getDefaultSkin(assetManager: AssetManager): Skin {
        return loadSync(assetManager, BaseConfigProvider.getBaseConfig().getDefaultSkinFilePath())
    }
}