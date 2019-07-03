package com.vincent.dialoglibrary

/**
 * <p>文件描述：<p>
 * <p>@author 烤鱼<p>
 * <p>@date 2019/7/3 0003 <p>
 * <p>@update 2019/7/3 0003<p>
 * <p>版本号：1<p>
 *
 */
class Builder<B : Builder<B>> {

    private var a: Int = 0

    fun setA(b: Int): B {
        this.a = b
        return this as B
    }
}