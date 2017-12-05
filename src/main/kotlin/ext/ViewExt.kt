package ext

import interfaces.View

fun View.isWillCollision(v: View): Boolean = when {
    (v.x + v.width <= x) && (v.x <= x) -> false
    (x + width <= v.x) && (x <= v.x) -> false
    (v.y + v.height <= y) && (v.y <= y) -> false
    (y + height <= v.y) && (y <= v.y) -> false
    else -> true
}

//fun View.isWillCollision(v: View): Boolean{
//    return if (this.x >= v.x && this.x >= v.x + v.width) false
//    else if (this.x <= v.x && this.x + this.width <= v.x) false
//    else if (this.y >= v.y && this.y >= v.y + v.height) false
//    else !(this.y <= v.y && this.y + this.height <= v.y)
//}