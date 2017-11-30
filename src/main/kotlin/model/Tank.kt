package model

import interfaces.Blockable
import interfaces.Moveable
import manager.Config
import org.itheima.kotlin.game.core.Painter

/**
 * Created by zhangshengli on 2017/11/16.
 */
class Tank(override var x: Int, override var y: Int, override var width: Int = Config.BLOCK, override var height: Int = Config.BLOCK) : Moveable {
    override var currentDirection: Direction = Direction.UP
    override var badDirection: Direction? = null
    override fun notifyCollision(badDirection: Direction?, badBlock: Blockable?) {
        this.badDirection = badDirection
    }
    override var speed = 8

    override fun draw() {
        Painter.drawImage(getPath(currentDirection), x, y)
    }

    private fun getPath(direction: Direction) =
            when (direction) {
                Direction.UP -> "img/tank_u.gif"
                Direction.DOWN -> "img/tank_d.gif"
                Direction.LEFT -> "img/tank_l.gif"
                Direction.RIGHT -> "img/tank_r.gif"
            }

    fun move(outDir: Direction) {
        if(outDir != currentDirection){
            currentDirection = outDir
        }else{
            if (badDirection == currentDirection) {
                return
            }
            when (outDir) {
                Direction.UP -> y -= speed
                Direction.DOWN -> y += speed
                Direction.LEFT -> x -= speed
                Direction.RIGHT -> x += speed
            }
            // check boundry
            if (x < 0) x = 0
            if (x > Config.GAMEWIDTH - width) x = Config.GAMEWIDTH - width
            if (y < 0) y = 0
            if (y > Config.GAMEWIDTH - width) y = Config.GAMEWIDTH - width
        }
    }

    fun shot(): Bullet {
        return Bullet(0, 0, 0, 0, currentDirection) { dir, bWidth, bHeight ->
            var result: Pair<Int, Int> = Pair(0, 0)
            when (dir) {
                Direction.UP -> {
                    result = Pair(x + (width - bWidth) / 2, y - bHeight / 2)
                }
                Direction.DOWN -> {
                    result = Pair(x + (width - bWidth) / 2, y + bHeight / 2)
                }
                Direction.LEFT -> {
                    result = Pair(x - bWidth / 2, y + (height - bHeight) / 2)
                }
                Direction.RIGHT -> {
                    result = Pair(x + bWidth / 2, y + (height - bHeight) / 2)
                }
            }
            result
        }
    }
}