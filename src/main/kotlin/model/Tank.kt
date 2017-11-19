package model

import org.itheima.kotlin.game.core.Painter

/**
 * Created by zhangshengli on 2017/11/16.
 */
class Tank(override var x: Int, override var y: Int) : BaseModel() {
    var direction:Direction = Direction.UP
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

    fun move(outDir: Direction){
        if(direction != outDir){
            direction = outDir
            return
        }
        when(outDir){
            Direction.UP->y-=speed
            Direction.DOWN->y+=speed
            Direction.LEFT->x-=speed
            Direction.RIGHT->x+=speed
        }
        if(x<0) x=0
        if(x>Config.GAMEWIDTH) x = Config.GAMEWIDTH
        if(y<0) y=0
        if(y>Config.GAMEWIDTH) y = Config.GAMEWIDTH

    }
}