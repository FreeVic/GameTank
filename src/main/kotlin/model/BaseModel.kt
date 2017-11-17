package model

import interfaces.IView

/**
 * Created by zhangshengli on 2017/11/16.
 */
abstract class BaseModel: IView {
    override var height: Int
        get() = Config.BLOCK
        set(value) {}

    override var width: Int
        get() = Config.BLOCK
        set(value) {}
}