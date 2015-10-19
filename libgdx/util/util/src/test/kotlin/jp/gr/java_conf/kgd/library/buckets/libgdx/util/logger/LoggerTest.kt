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

import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

class LoggerTest {

    class TestLogger : Logger {

        val log = LinkedList<String>()

        override fun getLogLevel(tag: String): LogLevel {
            return LogLevel.WARN
        }

        override fun outputLog(tag: String, logLevel: LogLevel, message: String, exception: Exception?) {
            log.add(tag + logLevel + message)
        }
    }

    @Test
    fun outputTest() {
        val sut = TestLogger()
        sut.outputLog("tag", LogLevel.DEBUG, "message", null)

        assertEquals("tag" + LogLevel.DEBUG + "message", sut.log.get(0))
    }

    @Test
    fun logLevelTest_greater() {
        val sut = TestLogger()
        sut.debug("tag", { "message" })

        // ログレベルがWARNなため、DEBUGでは出力されていないはず
        assertEquals(0, sut.log.size())
    }

    @Test
    fun logLevelTest_less() {
        val sut = TestLogger()
        sut.error("tag", { "message" })

        // ログレベルがWARNなため、ERRORは出力される
        assertEquals("tag" + LogLevel.ERROR + "message", sut.log.get(0))
    }

    @Test
    fun logLevelTest_equal() {
        val sut = TestLogger()
        sut.warn("tag", { "message" })

        // ログレベルがWARNなため、WARNは出力される
        assertEquals("tag" + LogLevel.WARN + "message", sut.log.get(0))
    }
}