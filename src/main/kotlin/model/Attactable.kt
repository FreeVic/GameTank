package model

import interfaces.View

interface Attackable :View{
    var attackPower:Int
    fun notifyAttack(sufferable: Sufferable)
}