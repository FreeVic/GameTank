package model

import interfaces.Blockable
import org.itheima.kotlin.game.core.Painter

/**
 * Created by zhangshengli on 2017/11/16.
 */
class Wall(override var x: Int, override var y: Int,override var width: Int=Config.BLOCK, override var height: Int = Config.BLOCK) :Blockable {
    override fun draw() {
        Painter.drawImage("img/wall.gif",x,y)
    }

}