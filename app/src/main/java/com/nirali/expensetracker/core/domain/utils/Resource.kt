/**Created By Nirali Pandya
 * Date :2024-10-04
 * Time :12:23 p.m.
 * Project Name :Interview
 *
 */
package com.nirali.expensetracker.core.domain.utils

sealed class Resource<T>(
    val data: T? = null
) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(data: T? = null) : Resource<T>(data)
    class Loading<T> : Resource<T>()

}