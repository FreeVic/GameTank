package manager

import javafx.scene.input.KeyEvent
import org.itheima.kotlin.game.core.Window

class MyWindow : Window("小坦坦", "img/tank_u.gif", Config.BLOCK*13, Config.BLOCK*13) {

    override fun onCreate() {
        GameManager.create()
    }



    override fun onDisplay() {
       GameManager.disPlay()
    }

    override fun onKeyPressed(event: KeyEvent) {
       GameManager.onKeyEvent(event)
    }


    override fun onRefresh() {
    }
}