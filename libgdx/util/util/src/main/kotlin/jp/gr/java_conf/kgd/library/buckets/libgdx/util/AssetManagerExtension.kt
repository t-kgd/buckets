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

@file:JvmName("AssetManagerExtension")

package jp.gr.java_conf.kgd.library.buckets.libgdx.util

import com.badlogic.gdx.assets.AssetManager

/**
 * 同期読み込み。
 *
 * 非同期読み込みである[AssetManager] [load]に委譲し、読み込みの終了を待ってから値を返します。
 */
fun AssetManager.loadSync<T>(path: String, clazz: Class<T>): T {
    if (isLoaded(path, clazz)) {
        load(path, clazz)
        finishLoadingAsset(path)
    }
    return get(path, clazz)
}

inline fun AssetManager.isLoaded<reified T : Any>(path: String) = isLoaded(path, T::class.java)

inline fun AssetManager.load<reified T : Any>(path: String) = load(path, T::class.java)

inline fun AssetManager.get<reified T : Any>(path: String) = get(path, T::class.java)

inline fun AssetManager.loadSync<reified T : Any>(path: String) = loadSync(path, T::class.java)
