package model

import interfaces.Blockable
import interfaces.Destroyedable
import interfaces.View
import manager.Config
import org.itheima.kotlin.game.core.Composer
import org.itheima.kotlin.game.core.Painter

/**
 * Created by zhangshengli on 2017/12/2.
 */
class Camp :Blockable,Sufferable,Destroyedable{
    override var isDestroy: Boolean = false

    override fun isDestroyed(): Boolean {
        return blood<=0
    }

    override var blood: Int = 7

    override fun notifySuffer(attackable: Attackable): Array<View>? {
        blood-=attackable.attackPower
        Composer.play("snd/fire.wav")
        return null
    }

    override var x: Int = Config.GAMEWIDTH/2-Config.BLOCK
    override var y: Int = Config.GAMEWIDTH-Config.BLOCK-Config.BLOCK_SMALL
    override var width: Int = Config.BLOCK*2
    override var height: Int = Config.BLOCK+Config.BLOCK_SMALL

    override fun draw() {
        if(blood<=1){
            Painter.drawImage("img/symbol.gif",(Config.GAMEWIDTH-Config.BLOCK)/2,Config.GAMEWIDTH-Config.BLOCK)
        } else if(blood<=3){
            Painter.drawImage("img/symbol.gif",(Config.GAMEWIDTH-Config.BLOCK)/2,Config.GAMEWIDTH-Config.BLOCK)

            Painter.drawImage("img/wall_small.gif",Config.GAMEWIDTH/2-Config.BLOCK,Config.GAMEWIDTH-Config.BLOCK_SMALL*3)
            Painter.drawImage("img/wall_small.gif",Config.GAMEWIDTH/2-Config.BLOCK,Config.GAMEWIDTH-Config.BLOCK_SMALL*2)
            Painter.drawImage("img/wall_small.gif",Config.GAMEWIDTH/2-Config.BLOCK,Config.GAMEWIDTH-Config.BLOCK_SMALL*1)

            Painter.drawImage("img/wall_small.gif",Config.GAMEWIDTH/2-Config.BLOCK+Config.BLOCK_SMALL,Config.GAMEWIDTH-Config.BLOCK_SMALL*3)
            Painter.drawImage("img/wall_small.gif",Config.GAMEWIDTH/2-Config.BLOCK+Config.BLOCK_SMALL*2,Config.GAMEWIDTH-Config.BLOCK_SMALL*3)
            Painter.drawImage("img/wall_small.gif",Config.GAMEWIDTH/2-Config.BLOCK+Config.BLOCK_SMALL*3,Config.GAMEWIDTH-Config.BLOCK_SMALL*3)

            Painter.drawImage("img/wall_small.gif",Config.GAMEWIDTH/2-Config.BLOCK+Config.BLOCK_SMALL*3,Config.GAMEWIDTH-Config.BLOCK_SMALL*2)
            Painter.drawImage("img/wall_small.gif",Config.GAMEWIDTH/2-Config.BLOCK+Config.BLOCK_SMALL*3,Config.GAMEWIDTH-Config.BLOCK_SMALL*1)


        }else if(blood<=7){
            Painter.drawImage("img/symbol.gif",(Config.GAMEWIDTH-Config.BLOCK)/2,Config.GAMEWIDTH-Config.BLOCK)

            Painter.drawImage("img/steel_small.gif",Config.GAMEWIDTH/2-Config.BLOCK,Config.GAMEWIDTH-Config.BLOCK_SMALL*3)
            Painter.drawImage("img/steel_small.gif",Config.GAMEWIDTH/2-Config.BLOCK,Config.GAMEWIDTH-Config.BLOCK_SMALL*2)
            Painter.drawImage("img/steel_small.gif",Config.GAMEWIDTH/2-Config.BLOCK,Config.GAMEWIDTH-Config.BLOCK_SMALL*1)

            Painter.drawImage("img/steel_small.gif",Config.GAMEWIDTH/2-Config.BLOCK+Config.BLOCK_SMALL,Config.GAMEWIDTH-Config.BLOCK_SMALL*3)
            Painter.drawImage("img/steel_small.gif",Config.GAMEWIDTH/2-Config.BLOCK+Config.BLOCK_SMALL*2,Config.GAMEWIDTH-Config.BLOCK_SMALL*3)
            Painter.drawImage("img/steel_small.gif",Config.GAMEWIDTH/2-Config.BLOCK+Config.BLOCK_SMALL*3,Config.GAMEWIDTH-Config.BLOCK_SMALL*3)

            Painter.drawImage("img/steel_small.gif",Config.GAMEWIDTH/2-Config.BLOCK+Config.BLOCK_SMALL*3,Config.GAMEWIDTH-Config.BLOCK_SMALL*2)
            Painter.drawImage("img/steel_small.gif",Config.GAMEWIDTH/2-Config.BLOCK+Config.BLOCK_SMALL*3,Config.GAMEWIDTH-Config.BLOCK_SMALL*1)
        }
    }
}