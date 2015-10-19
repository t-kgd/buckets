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

import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx

class GdxLogger : Logger {

    private val app: Application by lazy { Gdx.app.apply { logLevel = Application.LOG_DEBUG } }

    override fun getLogLevel(tag: String): LogLevel {
        return convertGdxLogLevel(app.logLevel)
    }

    override fun outputLog(tag: String, logLevel: LogLevel, message: String, exception: Exception?) {
        if (exception != null) {
            when (logLevel) {
                LogLevel.ERROR -> app.error(tag, message, exception)
                LogLevel.WARN -> app.log(tag, message, exception)
                LogLevel.INFO -> app.log(tag, message, exception)
                LogLevel.DEBUG -> app.debug(tag, message, exception)
                else -> Unit
            }
        } else {
            when (logLevel) {
                LogLevel.ERROR -> app.error(tag, message)
                LogLevel.WARN -> app.log(tag, message)
                LogLevel.INFO -> app.log(tag, message)
                LogLevel.DEBUG -> app.debug(tag, message)
                else -> Unit
            }
        }
    }

    companion object {

        fun convertGdxLogLevel(gdxLogLevel: Int): LogLevel {
            return when (gdxLogLevel) {
                com.badlogic.gdx.utils.Logger.NONE -> LogLevel.NONE
                com.badlogic.gdx.utils.Logger.ERROR -> LogLevel.ERROR
                com.badlogic.gdx.utils.Logger.INFO -> LogLevel.INFO
                com.badlogic.gdx.utils.Logger.DEBUG -> LogLevel.DEBUG
                else -> LogLevel.NONE
            }
        }
    }
}