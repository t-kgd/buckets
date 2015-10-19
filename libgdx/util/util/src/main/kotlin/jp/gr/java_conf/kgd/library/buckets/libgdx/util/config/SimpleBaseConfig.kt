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

class SimpleBaseConfig : MutableBaseConfig {

    private var resourcesDirectoryPath: String = "resources/"

    private var scriptsDirectoryPath: String = "scripts/"

    private var saveDirectoryPath: String = "save/"

    private var loggerFilterConfigFilePath: String = "config/loggerFilterConfig.groovy"

    private var defaultSkinFilePath: String = "${resourcesDirectoryPath}ui/uiskin.json"

    override fun getResourcesDirectoryPath(): String {
        return resourcesDirectoryPath
    }

    override fun getScriptsDirectoryPath(): String {
        return scriptsDirectoryPath
    }

    override fun getSaveDirectoryPath(): String {
        return saveDirectoryPath
    }

    override fun getLoggerFilterConfigFilePath(): String {
        return loggerFilterConfigFilePath
    }

    override fun getDefaultSkinFilePath(): String {
        return defaultSkinFilePath
    }

    override fun setResourcesDirectoryPath(path: String) {
        resourcesDirectoryPath = path
    }

    override fun setScriptsDirectoryPath(path: String) {
        scriptsDirectoryPath = path
    }

    override fun setSaveDirectoryPath(path: String) {
        saveDirectoryPath = path
    }

    override fun setLoggerFilterConfigFilePath(path: String) {
        loggerFilterConfigFilePath = path
    }

    override fun setDefaultSkinFilePath(path: String) {
        defaultSkinFilePath = path
    }
}