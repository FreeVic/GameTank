package model

import interfaces.Blockable
import interfaces.Moveable
import manager.Config
import GameManager
import org.itheima.kotlin.game.core.Painter

/**
 * Created by zhangshengli on 2017/11/16.
 */
class Tank(override var x: Int, override var y: Int, override var width: Int = Config.BLOCK, override var height: Int = Config.BLOCK) : Moveable {
    override fun isWillCllision(blockable: Blockable): Boolean {
        var x = this.x
        var y = this.y
        when (direction) {
            Direction.UP -> y -= speed
            Direction.DOWN -> y += speed
            Direction.LEFT -> x -= speed
            Direction.RIGHT -> x += speed
        }

        return when {
            blockable.x + blockable.width <= x -> false
            x + width <= blockable.x -> false
            blockable.y + blockable.width <= y -> false
            y + width <= blockable.y -> false
            else -> true
        }
    }

    var direction: Direction = Direction.UP
    var speed = 8

    override fun draw() {
        Painter.drawImage(getPath(direction), x, y)
    }

    fun getPath(direction: Direction) =
            when (direction) {
                Direction.UP -> "img/tank_u.gif"
                Direction.DOWN -> "img/tank_d.gif"
                Direction.LEFT -> "img/tank_l.gif"
                Direction.RIGHT -> "img/tank_r.gif"
            }

    fun move(outDir: Direction) {
        // check same direction
        if (direction != outDir) {
            direction = outDir
            return
        }
        // check collision
        if (!GameManager.checkCollision(this)) {
            when (outDir) {
                Direction.UP -> y -= speed
                Direction.DOWN -> y += speed
                Direction.LEFT -> x -= speed
                Direction.RIGHT -> x += speed
            }
        }
        if (x < 0) x = 0
        if (x > Config.GAMEWIDTH) x= Config.GAMEWIDTH
        if (y < 0) y = 0
        if (y > Config.GAMEWIDTH) y = Config.GAMEWIDTH
    }

    fun shot():Bullet{
            return Bullet(0,0,0,0,direction){dir,bWidth, bHeight->
                var result:Pair<Int,Int> = Pair(0,0)
                when(dir){
                    Direction.UP->{
                        result= Pair(x+(width-bWidth)/2,y-bHeight/2)
                    }
                }
                result
            }
    }
}