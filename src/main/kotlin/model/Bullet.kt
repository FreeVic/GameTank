package model

import interfaces.AutoMoveable
import interfaces.Destroyedable
import interfaces.View
import manager.Config
import org.itheima.kotlin.game.core.Painter

class Bullet(override var ower: View, override var x: Int, override var y: Int, override var width: Int, override var height: Int, var direction: Direction, create: (dir: Direction, bWidth: Int, bHeight: Int) -> Pair<Int, Int>) : AutoMoveable, Destroyedable, Attackable, Sufferable {
    override var blood: Int = 1
    override var attackPower: Int = 2
    override var isDestroy: Boolean = false
    override var currentDirection = direction
    override var speed = 8

    init {
        val size = Painter.size(getPath())
        width = size[0]
        height = size[1]
        val invoke = create(direction, width, height)
        x = invoke.first
        y = invoke.second
    }

    override fun notifyAttack(sufferable: Sufferable) {
        isDestroy = true
    }

    override fun autoMove() {
        when (currentDirection) {
            Direction.UP -> y -= speed
            Direction.DOWN -> y += speed
            Direction.LEFT -> x -= speed
            Direction.RIGHT -> x += speed
        }
    }

    override fun isDestroyed(): Boolean {
        if (isDestroy)
            return true
        return x < 0 || x > Config.GAMEWIDTH || y < 0 || y > Config.GAMEWIDTH
    }

    fun getPath() = when (direction) {
        Direction.UP -> "img/bullet_u.gif"
        Direction.DOWN -> "img/bullet_d.gif"
        Direction.LEFT -> "img/bullet_l.gif"
        Direction.RIGHT -> "img/bullet_r.gif"
    }

    override fun draw() {
        Painter.drawImage(getPath(), x, y)
    }

    override fun notifySuffer(attackable: Attackable): Array<View>? {
        isDestroy = true
        return arrayOf(Blast(x, y))
    }
}