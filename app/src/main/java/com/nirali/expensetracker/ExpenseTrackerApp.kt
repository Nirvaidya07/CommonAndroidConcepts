/**Created By Nirali Pandya
 * Date :2024-10-04
 * Time :11:05 a.m.
 * Project Name :Interview
 *
 */
package com.nirali.expensetracker

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ExpenseTrackerApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }

}