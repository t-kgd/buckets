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

@file:JvmName("TreeExtension")

package jp.gr.java_conf.kgd.library.buckets.libgdx.util.actor.tree

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Tree
import java.util.*

/**
 * [Actor]から[Tree.Node]を生成し、追加する。
 *
 * [actor]を[nodeActorFactory]によって変換し、[Tree.Node]に加えます。
 * [Actor]が[com.badlogic.gdx.scenes.scene2d.Group]であれば再帰的に走査し、加えてきます。
 * 戻り値の実体は[IdentityHashMap]であり、「変換後の[Actor]」から「元の[Actor]」を検索できます。
 */
fun Tree.addActorRecursive(actor: Actor, nodeActorFactory: (Actor) -> Actor): Map<Actor, Actor> {
    val map = IdentityHashMap<Actor, Actor>()
    val treeNode = TreeUtil.createTreeNode(actor, {
        val label = nodeActorFactory(it)
        map.put(label, it)
        label
    })
    this.add(treeNode)
    return map
}

fun Tree.addActorRecursive(actor: Actor, skin: Skin): Map<Actor, Actor> {
    return addActorRecursive(actor, { Label(it.javaClass.simpleName, skin) })
}
