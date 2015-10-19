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

package jp.gr.java_conf.kgd.library.buckets.libgdx.util.logger

import java.util.*

class SimpleLoggerFilterConfig : MutableLoggerFilterConfig {

    private val logLevelMap by lazy { HashMap<String, LogLevel>() }

    override fun getLogLevel(tag: String): LogLevel {
        // まず、タグがそのままあるか
        logLevelMap.get(tag)?.let { return it }
        // なければパッケージをさかのぼる
        return getPackageDefaultLogLevel(tag)
    }

    override fun setLogLevel(tag: String, logLevel: LogLevel) {
        logLevelMap.put(tag, logLevel)
    }

    override fun setPackageLogLevel(tag: String, logLevel: LogLevel) {
        setLogLevel(tag + "." + packageTag, logLevel)
    }

    private fun getPackageDefaultLogLevel(tag: String): LogLevel {
        val nextTag = tag.substringBeforeLast(".")
        if (nextTag == tag) {
            // トップドメインまで行きついたら
            // グローバルのデフォルト値があるか
            logLevelMap.get(packageTag)?.let { return it }
            // なければ出力なし
            return LogLevel.NONE
        }
        // パッケージのデフォルト値があるかチェック
        logLevelMap.get(nextTag + "." + packageTag)?.let { return it }
        // なければ再帰
        return getPackageDefaultLogLevel(nextTag)
    }


    companion object {

        // パッケージ名とクラス名に被らないような識別子
        val packageTag: String = "ALL"
    }
}