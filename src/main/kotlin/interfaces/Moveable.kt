package interfaces

import manager.Config
import model.Direction


/**
 * Created by zhangshengli on 2017/11/19.
 */
interface Moveable :View{
    var currentDirection:Direction
    var badDirection:Direction?
    var speed:Int
    fun isWillCollision(blockable: Blockable):Direction?{

        var x = this.x
        var y = this.y

        when (currentDirection) {
            Direction.UP -> y -= speed
            Direction.DOWN -> y += speed
            Direction.LEFT -> x -= speed
            Direction.RIGHT -> x += speed
        }

        if (x < 0) return Direction.LEFT
        if (x > Config.GAMEWIDTH - width) return Direction.RIGHT
        if (y < 0) return Direction.UP
        if (y > Config.GAMEWIDTH - width) return Direction.DOWN
        return when {
            blockable.x + blockable.width <= x -> null
            x + width <= blockable.x -> null
            blockable.y + blockable.width <= y -> null
            y + width <= blockable.y -> null
            else -> currentDirection
        }
    }

    fun notifyCollision(badDirection:Direction?,badBlock: Blockable?)
}