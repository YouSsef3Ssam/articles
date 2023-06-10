package com.youssef.utils

interface BaseMapper<T, K> {
    fun map(data: T): K
}
