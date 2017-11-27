import interfaces.AutoMoveable
import interfaces.Blockable
import interfaces.Destroyedable
import interfaces.View
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
        tank = Tank(0, 0)
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
                }
            }
        }
    }

    fun disPlay() {
        list.forEach { it.draw() }
        // remove Destroyed view
        list.filter { it is Destroyedable }.forEach {
            it as Destroyedable
            if(it.isDestroyed())
                list.remove(it)
        }

        // autoMove
        list.filter { it is AutoMoveable }.forEach {
            it as AutoMoveable
            it.autoMove()
        }
        tank.draw()
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

    fun checkCollision(tank: Tank): Boolean {
        // check collision
        var isWillCollision = false
        list.filter { it is Blockable }.forEach {
            it as Blockable
            if (tank.isWillCllision(it)) {
                isWillCollision = true
            }
        }
        return isWillCollision
    }
}