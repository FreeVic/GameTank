package model

import interfaces.Destroyedable
import interfaces.View
import manager.Config
import org.itheima.kotlin.game.core.Painter

class Blast(override var x: Int, override var y: Int) :View,Destroyedable{
    override var isDestroy: Boolean = false

    override fun isDestroyed(): Boolean = index>=paths.size

    override var width: Int = Config.BLOCK
    override var height: Int = Config.BLOCK
    val paths = arrayListOf<String>()
    var index = 0
    init {
        (1..32).forEach {
            paths.add("img/blast_$it.png")
        }
    }
    override fun draw() {
        var i = index%paths.size
        Painter.drawImage(paths[i],x,y)
        index++
    }

}