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
import jp.gr.java_conf.kgd.library.buckets.util.groovy.getOrElse

/**
 * アプリ起動時の初期設定。
 */
interface StartUpConfig : Config {

    fun getResourcesPath(): String

    fun getScriptsPath(): String

    fun getSavePath(): String

    fun getDefaultSkinPath(): String

    /**
     * [StartUpConfig]のデフォルトの振る舞い。
     *
     * デフォルトでは、設定を[groovy.util.ConfigSlurper]によって読み込みます。
     * 下記の階層で値を設定してください。（値は例です。）
     * <pre>
     *     path {
     *         resources = "resources/"
     *         scripts = "scripts/"
     *         save = "save/"
     *         defaultSkin = "${resources}ui/uiskin.json"
     *     }
     * </pre>
     */
    class SimpleStartUpConfig(configFilePath: String = "config/startUpConfig.groovy")
    : Config by AutoInitializeConfig(SimpleConfig(configFilePath)), StartUpConfig {

        private val path: ConfigObject by lazy { getOrElse("path", { ConfigObject() }) }

        private val resourcesPath_: String by lazy { path.getOrElse("resources", { "resources/" }) }

        private val scriptsPath_: String  by lazy { path.getOrElse("scripts", { "scripts/" }) }

        private val savePath_: String  by lazy { path.getOrElse("save", { "save/" }) }

        private val defaultSkinPath_: String  by lazy { path.getOrElse("defaultSkin", { "defaultSkin/" }) }

        override fun getResourcesPath(): String {
            return resourcesPath_
        }

        override fun getScriptsPath(): String {
            return scriptsPath_
        }

        override fun getSavePath(): String {
            return savePath_
        }

        override fun getDefaultSkinPath(): String {
            return defaultSkinPath_
        }
    }

    companion object : StartUpConfig by SimpleStartUpConfig()
}