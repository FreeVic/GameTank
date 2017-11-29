package model

import interfaces.Blockable
import interfaces.Destroyedable
import interfaces.View
import manager.Config
import org.itheima.kotlin.game.core.Composer
import org.itheima.kotlin.game.core.Painter

/**
 * Created by zhangshengli on 2017/11/16.
 */
class Wall(override var x: Int, override var y: Int, override var width: Int= Config.BLOCK, override var height: Int = Config.BLOCK) :Blockable,Sufferable,Destroyedable {
    override var isDestroy: Boolean = false

    override fun isDestroyed(): Boolean = blood<=0

    override var blood: Int = 3

    override fun notifySuffer(attackable: Attackable):Array<View>? {
        blood-=attackable.attackPower
        Composer.play("snd/fire.wav")
        println("suffer ${attackable.x}:${attackable.y}")
        return arrayOf(Blast(x,y))
    }

    override fun draw() {
        Painter.drawImage("img/wall.gif",x,y)
    }

}