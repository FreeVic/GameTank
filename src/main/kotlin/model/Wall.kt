package model

import org.itheima.kotlin.game.core.Painter

/**
 * Created by zhangshengli on 2017/11/16.
 */
class Wall(override var x: Int, override var y: Int) :BaseModel() {
    override fun draw() {
        Painter.drawImage("img/wall.gif",x,y)
    }

}