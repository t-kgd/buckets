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

package jp.gr.java_conf.kgd.library.buckets.libgdx.util.script

import groovy.lang.Binding
import jp.gr.java_conf.kgd.library.buckets.util.groovy.getOrPut
import java.util.*

/**
 * Groovyスクリプトを使用する際のユーティリティ。
 */
interface ScriptTrait {

    fun getPackageName(): String = this.javaClass.`package`.name

    fun getPackagePath(): String = getPackageName().replace(".", "/") + "/"

    fun getLocalBinding(): Binding {
        val localBindingMap = getLocalBindingMap()

        // localBindingは自クラスに持たず、自分の参照をキーにしてWeakHashMapに登録する
        val localBinding = localBindingMap.getOrPut(this, {
            val binding = Binding()
            binding.setVariable("self", this@ScriptTrait)
            return@getOrPut binding
        })

        return localBinding
    }

    fun runScript(path: String): Any? {
        // 判別方法がまずいかもしれない。
        val pathEx = if (path.split(".").size() < 2) {
            path + ".groovy"
        } else {
            path
        }
        val scriptEngine = ScriptEngineProvider.getScriptEngine()
        val result = scriptEngine.run(pathEx, getLocalBinding())
        return result
    }

    fun runPackageScript(path: String): Any? = runScript(getPackagePath() + path)

    companion object {

        fun getLocalBindingMap(): MutableMap<Any, Binding> {
            val globalBinding = ScriptEngineProvider.getGlobalBinding()
            // シングルトンに登録することになるのでメモリリークしないようにWeakHashMapに入れる
            val map = globalBinding.getOrPut("localBindings", { WeakHashMap<Any, Binding>() })
            return map
        }
    }
}