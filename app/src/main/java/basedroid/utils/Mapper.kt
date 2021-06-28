package basedroid.utils

interface Mapper<S, T> {
    fun map(from: S): T
}