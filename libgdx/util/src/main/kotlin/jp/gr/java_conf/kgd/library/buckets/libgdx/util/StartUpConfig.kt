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
import java.io.InputStream
import kotlin.properties.Delegates

/**
 * アプリ起動時の初期設定。
 */
public interface StartUpConfig {

    var resourcesPath: String
        protected set

    var scriptsPath: String
        protected set

    var savePath: String
        protected set

    var defaultSkinPath: String
        protected set

    fun reload()

    /**
     * [StartUpConfig]のデフォルトの振る舞い。
     *
     * デフォルトでは、設定を[ConfigSlurper]によって読み込みます。
     * 下記の階層で値を設定してください。
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

        var configPath: String

        var resourceLoader: (String) -> InputStream

        override fun reload() {
            load()
        }

        private fun load() {
            val script = resourceLoader.invoke(configPath).bufferedReader().readText()
            val config = ConfigSlurper().parse(script)
            initialize(config)
        }

        private fun initialize(config: ConfigObject) {
            val pathMap = config.get("path") as ConfigObject
            this.resourcesPath = pathMap.getProperty("resources").toString()
            this.scriptsPath = pathMap.getProperty("scripts").toString()
            this.savePath = pathMap.getProperty("save").toString()
            this.defaultSkinPath = pathMap.getProperty("defaultSkin").toString()
        }
    }

    /**
     * [StartUpConfig]のデフォルト実装。
     *
     * このクラスはコンストラクタで初期化（[reload]）を行いません。
     */
    open class DefaultStartUpConfig : StartUpConfigTrait {

        override var configPath = "config/startUpConfig.groovy"

        override var resourceLoader: (String) -> InputStream = { FilesProvider.getFiles().internal(it).read() }

        override var resourcesPath: String by Delegates.notNull()

        override var scriptsPath: String by Delegates.notNull()

        override var savePath: String by Delegates.notNull()

        override var defaultSkinPath: String by Delegates.notNull()
    }

    /**
     * [StartUpConfig]のデフォルト実装のシングルトン。
     *
     * 初回アクセス時に初期化を行いますが、先にlibGDXが初期化されている必要があるので注意してください。
     * このシングルトンにアクセスする場合はできるだけアクセスを遅延した方が良いでしょう。
     */
    companion object : StartUpConfig by DefaultStartUpConfig() {
        init {
            reload()
        }
    }
}