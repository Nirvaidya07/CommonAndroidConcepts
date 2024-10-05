/**Created By Nirali Pandya
 * Date :2024-10-04
 * Time :9:47 p.m.
 * Project Name :Interview
 *
 */
package com.nirali.sample

class Operations {

    fun scopedFunction() {
        val student = Student().apply {
            name = "John"
            mark = 90
            isPassed = mark >= 40

        }
        student.let {
            if (it.isPassed)
                println("Student is passed: $this")
            else
                println("Student is failed: $this")
        }
        student.run {
            if (mark>90)
                println("Student is top performer: $this")

        }
        val result = run {
            val a = 10
            val b = 20
            a + b

        }
        val marks = 85
        with(student)
        {
           println(
               "Student name is: $name and mark is: $mark and isPassed is: $isPassed"
           )
        }
        student.also {
            println("Student name is: ${it.name}")
        }
        with(student){
            println("Student name is: $name and mark is: $mark and isPassed is: $isPassed")
        }



    }
}