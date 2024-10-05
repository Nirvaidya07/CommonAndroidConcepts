/**Created By Nirali Pandya
 * Date :2024-10-03
 * Time :1:12 p.m.
 * Project Name :Interview
 *
 */
package com.nirali.restApi
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import com.nirali.MainActivity
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val composeRule= createAndroidComposeRule<MainActivity>()

    @Test
    fun button_Click_Empty() {
        composeRule.onNodeWithTag("text").assertIsDisplayed().assertHeightIsEqualTo(100.dp)
    }
}