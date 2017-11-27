package model

import interfaces.View


interface Sufferable: View {
    var blood:Int
    fun notifySuffer(attackable: Attackable)
}