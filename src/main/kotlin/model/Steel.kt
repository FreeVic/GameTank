package model

import interfaces.IBase
import org.itheima.kotlin.game.core.Painter

/**
 * Created by zhangshengli on 2017/11/16.
 */
class Steel :BaseModel() {
    override fun draw(direction: Direction) {
        Painter.drawImage("img/steel.gif",x,y)
    }

}