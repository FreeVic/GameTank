package model

import interfaces.View

/**
 * Created by zhangshengli on 2017/11/16.
 */
abstract class BaseModel: View {
    override var height: Int
        get() = Config.BLOCK
        set(value) {}

    override var width: Int
        get() = Config.BLOCK
        set(value) {}
}