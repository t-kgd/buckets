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

package jp.gr.java_conf.kgd.library.buckets.util.groovy

/*
 * interfaceにしてcompanion objectに継承させるか迷う。
 * Javaからの見た目を重視してstaticメソッドにする。
 */

class GroovyUtil {

    companion object {

        // GStringImpl対策。BigDecimalは勝手に変換してくれるみたい。
        @JvmStatic
        fun castOrTransform<T>(obj: Any, clazz: Class<T>): T {
            return when (clazz) {
                String::class.java -> obj.toString() as T
                else -> obj as T
            }
        }

        @JvmStatic
        inline fun castOrTransform<reified T : Any>(obj: Any): T {
            return castOrTransform(obj, T::class.java)
        }
    }
}