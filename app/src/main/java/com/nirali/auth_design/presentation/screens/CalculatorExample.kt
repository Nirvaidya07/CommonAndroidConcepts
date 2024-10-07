package com.nirali.auth_design.presentation.screens

class CalculatorExample (val operators:Operators) {
    fun addTwoNumbers(a: Int, b: Int): Int = operators.addTwoInt(a,b)
}
object Operators {
    fun addTwoInt(m: Int, n: Int): Int = m + n
}
