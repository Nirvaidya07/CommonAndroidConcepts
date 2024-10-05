/**Created By Nirali Pandya
 * Date :2024-10-03
 * Time :1:07 p.m.
 * Project Name :Interview
 *
 */
package com.nirali.interview_api_call

import org.junit.Assert
import org.junit.Test

class StringTest {

    @Test
    fun emptyString() {
        val sut = Utils()
        val result=sut.reverseString("")
        Assert.assertEquals("",result)
    }

    @Test
    fun singleInputString() {
        val sut = Utils()
        val result=sut.reverseString("A")
        Assert.assertEquals("A",result)
    }

    @Test
    fun validInputString() {
        val sut = Utils()
        val result=sut.reverseString("ABAcc")
        Assert.assertEquals("ccABA",result)
    }
}