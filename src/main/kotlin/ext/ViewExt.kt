package ext

import interfaces.View

fun View.isWillCollision(v: View): Boolean = when {
    (v.x + v.width <= x) && (v.x <= x) -> false
    (x + width <= v.x) && (x <= v.x) -> false
    (v.y + v.height <= y) && (v.y <= y) -> false
    (y + height <= v.y) && (y <= v.y) -> false
    else -> true
}