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

@file:JvmName("LoggingExtension")

package jp.gr.java_conf.kgd.library.buckets.libgdx.util.logger

fun Any.logErrorThrough<T>(obj: T, lazyMessage: (T) -> String, exception: Exception? = null): T {
    return LoggingUtil.errorThrough(this, obj, lazyMessage, exception)
}

fun Any.logErrorThrough<T>(obj: T): T {
    return LoggingUtil.errorThrough(this, obj)
}

fun Any.logError(lazyMessage: () -> String, exception: Exception? = null) {
    LoggingUtil.error(this, lazyMessage, exception)
}

fun Any.logWarnThrough<T>(obj: T, lazyMessage: (T) -> String, exception: Exception? = null): T {
    return LoggingUtil.warnThrough(this, obj, lazyMessage, exception)
}

fun Any.logWarnThrough<T>(obj: T): T {
    return LoggingUtil.warnThrough(this, obj)
}

fun Any.logWarn(lazyMessage: () -> String, exception: Exception? = null) {
    LoggingUtil.warn(this, lazyMessage, exception)
}

fun Any.logInfoThrough<T>(obj: T, lazyMessage: (T) -> String, exception: Exception? = null): T {
    return LoggingUtil.infoThrough(this, obj, lazyMessage, exception)
}

fun Any.logInfoThrough<T>(obj: T): T {
    return LoggingUtil.infoThrough(this, obj)
}

fun Any.logInfo(lazyMessage: () -> String, exception: Exception? = null) {
    LoggingUtil.info(this, lazyMessage, exception)
}

fun Any.logDebugThrough<T>(obj: T, lazyMessage: (T) -> String, exception: Exception? = null): T {
    return LoggingUtil.debugThrough(this, obj, lazyMessage, exception)
}

fun Any.logDebugThrough<T>(obj: T): T {
    return LoggingUtil.debugThrough(this, obj)
}

fun Any.logDebug(lazyMessage: () -> String, exception: Exception? = null) {
    LoggingUtil.debug(this, lazyMessage, exception)
}