package model

import interfaces.View
import org.itheima.kotlin.game.core.Painter

class Bullet(override var x: Int, override var y: Int, override var width: Int = 100, override var height: Int = 100) :View{
    override fun draw() {
        Painter.drawImage("img/bullet_d.gif",x,y)
    }
}