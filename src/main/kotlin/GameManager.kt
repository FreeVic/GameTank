import ext.isWillCollision
import interfaces.*
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import manager.Config
import model.*
import java.io.File
import java.util.ArrayList
import java.util.concurrent.CopyOnWriteArrayList

object GameManager {
    lateinit var tank: Tank
    lateinit var list: CopyOnWriteArrayList<View>
    fun create() {
        list = CopyOnWriteArrayList()
        val file = File(javaClass.getResource("map/1.map").path)
        file.readLines().forEachIndexed { indexY, line ->
            println("$indexY:$line")
            line.toCharArray().forEachIndexed { indexX, char ->
                when (char) {
                    '铁' -> list.add(Steel(indexX * Config.BLOCK, indexY * Config.BLOCK))
                    '砖' -> list.add(Wall(indexX * Config.BLOCK, indexY * Config.BLOCK))
                    '水' -> list.add(Water(indexX * Config.BLOCK, indexY * Config.BLOCK))
                    '草' -> list.add(Grass(indexX * Config.BLOCK, indexY * Config.BLOCK))
                    '敌'-> list.add(Enemy(indexX * Config.BLOCK, indexY * Config.BLOCK))
                    '我'->{

                    }
                }
            }
        }
        tank = Tank(Config.BLOCK*4, Config.GAMEWIDTH-Config.BLOCK)
        list.add(tank)

        list.add(Camp())
    }

    fun disPlay() {
        list.forEach { it.draw() }

        // checkCollision of moveable
        var badDirection: Direction? = null
        var badBlock: Blockable? = null
        list.filter { it is Moveable }.forEach { move ->
            move as Moveable
            list.filter { it is Blockable && move!=it }.forEach blockTag@ { block ->
                block as Blockable
                val result = move.isWillCollision(block)
                result?.let {
                    badDirection = result
                    badBlock = block
                    return@blockTag
                }
            }
            move.notifyCollision(badDirection, badBlock)
        }

        // check collision of attack
        list.filter { it is Attackable }.forEach attackTag@ { attack ->
            attack as Attackable
            list.filter { (it is Sufferable) and(attack.ower!=it) }.forEach sufferTag@ { suffer ->
                suffer as Sufferable
                val result = attack.isWillCollision(suffer)
                if (result) {
                    attack.notifyAttack(suffer)
                    val blasts = suffer.notifySuffer(attack)
                    blasts?.let{
                        list.addAll(blasts)
                    }
                    return@attackTag
                }
            }

        }

        // remove Destroyed view
        list.filter { it is Destroyedable }.forEach {
            it as Destroyedable
            if (it.isDestroyed())
                list.remove(it)
        }

        // autoMove
        list.filter { it is AutoMoveable }.forEach {
            it as AutoMoveable
            it.autoMove()
        }

        // autoShot
        list.filter { it is AutoShot }.forEach {
            it as AutoShot
            it.autoShot()?.let {
                list.add(it)
            }
        }
    }

    fun gameLogic() {

    }

    fun onKeyEvent(event: KeyEvent) {
        when (event.code) {
            KeyCode.W -> tank.move(Direction.UP)
            KeyCode.S -> tank.move(Direction.DOWN)
            KeyCode.A -> tank.move(Direction.LEFT)
            KeyCode.D -> tank.move(Direction.RIGHT)
            KeyCode.ENTER -> list.add(tank.shot())
        }
    }
}