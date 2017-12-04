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
class Camp : Blockable, Sufferable, Destroyedable {
    override var isDestroy: Boolean = false
    override var blood: Int = 12
    override var x: Int = Config.GAMEWIDTH / 2 - Config.BLOCK
    override var y: Int = Config.GAMEWIDTH - Config.BLOCK_SMALL * 3
    override var width: Int = Config.BLOCK_SMALL * 4
    override var height: Int = Config.BLOCK_SMALL * 3
    var isBlasted = arrayOf(false,false,false)
    var subWallPos: Array<Pair<Int, Int>> = arrayOf(
            Pair(Config.GAMEWIDTH / 2 - Config.BLOCK, Config.GAMEWIDTH - Config.BLOCK_SMALL * 1),
            Pair(Config.GAMEWIDTH / 2 - Config.BLOCK, Config.GAMEWIDTH - Config.BLOCK_SMALL * 2),
            Pair(Config.GAMEWIDTH / 2 - Config.BLOCK, Config.GAMEWIDTH - Config.BLOCK_SMALL * 3),
            Pair(Config.GAMEWIDTH / 2 - Config.BLOCK + Config.BLOCK_SMALL * 1, Config.GAMEWIDTH - Config.BLOCK_SMALL * 3),
            Pair(Config.GAMEWIDTH / 2 - Config.BLOCK + Config.BLOCK_SMALL * 2, Config.GAMEWIDTH - Config.BLOCK_SMALL * 3),
            Pair(Config.GAMEWIDTH / 2 - Config.BLOCK + Config.BLOCK_SMALL * 3, Config.GAMEWIDTH - Config.BLOCK_SMALL * 3),
            Pair(Config.GAMEWIDTH / 2 - Config.BLOCK + Config.BLOCK_SMALL * 3, Config.GAMEWIDTH - Config.BLOCK_SMALL * 2),
            Pair(Config.GAMEWIDTH / 2 - Config.BLOCK + Config.BLOCK_SMALL * 3, Config.GAMEWIDTH - Config.BLOCK_SMALL * 1)
    )

    override fun isDestroyed(): Boolean {
        return blood <= 0
    }


    override fun notifySuffer(attackable: Attackable): Array<View>? {
        blood -= attackable.attackPower
        Composer.play("snd/fire.wav")
        if (blood<=0 && !isBlasted[0]) {
            isBlasted[0] = true
            return arrayOf(Blast(x,y))
        }else if(blood <= 3 && !isBlasted[1]){
            isBlasted[1] = true
            return subWallPos.map { Blast(it.first-Config.BLOCK_SMALL/2, it.second-Config.BLOCK_SMALL/2) }.toTypedArray()
        }else if(blood<=6 && !isBlasted[2]){
            isBlasted[2] = true
            return subWallPos.map { Blast(it.first-Config.BLOCK_SMALL/2, it.second-Config.BLOCK_SMALL/2) }.toTypedArray()
        }
        return null
    }

    override fun draw() {
        if (blood <= 3) {
            x = Config.GAMEWIDTH / 2 - Config.BLOCK_SMALL
            y = Config.GAMEWIDTH - Config.BLOCK
            width = Config.BLOCK
            height = Config.BLOCK
            Painter.drawImage("img/symbol.gif", x, y)
        } else if (blood <= 6) {
            Painter.drawImage("img/symbol.gif", (Config.GAMEWIDTH - Config.BLOCK) / 2, Config.GAMEWIDTH - Config.BLOCK)
            subWallPos.forEach { Painter.drawImage("img/wall_small.gif", it.first, it.second) }

        } else if (blood <= 12) {
            Painter.drawImage("img/symbol.gif", (Config.GAMEWIDTH - Config.BLOCK) / 2, Config.GAMEWIDTH - Config.BLOCK)
            subWallPos.forEach { Painter.drawImage("img/steel_small.gif", it.first, it.second) }
        }
    }
}