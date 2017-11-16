package model

import interfaces.IBase

/**
 * Created by zhangshengli on 2017/11/16.
 */
abstract class BaseModel:IBase{
    var x = 0
    var y = 0
    var width = Config.BLOCK
    var height = Config.BLOCK
}