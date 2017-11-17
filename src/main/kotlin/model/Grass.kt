package model

import org.itheima.kotlin.game.core.Painter

/**
 * Created by zhangshengli on 2017/11/16.
 */
class Grass(override var x: Int, override var y: Int) :BaseModel() {
    override fun draw() {
        Painter.drawImage("img/grass.gif",x,y)
    }

}