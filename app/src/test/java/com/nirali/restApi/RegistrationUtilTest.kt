/**Created By Nirali Pandya
 * Date :2024-10-06
 * Time :9:32 p.m.
 * Project Name :CommonConcepts
 *
 */
package com.nirali.restApi

import com.nirali.auth_design.presentation.screens.RegistrationUtil
import org.junit.Assert
import org.junit.Test

class RegistrationUtilTest {

    @Test
    fun `empty username return false`() {
        val result = RegistrationUtil.validRegistrationInput("", "123", "123")
        Assert.assertEquals(false, result)

    }

    @Test
    fun `valid username and correctly repeated password returns true`() {
        val result = RegistrationUtil.validRegistrationInput("Neha", "123", "123")
        Assert.assertEquals(true, result)
    }

    @Test
    fun `password is not matched with confirm password returns false`()
    {
        val result = RegistrationUtil.validRegistrationInput("Nehs","123","223")
        Assert.assertEquals(false,result)

    }
    @Test
    fun `password is less than 2 digits returns false`()
    {
        val result = RegistrationUtil.validRegistrationInput("Neha","1","1")
        Assert.assertEquals(false,result)
    }

}