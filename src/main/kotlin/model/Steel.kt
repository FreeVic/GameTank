package model

import interfaces.Blockable
import interfaces.View
import manager.Config
import org.itheima.kotlin.game.core.Painter

/**
 * Created by zhangshengli on 2017/11/16.
 */
class Steel(override var x: Int, override var y: Int, override var width: Int = Config.BLOCK, override var height: Int = Config.BLOCK) : Blockable, Sufferable {
    override var blood: Int = 1

    override fun notifySuffer(attackable: Attackable): Array<View>? = null

    override fun draw() {
        Painter.drawImage("img/steel.gif", x, y)
    }

}