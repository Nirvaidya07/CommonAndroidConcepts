/**Created By Nirali Pandya
 * Date :2024-10-05
 * Time :6:32 p.m.
 * Project Name :CommonConcepts
 *
 */
package com.nirali.auth_design.presentation.viewmodel

import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Phone
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nirali.auth_design.data.datastore.SecurePreferences
import com.nirali.auth_design.data.datastore.UserDataStore
import com.nirali.auth_design.data.room.UserDao
import com.nirali.auth_design.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


data class UserState(
    val email: String = "",
    val phone: String = "",
    val username: String = "",
    val password: String = ""
)

@HiltViewModel
class AuthViewModel @Inject constructor(
    val userDataStore: UserDataStore,
    val securePreferences: SecurePreferences
) : ViewModel() {



    fun login(
        email: String,
        phone: String,
        username: String,
        password: String,
        onSuccessfulLogin: () -> Unit
    ) {
        viewModelScope.launch {
            userDataStore.saveData(email, phone, username)
            securePreferences.savePassword(password)
            onSuccessfulLogin()
        }
    }




}


