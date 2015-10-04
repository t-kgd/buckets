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

package jp.gr.java_conf.kgd.library.buckets.util.groovy

import groovy.util.ConfigObject
import groovy.util.ConfigSlurper
import org.junit.Test
import kotlin.test.assertEquals

class ConfigObjectExtensionTest {

    @Test
    fun getAsStringTest() {
        // GStringImplがうまく変換されているかどうか
        val sut = ConfigSlurper().parse("foo = \"\${100}fooValue\"")

        assertEquals(true, sut.containsKey("foo"))

        val actual = sut.getAs<String>("foo")
        assertEquals("100fooValue", actual)

        assertEquals(true, sut.containsKey("foo"))
    }

    @Test
    fun getAsBigDecimalTest() {
        // これはBigDecimalになる
        val sut = ConfigSlurper().parse("foo = 100.0")

        assertEquals(true, sut.containsKey("foo"))

        // BigDecimalなのになんで変換できるんだろう？
        // ジェネリクスを通すとOKで、as Doubleすると実行時エラーになるようだ
        val actual = sut.getAs<Double>("foo")

        assertEquals(100.0, actual)

        assertEquals(true, sut.containsKey("foo"))
    }

    @Test
    fun getOrElseTest() {
        val sut = ConfigObject()

        assertEquals(false, sut.containsKey("foo"))

        val actual = sut.getOrElse("foo", "fooValue")
        assertEquals("fooValue", actual)

        assertEquals(false, sut.containsKey("foo"))
    }

    @Test
    fun getOrPutTest() {
        val sut = ConfigObject()

        assertEquals(false, sut.containsKey("foo"))

        val actual = sut.getOrPut("foo", "fooValue")
        assertEquals("fooValue", actual)

        assertEquals(true, sut.containsKey("foo"))
    }
}