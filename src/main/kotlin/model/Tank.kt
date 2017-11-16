package model

import org.itheima.kotlin.game.core.Painter

/**
 * Created by zhangshengli on 2017/11/16.
 */
class Tank : BaseModel() {
    override fun draw(direction: Direction) {
        Painter.drawImage(getPath(direction), x, y)
    }

    fun getPath(direction: Direction) =
            when (direction) {
                Direction.UP -> "img/tank_u.gif"
                Direction.DOWN -> "img/tank_d.gif"
                Direction.LEFT -> "img/tank_l.gif"
                Direction.RIGHT -> "img/tank_r.gif"
            }
}