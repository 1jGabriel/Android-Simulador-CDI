package com.br.basedroid.utils

interface Mapper<S, T> {
    fun map(from: S): T
}