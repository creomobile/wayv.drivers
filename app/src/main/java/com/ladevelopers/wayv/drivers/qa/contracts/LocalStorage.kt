package com.ladevelopers.wayv.drivers.qa.contracts

interface LocalStorage {
    fun get(key: String, type: Class<*>): Any
    fun set(key: String, value: Any)
    fun contains(key: String): Boolean
}

inline fun <reified T> LocalStorage.get(key: String) = this.get(key, T::class.java) as T