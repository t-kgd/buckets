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

package jp.gr.java_conf.kgd.library.buckets.libgdx.util.config

import groovy.util.ConfigObject
import groovy.util.ConfigSlurper
import jp.gr.java_conf.kgd.library.buckets.libgdx.util.file.FilesProvider
import jp.gr.java_conf.kgd.library.buckets.libgdx.util.file.SingletonFilesProvider

class SimpleConfig(val configFilePath: String, val filesProvider: FilesProvider = SingletonFilesProvider) : ConfigTrait {

    private var initialized = false

    private var configObject: ConfigObject? = null
        protected set

    override fun load() {
        this.configObject = loadConfigObject(configFilePath)
        this.initialized = true
    }

    override fun isInitialized(): Boolean {
        return this.initialized
    }

    override fun getConfigObject(): ConfigObject {
        return configObject!!
    }

    protected fun loadConfigObject(path: String): ConfigObject {
        val fileHandle = filesProvider.getFiles().internal(path);
        return if (fileHandle.exists()) {
            ConfigSlurper().parse(fileHandle.readString())
        } else {
            return ConfigObject()
        }
    }
}