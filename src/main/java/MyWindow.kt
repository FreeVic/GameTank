import javafx.scene.input.KeyEvent
import org.itheima.kotlin.game.core.Window

class MyWindow : Window(){
    override fun onCreate() {
        println("window onCreate")
    }

    override fun onDisplay() {
        println("window onDisplay")
    }

    override fun onKeyPressed(event: KeyEvent) {
        println("window keyEvent")
    }

    override fun onRefresh() {
        println("window onRefresh")
    }
}