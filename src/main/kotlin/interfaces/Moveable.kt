package interfaces

import model.Direction


/**
 * Created by zhangshengli on 2017/11/19.
 */
interface Moveable :View{
    fun isWillCollision(blockable: Blockable):Direction?

    fun notifyCollision(badDirection:Direction?,badBlock: Blockable?)
}