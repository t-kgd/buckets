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

package jp.gr.java_conf.kgd.library.buckets.libgdx.util

import groovy.util.ConfigObject
import groovy.util.ConfigSlurper
import jp.gr.java_conf.kgd.library.buckets.util.groovy.getAs
import jp.gr.java_conf.kgd.library.buckets.util.groovy.getAsString
import java.io.InputStream

/**
 * アプリ起動時の初期設定。
 */
public interface StartUpConfig {

    fun getResourcesPath(): String

    fun getScriptsPath(): String

    fun getSavePath(): String

    fun getDefaultSkinPath(): String

    fun isInitialized(): Boolean

    fun initialize()

    fun reload()

    /**
     * [StartUpConfig]のデフォルトの振る舞い。
     *
     * デフォルトでは、設定を[ConfigSlurper]によって読み込みます。
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
    interface StartUpConfigTrait : StartUpConfig {

        protected var configPath: String

        protected var resourceLoader: (String) -> InputStream

        protected var initialized: Boolean

        protected fun setResourcesPath(resourcesPath: String)

        protected fun setScriptsPath(scriptsPath: String)

        protected fun setSavePath(savePath: String)

        protected fun setDefaultSkinPath(defaultSkinPath: String)

        override fun isInitialized(): Boolean = initialized

        override fun initialize(): Unit {
            if (!isInitialized()) load()
        }

        override fun reload() {
            load()
        }

        private fun load() {
            val script = resourceLoader.invoke(configPath).bufferedReader().readText()
            val config = ConfigSlurper().parse(script)

            val pathMap = config.getAs<ConfigObject>("path")
            setResourcesPath(pathMap.getAsString("resources"))
            setScriptsPath(pathMap.getAsString("scripts"))
            setSavePath(pathMap.getAsString("save"))
            setDefaultSkinPath(pathMap.getAsString("defaultSkin"))

            initialized = true
        }

        companion object {
            val defaultResourcesPath: String = "resources/"

            val defaultScriptsPath: String = "scripts/"

            val defaultSavePath: String = "save/"

            val defaultDefaultSkinPath: String = defaultResourcesPath + "ui/uiskin.json"
        }
    }

    /**
     * [StartUpConfig]のデフォルト実装。
     *
     * このクラスはコンストラクタで初期化（[reload]）を行いません。
     */
    class DefaultStartUpConfig(
            override public var configPath: String = DefaultStartUpConfig.defaultConfigPath,
            override public var resourceLoader: (String) -> InputStream = DefaultStartUpConfig.defaultResourceLoader
    ) : StartUpConfigTrait {

        private companion object {
            val defaultConfigPath: String = "config/startUpConfig.groovy"
            val defaultResourceLoader: (String) -> InputStream = { FilesProvider.getFiles().internal(it).read() }
        }

        override var initialized: Boolean = false

        private var resourcesPath: String = StartUpConfigTrait.defaultResourcesPath

        private var scriptsPath: String = StartUpConfigTrait.defaultScriptsPath

        private var savePath: String = StartUpConfigTrait.defaultSavePath

        private var defaultSkinPath: String = StartUpConfigTrait.defaultDefaultSkinPath

        override fun getResourcesPath(): String = resourcesPath

        override fun getScriptsPath(): String = scriptsPath

        override fun getSavePath(): String = savePath

        override fun getDefaultSkinPath(): String = defaultSkinPath

        override fun setResourcesPath(resourcesPath: String) {
            this.resourcesPath = resourcesPath
        }

        override fun setScriptsPath(scriptsPath: String) {
            this.scriptsPath = scriptsPath
        }

        override fun setSavePath(savePath: String) {
            this.savePath = savePath
        }

        override fun setDefaultSkinPath(defaultSkinPath: String) {
            this.defaultSkinPath = defaultSkinPath
        }
    }

    class AutoInitializeStartUpConfigWrapper(startUpConfig: StartUpConfig) : StartUpConfig {

        val startUpConfig by lazy {
            startUpConfig.initialize()
            startUpConfig
        }

        override fun getResourcesPath(): String = startUpConfig.getResourcesPath()

        override fun getScriptsPath(): String = startUpConfig.getScriptsPath()

        override fun getSavePath(): String = startUpConfig.getSavePath()

        override fun getDefaultSkinPath(): String = startUpConfig.getDefaultSkinPath()

        override fun initialize() = startUpConfig.initialize()

        override fun isInitialized(): Boolean = startUpConfig.isInitialized()

        override fun reload() = startUpConfig.reload()
    }

    /**
     * [StartUpConfig]のデフォルト実装のシングルトンを[AutoInitializeStartUpConfigWrapper]で包んだもの。
     *
     * メソッドに初回アクセスした際に初期化を行います。それまでにlibGDXが初期化されている必要があるので注意してください。
     */
    companion object : StartUpConfig by AutoInitializeStartUpConfigWrapper(DefaultStartUpConfig())
}