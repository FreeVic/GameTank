import interfaces.View
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import model.*
import org.itheima.kotlin.game.core.Window
import java.io.File
import java.util.*

class MyWindow : Window("小坦坦","img/tank_u.gif",Config.GAMEWIDTH,Config.GAMEWIDTH){
    lateinit var tank:Tank
    private lateinit var list: ArrayList<View>
    override fun onCreate() {
        println("window onCreate")
        createViews()
    }

    private fun createViews() {
        tank = Tank(0,0)
        list = ArrayList()
        File(javaClass.getResource("map/1.map").path).readLines().forEachIndexed { indexY, line ->
            println("$indexY:$line")
            line.toCharArray().forEachIndexed { indexX, char ->
                when(char){
                    '铁'-> list.add(Steel(indexX*Config.BLOCK,indexY*Config.BLOCK))
                    '砖'-> list.add(Wall(indexX*Config.BLOCK,indexY*Config.BLOCK))
                    '水'->list.add(Water(indexX*Config.BLOCK,indexY*Config.BLOCK))
                    '草'->list.add(Grass(indexX*Config.BLOCK,indexY*Config.BLOCK))
                }
            }
        }
    }

    override fun onDisplay() {
        list.forEach { it.draw() }
        tank.draw()
    }

    override fun onKeyPressed(event: KeyEvent) {
        var direction =
        when(event.code){
            KeyCode.W-> Direction.UP
            KeyCode.S->Direction.DOWN
            KeyCode.A-> Direction.LEFT
            KeyCode.D-> Direction.RIGHT
            else->Direction.UP
        }
        tank.move(direction)
    }

    override fun onRefresh() {
    }
}