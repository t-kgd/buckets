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

/*
 * トレイトや拡張メソッドでどうとでもできるのでクラスごとのLoggerのインスタンスを作らないタイプにする。
 */

interface Logger {

    fun getLogLevel(tag: String): LogLevel

    fun isLogLevelEnable(tag: String, logLevel: LogLevel): Boolean {
        return getLogLevel(tag) >= logLevel
    }

    fun outputLog(tag: String, logLevel: LogLevel, message: String, exception: Exception?)

    fun logThrough<T>(tag: String, logLevel: LogLevel, obj: T, lazyMessage: (T) -> String, exception: Exception? = null): T {
        if (isLogLevelEnable(tag, logLevel)) {
            outputLog(tag, logLevel, lazyMessage.invoke(obj), exception)
        }
        return obj
    }

    fun log(tag: String, logLevel: LogLevel, lazyMessage: () -> String, exception: Exception? = null) {
        if (isLogLevelEnable(tag, logLevel)) {
            outputLog(tag, logLevel, lazyMessage.invoke(), exception)
        }
    }

    fun errorThrough<T>(tag: String, obj: T, lazyMessage: (T) -> String, exception: Exception? = null): T {
        return logThrough(tag, LogLevel.ERROR, obj, lazyMessage, exception)
    }

    fun error(tag: String, lazyMessage: () -> String, exception: Exception? = null) {
        log(tag, LogLevel.ERROR, lazyMessage, exception)
    }

    fun warnThrough<T>(tag: String, obj: T, lazyMessage: (T) -> String, exception: Exception? = null): T {
        return logThrough(tag, LogLevel.WARN, obj, lazyMessage, exception)
    }

    fun warn(tag: String, lazyMessage: () -> String, exception: Exception? = null) {
        log(tag, LogLevel.WARN, lazyMessage, exception)
    }

    fun infoThrough<T>(tag: String, obj: T, lazyMessage: (T) -> String, exception: Exception? = null): T {
        return logThrough(tag, LogLevel.INFO, obj, lazyMessage, exception)
    }

    fun info(tag: String, lazyMessage: () -> String, exception: Exception? = null) {
        log(tag, LogLevel.INFO, lazyMessage, exception)
    }

    fun debugThrough<T>(tag: String, obj: T, lazyMessage: (T) -> String, exception: Exception? = null): T {
        return logThrough(tag, LogLevel.DEBUG, obj, lazyMessage, exception)
    }

    fun debug(tag: String, lazyMessage: () -> String, exception: Exception? = null) {
        log(tag, LogLevel.DEBUG, lazyMessage, exception)
    }
}