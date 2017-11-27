package interfaces

import model.Direction

interface AutoMoveable {
    var currentDirection:Direction
    var speed:Int

    fun autoMove()
}