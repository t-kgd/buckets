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

@file:JvmName("BindingExtension")

package jp.gr.java_conf.kgd.library.buckets.util.groovy

import groovy.lang.Binding

/*
 * Mapと透過的に扱えないせいでDRYじゃないのが悔しい。
 * ダックタイピングはできないが悪あがきをする。
 */

fun Binding.get(key: String): Any {
    return getVariable(key)
}

fun Binding.put(key: String, value: Any) {
    setVariable(key, value)
}

fun Binding.containsKey(key: String): Boolean {
    return hasVariable(key)
}

fun Binding.getAs<T>(key: String): T {
    return get(key) as T
}

fun Binding.getOrElse<T>(key: String, defaultValue: () -> T): T {
    return if (containsKey(key)) {
        getAs(key)
    } else {
        defaultValue.invoke()
    }
}

fun Binding.getOrElse<T>(key: String, defaultValue: T): T {
    return getOrElse(key, { defaultValue })
}

fun Binding.getOrPut<T : Any>(name: String, defaultValueProvider: () -> T): T {
    return if (containsKey(name)) {
        getAs(name)
    } else {
        val defaultValue = defaultValueProvider.invoke()
        put(name, defaultValue)
        defaultValue
    }
}

fun Binding.getOrPut<T : Any>(key: String, defaultValue: T): T {
    return getOrElse(key, { defaultValue })
}