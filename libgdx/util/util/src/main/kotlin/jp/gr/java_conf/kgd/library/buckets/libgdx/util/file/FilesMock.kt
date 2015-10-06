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

package jp.gr.java_conf.kgd.library.buckets.libgdx.util.file

import com.badlogic.gdx.Files
import com.badlogic.gdx.files.FileHandle

open class FilesMock : Files {

    override fun getLocalStoragePath(): String? {
        throw UnsupportedOperationException()
    }

    override fun local(path: String?): FileHandle? {
        throw UnsupportedOperationException()
    }

    override fun external(path: String?): FileHandle? {
        throw UnsupportedOperationException()
    }

    override fun getFileHandle(path: String?, type: Files.FileType?): FileHandle? {
        throw UnsupportedOperationException()
    }

    override fun absolute(path: String?): FileHandle? {
        throw UnsupportedOperationException()
    }

    override fun isExternalStorageAvailable(): Boolean {
        throw UnsupportedOperationException()
    }

    override fun classpath(path: String?): FileHandle? {
        throw UnsupportedOperationException()
    }

    override fun internal(path: String?): FileHandle? {
        throw UnsupportedOperationException()
    }

    override fun getExternalStoragePath(): String? {
        throw UnsupportedOperationException()
    }

    override fun isLocalStorageAvailable(): Boolean {
        throw UnsupportedOperationException()
    }
}