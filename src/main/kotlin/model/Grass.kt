package model

import interfaces.View
import manager.Config
import org.itheima.kotlin.game.core.Painter

/**
 * Created by zhangshengli on 2017/11/16.
 */
class Grass(override var x: Int, override var y: Int, override var width: Int= Config.BLOCK, override var height: Int = Config.BLOCK) :View {
    override fun draw() {
        Painter.drawImage("img/grass.gif",x,y)
    }

}