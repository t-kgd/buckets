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

package jp.gr.java_conf.kgd.library.buckets.libgdx.util.actor

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Group
import java.util.*

interface ActorNode {

    fun getCurrentActor(): Actor

    fun getParentActorNode(): ActorNode?

    fun getChildActorNodes(): List<ActorNode>

    private class ActorNodeImpl(private val currentActor: Actor, private val parentActorNode: ActorNode? = null) : ActorNode {

        private val childActorNodes_: MutableList<ActorNode> by lazy { ArrayList<ActorNode>() }

        override fun getCurrentActor(): Actor {
            return currentActor
        }

        override fun getParentActorNode(): ActorNode {
            return parentActorNode!!
        }

        override fun getChildActorNodes(): List<ActorNode> {
            return childActorNodes_
        }

        fun getMutableChildActorNodes(): MutableList<ActorNode> {
            return childActorNodes_
        }
    }

    companion object {

        fun createActorNode(actor: Actor): ActorNode {
            return createActorNodeImpl(actor, null)
        }

        private fun createActorNodeImpl(actor: Actor, parentActorNode: ActorNode?): ActorNode {
            val actorNode = ActorNodeImpl(actor, parentActorNode)
            if (actor is Group) {
                actor.children.forEach {
                    actorNode.getMutableChildActorNodes().add(createActorNodeImpl(it, actorNode))
                }
            }
            return actorNode
        }
    }
}