package com.ngocvu.example.utils

import java.util.regex.Pattern


object RegexUtil {
    const val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})$"

    fun validateEmailAddress(email: String): Boolean {
        return EMAIL_REGEX.toRegex().matches(email)
    }

}