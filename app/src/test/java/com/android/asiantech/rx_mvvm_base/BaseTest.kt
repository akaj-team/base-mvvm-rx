package com.android.asiantech.rx_mvvm_base

import org.mockito.Mockito

/**
 * Use for mockito
 */
open class BaseTest {

    @Suppress("UNCHECKED_CAST")
    fun <T> any(): T {
        Mockito.any<T>()
        return null as T
    }
}
