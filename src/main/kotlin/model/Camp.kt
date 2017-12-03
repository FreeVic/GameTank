package model

import interfaces.View
import manager.Config
import org.itheima.kotlin.game.core.Painter

/**
 * Created by zhangshengli on 2017/12/2.
 */
class Camp() :View{
    override var x: Int = 0
    override var y: Int = 0
    override var width: Int = Config.BLOCK
    override var height: Int = Config.BLOCK

    override fun draw() {
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