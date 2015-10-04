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
import groovy.util.GroovyScriptEngine
import jp.gr.java_conf.kgd.library.buckets.libgdx.util.config.StartUpConfig
import jp.gr.java_conf.kgd.library.buckets.libgdx.util.file.FilesProvider

interface ScriptEngineProvider {

    private object Internal {
        val scriptEngine: GroovyScriptEngine by lazy {
            GroovyScriptEngine(FilesProvider.getFiles().internal(StartUpConfig.getScriptsPath()).path())
        }
        val globalBinding: Binding by lazy { Binding() }
    }

    fun getScriptEngine(): GroovyScriptEngine = Internal.scriptEngine

    fun getGlobalBinding(): Binding = Internal.globalBinding

    companion object : ScriptEngineProvider
}