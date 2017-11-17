package model

import org.itheima.kotlin.game.core.Painter

/**
 * Created by zhangshengli on 2017/11/16.
 */
class Steel(override var x: Int, override var y: Int) :BaseModel() {
    override fun draw() {
        Painter.drawImage("img/steel.gif", x, y)
    }

}