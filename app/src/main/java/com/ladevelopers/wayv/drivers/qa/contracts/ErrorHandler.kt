package com.ladevelopers.wayv.drivers.qa.contracts

interface ErrorHandler {
    fun handle(throwable: Throwable)
}