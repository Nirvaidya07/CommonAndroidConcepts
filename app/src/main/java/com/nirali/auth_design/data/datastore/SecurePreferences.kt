/**Created By Nirali Pandya
 * Date :2024-10-05
 * Time :9:53 p.m.
 * Project Name :CommonConcepts
 *
 */
package com.nirali.auth_design.data.datastore

import android.content.Context
import android.provider.Settings.Global.putString
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class SecurePreferences(private val context: Context) {
    private val masterKeyAlias = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val sharedPreferences = EncryptedSharedPreferences.create(
        context, "secure_shared_pref",
        masterKeyAlias,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun savePassword(password: String) {
        sharedPreferences.edit().apply {
            putString("password", password)
            apply()
        }

    }
    fun getPassword(): String? {
        return sharedPreferences.getString("password", null)
    }

}