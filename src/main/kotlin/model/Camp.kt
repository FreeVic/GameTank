package model

import interfaces.View
import manager.Config

/**
 * Created by zhangshengli on 2017/12/2.
 */
class Camp(override var x: Int, override var y: Int) :View{
    override var width: Int = Config.BLOCK
    override var height: Int = Config.BLOCK

    override fun draw() {
        
    }
}