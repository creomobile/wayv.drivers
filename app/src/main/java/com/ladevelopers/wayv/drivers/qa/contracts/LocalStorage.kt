package com.ladevelopers.wayv.drivers.qa.contracts

interface LocalStorage {
    fun get(key: String, type: Class<*>): Any
    fun set(key: String, value: Any)
    fun contains(key: String): Boolean
    fun remove(key: String)
}

inline fun <reified T> LocalStorage.get(key: String) = this.get(key, T::class.java) as T
inline fun <reified T> LocalStorage.getOrNull(key: String) =
        if (this.contains(key)) this.get<T>(key) else null
