/**Created By Nirali Pandya
 * Date :2024-10-03
 * Time :12:58 p.m.
 * Project Name :Interview
 *
 */
package com.nirali.restApi

class Utils {

    fun isPalindrome(input: String): Boolean {
        val reversed = input.reversed()
        return input == reversed
    }

    fun validatePassword(input: String)= when
    {
        input.isBlank() -> "Password cannot be blank"
        input.length < 6 -> "Password length must  be grater than 6 characters long"
        input.length > 15 -> "Password length must be less than 15 characters long"
        else -> "Valid"
    }

    fun reverseString(input: String): String {
        return input.reversed()
    }
}