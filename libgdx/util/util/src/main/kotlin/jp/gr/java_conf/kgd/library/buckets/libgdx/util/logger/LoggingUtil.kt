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

object LoggingUtil {

    @JvmStatic
    fun logThrough<T>(tagObj: Any, logLevel: LogLevel, obj: T, lazyMessage: (T) -> String, exception: Exception? = null): T {
        return LoggerProvider.getLogger().logThrough(tagObj.javaClass.name, logLevel, obj, lazyMessage, exception)
    }

    @JvmStatic
    fun logThrough<T>(tagObj: Any, logLevel: LogLevel, obj: T): T {
        return logThrough(tagObj, logLevel, obj, { "" + obj }, null)
    }

    @JvmStatic
    fun log(tagObj: Any, logLevel: LogLevel, lazyMessage: () -> String, exception: Exception? = null) {
        return LoggerProvider.getLogger().log(tagObj.javaClass.name, logLevel, lazyMessage, exception)
    }

    @JvmStatic
    fun errorThrough<T>(tagObj: Any, obj: T, lazyMessage: (T) -> String, exception: Exception? = null): T {
        return logThrough(tagObj, LogLevel.ERROR, obj, lazyMessage, exception)
    }

    @JvmStatic
    fun errorThrough<T>(tagObj: Any, obj: T): T {
        return errorThrough(tagObj, obj, { "" + obj }, null)
    }

    @JvmStatic
    fun error(tagObj: Any, lazyMessage: () -> String, exception: Exception? = null) {
        log(tagObj, LogLevel.ERROR, lazyMessage, exception)
    }

    @JvmStatic
    fun warnThrough<T>(tagObj: Any, obj: T, lazyMessage: (T) -> String, exception: Exception? = null): T {
        return logThrough(tagObj, LogLevel.WARN, obj, lazyMessage, exception)
    }

    @JvmStatic
    fun warnThrough<T>(tagObj: Any, obj: T): T {
        return warnThrough(tagObj, obj, { "" + obj }, null)
    }

    @JvmStatic
    fun warn(tagObj: Any, lazyMessage: () -> String, exception: Exception? = null) {
        log(tagObj, LogLevel.WARN, lazyMessage, exception)
    }

    @JvmStatic
    fun infoThrough<T>(tagObj: Any, obj: T, lazyMessage: (T) -> String, exception: Exception? = null): T {
        return logThrough(tagObj, LogLevel.INFO, obj, lazyMessage, exception)
    }

    @JvmStatic
    fun infoThrough<T>(tagObj: Any, obj: T): T {
        return infoThrough(tagObj, obj, { "" + obj }, null)
    }

    @JvmStatic
    fun info(tagObj: Any, lazyMessage: () -> String, exception: Exception? = null) {
        log(tagObj, LogLevel.INFO, lazyMessage, exception)
    }

    @JvmStatic
    fun debugThrough<T>(tagObj: Any, obj: T, lazyMessage: (T) -> String, exception: Exception? = null): T {
        return logThrough(tagObj, LogLevel.DEBUG, obj, lazyMessage, exception)
    }

    @JvmStatic
    fun debugThrough<T>(tagObj: Any, obj: T): T {
        return debugThrough(tagObj, obj, { "" + obj }, null)
    }

    @JvmStatic
    fun debug(tagObj: Any, lazyMessage: () -> String, exception: Exception? = null) {
        log(tagObj, LogLevel.DEBUG, lazyMessage, exception)
    }
}
