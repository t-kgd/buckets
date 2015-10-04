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

@file:JvmName("ConfigObjectExtension")

package jp.gr.java_conf.kgd.library.buckets.util.groovy

import groovy.util.ConfigObject

fun ConfigObject.getAs<T>(key: String, clazz: Class<T>): T {
    return GroovyUtil.castOrTransform(get(key)!!, clazz)
}

inline fun ConfigObject.getAs<reified T : Any>(key: String): T {
    return GroovyUtil.castOrTransform(get(key)!!)
}

// inline関数からクロージャを渡せない
//fun ConfigObject.getOrElse<T>(key: String, defaultValue: () -> T, clazz: Class<T>): T {
//    return if (containsKey(key)) {
//        getAs(key, clazz)
//    } else {
//        defaultValue.invoke()
//    }
//}

fun ConfigObject.getOrElse<T : Any>(key: String, defaultValue: T): T {
    return if (containsKey(key)) {
        getAs(key, defaultValue.javaClass)
    } else {
        defaultValue
    }
}

inline fun ConfigObject.getOrElse<reified T : Any>(key: String, defaultValueFactory: () -> T): T {
    return if (containsKey(key)) {
        getAs(key)
    } else {
        defaultValueFactory.invoke()
    }
}

fun ConfigObject.getOrPut<T : Any>(key: String, defaultValue: T): T {
    return if (containsKey(key)) {
        getAs(key, defaultValue.javaClass)
    } else {
        put(key, defaultValue)
        defaultValue
    }
}

inline fun ConfigObject.getOrPut<reified T : Any>(name: String, defaultValueFactory: () -> T): T {
    return if (containsKey(name)) {
        getAs(name)
    } else {
        val defaultValue = defaultValueFactory.invoke()
        put(name, defaultValue)
        defaultValue
    }
}
