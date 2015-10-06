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

package jp.gr.java_conf.kgd.library.buckets.libgdx.util.application.groovy

import com.badlogic.gdx.ApplicationListener
import groovy.lang.Binding
import groovy.util.GroovyScriptEngine
import jp.gr.java_conf.kgd.library.buckets.libgdx.util.application.ApplicationListenerProvider
import jp.gr.java_conf.kgd.library.buckets.libgdx.util.file.FilesProvider
import jp.gr.java_conf.kgd.library.buckets.libgdx.util.file.SingletonFilesProvider

interface GroovyApplicationListenerProvider : ApplicationListenerProvider {

    fun getBinding(): Binding = defaultBinding

    fun getScriptRootPath(): String = defaultScriptRootPath

    fun getEntryScriptPath() = defaultEntryScriptPath

    fun getFilesProvider(): FilesProvider = defaultFilesProvider

    override fun getApplicationListener(): ApplicationListener {
        val files = getFilesProvider().getFiles()
        val scriptEngine = GroovyScriptEngine(files.internal(getScriptRootPath()).path())
        val result = scriptEngine.run(getEntryScriptPath(), defaultBinding)
        return result as  ApplicationListener
    }

    companion object : GroovyApplicationListenerProvider {
        val defaultBinding = Binding()
        val defaultScriptRootPath = "internal/scripts/"
        val defaultEntryScriptPath = "createApplicationListener.groovy"
        val defaultFilesProvider by lazy { SingletonFilesProvider }
    }
}