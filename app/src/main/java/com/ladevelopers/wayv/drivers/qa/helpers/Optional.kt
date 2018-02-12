package com.ladevelopers.wayv.drivers.qa.helpers

class Optional<T>(val value: T?) {
    fun isPresent() = value != null
}