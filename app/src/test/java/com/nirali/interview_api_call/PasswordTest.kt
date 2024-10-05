/**Created By Nirali Pandya
 * Date :2024-10-03
 * Time :1:01 p.m.
 * Project Name :Interview
 *
 */
package com.nirali.interview_api_call

import org.junit.Assert.assertEquals
import org.junit.Test

class PasswordTest {

    @Test
    fun validatePassword_blankInput_expectedRequired() {
        val sut = Utils()
        val result = sut.validatePassword(" ")
        assertEquals("Password cannot be blank", result)
    }

    @Test
    fun validatePassword_lessThan6Characters_expectedLengthError() {
        val sut = Utils()
        val result = sut.validatePassword("12345")
        assertEquals("Password length must  be grater than 6 characters long", result)

    }
    @Test
    fun validatePassword_Correct_Input()
    {
        val sut=Utils()
        val result=sut.validatePassword("Pass56789")
        assertEquals("Valid",result)

    }
}