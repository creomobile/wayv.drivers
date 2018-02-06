package com.ladevelopers.wayv.drivers.qa.helpers

internal class TelephonyHelper {
    companion object {
        fun formatPhone(phone: String?): String {
            // (XXX) XXX-XXXX
            if (phone == null) return ""
            val builder = StringBuilder(unformatPhone(phone))
            if (builder.isNotEmpty()) builder.insert(0, '(')
            if (builder.length > 4) builder.insert(4, ") ")
            if (builder.length > 9) builder.insert(9, '-')
            if (builder.length > 14) builder.setLength(14)
            return builder.toString()
        }

        fun unformatPhone(phone: String?) = phone?.replace("\\D+".toRegex(), "") ?: ""
    }
}
