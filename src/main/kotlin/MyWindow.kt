import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.paint.Color
import model.Direction
import model.Tank
import org.itheima.kotlin.game.core.Painter
import org.itheima.kotlin.game.core.Window

class MyWindow : Window("小坦坦","img/tank_u.gif",Config.BLOCK*13,Config.BLOCK*13){
    lateinit var tank:Tank
    var direction:Direction = Direction.UP
    override fun onCreate() {
        println("window onCreate")
    }

    override fun onDisplay() {
        println("window onDisplay")
        tank = Tank()
        tank.draw(direction)
    }

    override fun onKeyPressed(event: KeyEvent) {
        direction =
        when(event.code){
            KeyCode.W-> Direction.UP
            KeyCode.S->Direction.DOWN
            KeyCode.A-> Direction.LEFT
            KeyCode.D-> Direction.RIGHT
            else->Direction.UP
        }
    }

    override fun onRefresh() {
        println("window onRefresh")
    }
}