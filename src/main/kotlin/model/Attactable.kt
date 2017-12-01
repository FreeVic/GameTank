package model

import interfaces.View

interface Attackable :View{
    var attackPower:Int
    var ower:View
    fun notifyAttack(sufferable: Sufferable)
}