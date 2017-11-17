package interfaces

import model.Direction

/**
 * Created by zhangshengli on 2017/11/16.
 */
interface IView {
    var x:Int
    var y:Int
    var width:Int
    var height:Int
    fun draw()
}