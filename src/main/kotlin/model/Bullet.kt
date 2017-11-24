package model

import interfaces.View
import org.itheima.kotlin.game.core.Painter

class Bullet(override var x: Int, override var y: Int, override var width: Int, override var height: Int,var direction:Direction, create:(dir:Direction,bWidth:Int,bHeight:Int)->Pair<Int,Int>) :View{
    init {
        val size = Painter.size(getPath())
        width = size[0]
        height = size[1]
        val invoke = create(direction,width,height)
        x = invoke.first
        y = invoke.second
    }

    fun getPath() = when(direction){
            Direction.UP-> "img/bullet_u.gif"
            Direction.DOWN->"img/bullet_d.gif"
            Direction.LEFT->"img/bullet_l.gif"
            Direction.RIGHT->"img/bullet_r.gif"
    }

    override fun draw() {
        Painter.drawImage(getPath(),x,y)
    }
}