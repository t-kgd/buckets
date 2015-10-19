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

import com.badlogic.gdx.Gdx

/*
 * シングルトンである。順番に説明する。
 * １．FilesProviderWrapperの実装である → 中身のFilesProviderを差し替え可能ということ
 * ２．LazyFilesProvider → 初めてアクセスされるまで委譲インスタンス「生成」を遅延する
 * ３．{ SimpleFilesProvider(Gdx.files) }としている → つまり、Gdx.filesへのアクセスを遅延できる
 * テスト時は、これを評価する前にFilesProviderWrapperのセッターでFilesProviderを差し替えれば良い
 */

object FilesProviderSingleton : FilesProviderWrapper
by SimpleFilesProviderWrapper(LazyFilesProvider({ SimpleFilesProvider(Gdx.files) }))