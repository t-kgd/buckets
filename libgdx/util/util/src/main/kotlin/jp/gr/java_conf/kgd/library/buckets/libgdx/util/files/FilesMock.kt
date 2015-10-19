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

package jp.gr.java_conf.kgd.library.buckets.libgdx.util.files

import com.badlogic.gdx.Files
import com.badlogic.gdx.files.FileHandle
import java.io.File

class FilesMock : Files {

    private val classLoader: ClassLoader by lazy { Thread.currentThread().contextClassLoader }

    fun getMockFileHandle(path: String?): FileHandle? {
        val resource = classLoader.getResource(path)
        val file = resource?.file
        return FileHandle(file ?: File(path).path)
    }

    override fun getLocalStoragePath(): String? {
        return classLoader.getResource("").path
    }

    override fun local(path: String?): FileHandle? {
        return getMockFileHandle(path)
    }

    override fun external(path: String?): FileHandle? {
        return getMockFileHandle(path)
    }

    override fun getFileHandle(path: String?, type: Files.FileType?): FileHandle? {
        return getMockFileHandle(path)
    }

    override fun absolute(path: String?): FileHandle? {
        return getMockFileHandle(path)
    }

    override fun isExternalStorageAvailable(): Boolean {
        return true
    }

    override fun classpath(path: String?): FileHandle? {
        return getMockFileHandle(path)
    }

    override fun internal(path: String?): FileHandle? {
        return getMockFileHandle(path)
    }

    override fun getExternalStoragePath(): String? {
        return classLoader.getResource("").path
    }

    override fun isLocalStorageAvailable(): Boolean {
        return true
    }
}