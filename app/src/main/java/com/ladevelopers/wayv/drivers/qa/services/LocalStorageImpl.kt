package com.ladevelopers.wayv.drivers.qa.services

import android.content.Context
import com.google.gson.Gson
import com.ladevelopers.wayv.drivers.qa.contracts.LocalStorage

class LocalStorageImpl(context: Context) : LocalStorage {

    private val preferences = context.getSharedPreferences(
            "${context.packageName}.preferences", Context.MODE_PRIVATE)
    private val gson = Gson()

    override fun get(key: String, type: Class<*>): Any {
        val json = preferences.getString(key, "")
        if (json.isNullOrEmpty())
            throw IndexOutOfBoundsException("Cannot find '$key' key in shared preferences")
        return gson.fromJson(json, type)
    }

    override fun set(key: String, value: Any) {
        val editor = preferences.edit()
        val json = gson.toJson(value)
        editor.putString(key, json)
        editor.apply()
    }

    override fun contains(key: String) = preferences.contains(key)
}