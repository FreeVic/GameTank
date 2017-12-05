package model

import interfaces.*
import manager.Config
import org.itheima.kotlin.game.core.Painter
import java.util.*

class Enemy(override var x: Int, override var y: Int) : View, Moveable, Blockable, AutoMoveable, AutoShot,Sufferable,Destroyedable {
    override var isDestroy: Boolean = false

    override fun isDestroyed(): Boolean = blood<=0

    override var ower: View = this
    override var badDirection: Direction? = null
    override var speed: Int = 8
    override var width: Int = Config.BLOCK
    override var height: Int = Config.BLOCK
    override var currentDirection: Direction = Direction.DOWN
    override var attackPower: Int = 1
    override var blood: Int = 3

    override fun notifyAttack(sufferable: Sufferable) {

    }
    override fun notifySuffer(attackable: Attackable): Array<View>? {
        if(attackable.ower is Enemy){
            return null
        }else{
            blood-=attackable.attackPower
            return arrayOf(Blast(x,y))
        }
    }
    override fun draw() {
        Painter.drawImage(getPath(currentDirection), x, y)
    }

    private fun getPath(direction: Direction) =
            when (direction) {
                Direction.UP -> "img/enemy_1_u.gif"
                Direction.DOWN -> "img/enemy_1_d.gif"
                Direction.LEFT -> "img/enemy_1_l.gif"
                Direction.RIGHT -> "img/enemy_1_r.gif"
            }

    override fun notifyCollision(badDirection: Direction?, badBlock: Blockable?) {
        this.badDirection = badDirection
    }

    private fun changeDirection(bad: Direction?): Direction {
        val rdmDirection = rdmDirection()
        if (rdmDirection == bad)
            return changeDirection(bad)
        return rdmDirection
    }

    override fun autoMove() {
        if (isFastMove())
            return
        if (badDirection == currentDirection) {
            this.currentDirection = changeDirection(badDirection)
            return
        }
        when (currentDirection) {
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

    override fun autoShot(): View? {
        if(isFastShot())
            return null
        return Bullet(this,0, 0, 0, 0, currentDirection) { dir, bWidth, bHeight ->
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

    private fun rdmDirection(): Direction {
        val random = Random()
        val nextInt = random.nextInt(4)
        println(nextInt)
        return when (nextInt) {
            0 -> Direction.DOWN
            1 -> Direction.LEFT
            2 -> Direction.UP
            3 -> Direction.RIGHT
            else -> Direction.UP
        }
    }

    private var lastMoveTime = 0L
    private var autoMoveFrequency = 50
    private fun isFastMove(): Boolean {
        val current = System.currentTimeMillis()
        val result = current - lastMoveTime < autoMoveFrequency
        if (!result) {
            lastMoveTime = current
        }
        return result
    }

    var lastShotTime = 0L
    var autoShotFrequency = 1000
    private fun isFastShot():Boolean{
        val current = System.currentTimeMillis()
        val result = current - lastShotTime < autoShotFrequency
        if (!result) {
            lastShotTime = current
        }
        return result
    }
}