import ext.isWillCollision
import interfaces.*
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import manager.Config
import model.*
import org.itheima.kotlin.game.core.Composer
import java.io.File
import java.util.ArrayList
import java.util.concurrent.CopyOnWriteArrayList

object GameManager {
    lateinit var tank: Tank
    lateinit var list: CopyOnWriteArrayList<View>
    var maxEnemyCount = 20
    var activeEnemyCount = 6
    var enemyBornPos:ArrayList<Pair<Int,Int>> = ArrayList()
    var enemyIndex = 0
    var isGameOver = false
    fun create() {
        list = CopyOnWriteArrayList()
        val file = File(javaClass.getResource("map/1.map").path)
        file.readLines().forEachIndexed { indexY, line ->
            line.toCharArray().forEachIndexed { indexX, char ->
                when (char) {
                    '铁' -> list.add(Steel(indexX * Config.BLOCK, indexY * Config.BLOCK))
                    '砖' -> list.add(Wall(indexX * Config.BLOCK, indexY * Config.BLOCK))
                    '水' -> list.add(Water(indexX * Config.BLOCK, indexY * Config.BLOCK))
                    '草' -> list.add(Grass(indexX * Config.BLOCK, indexY * Config.BLOCK))
                    '敌' -> enemyBornPos.add(Pair(indexX * Config.BLOCK, indexY * Config.BLOCK))
                }
            }
        }
        tank = Tank(Config.BLOCK * 4, Config.GAMEWIDTH - Config.BLOCK)
        list.add(tank)

        list.add(Camp())

        Composer.playLoop("snd/bg3.wav")
    }

    fun disPlay() {
        list.forEach { it.draw() }

        // remove Destroyed view
        list.filter { it is Destroyedable }.forEach {
            it as Destroyedable
            if (it.isDestroyed())
                list.remove(it)
        }

        if(isGameOver)
            return

        // checkCollision of moveable
        var badDirection: Direction? = null
        var badBlock: Blockable? = null
        list.filter { it is Moveable }.forEach { move ->
            move as Moveable
            list.filter { it is Blockable && move != it }.forEach blockTag@ { block ->
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
            list.filter { (it is Sufferable) and (attack.ower != it) and (attack != it) and !(it is Attackable && attack.ower==it.ower) }.forEach sufferTag@ { suffer ->
                suffer as Sufferable
                if (attack.isWillCollision(suffer)) {
                    attack.notifyAttack(suffer)
                    val blasts = suffer.notifySuffer(attack)
                    blasts?.let {
                        list.addAll(blasts)
                    }
                    return@attackTag
                }
            }

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

        // enemy auto generate
        if((list.filter { it is Enemy }.size < activeEnemyCount) and (maxEnemyCount > 0)){
            val index = enemyIndex%enemyBornPos.size
            list.add(Enemy(enemyBornPos[index].first,enemyBornPos[index].second))
            maxEnemyCount--
            enemyIndex++
        }

        // check GameOver
        isGameOver = list.none { it is Camp }
        isGameOver = (maxEnemyCount == 0) && list.none{ it is Enemy}
    }

    fun gameLogic() {

    }

    fun onKeyEvent(event: KeyEvent) {
        if(isGameOver)
            return
        when (event.code) {
            KeyCode.W -> tank.move(Direction.UP)
            KeyCode.S -> tank.move(Direction.DOWN)
            KeyCode.A -> tank.move(Direction.LEFT)
            KeyCode.D -> tank.move(Direction.RIGHT)
            KeyCode.ENTER -> list.add(tank.shot())
        }
    }
}