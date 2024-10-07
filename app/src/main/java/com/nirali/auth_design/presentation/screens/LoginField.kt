package com.nirali.auth_design.presentation.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.Alignment
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.nirali.auth_design.data.datastore.SecurePreferences
import com.nirali.auth_design.data.datastore.UserDataStore
import com.nirali.auth_design.presentation.lengthOfLongestSubstring
import com.nirali.auth_design.presentation.navigation.Routes

@Preview
@Composable
fun LoginField(onLogin: () -> Unit) {
    var inputText by remember { mutableStateOf("") }
    var inputPassword by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    val userDataStore = UserDataStore(context)
    val securePreferences = SecurePreferences(context)


    LaunchedEffect(Unit) {
        userDataStore.username.collect { email ->
            username = email ?: ""
        }
    }
    password = securePreferences.getPassword() ?: ""

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val sampleString = "abcabcbb"
            val lengthOfLongestSubstring = sampleString.lengthOfLongestSubstring()
            println(lengthOfLongestSubstring)
            val list = listOf<Int>(1, 2, 3, 4, 5,6,7,8,9,10)
            var evenList = list.filter { it % 2 == 0 }
            println ("Even numbers are $evenList")
            var double = evenList.map { e -> e * 2 }
            println ("Sum of even numbers doubled is $double")
            var sum = double.reduce { acc, i ->
                println ("acc $acc i $i")
                acc + i

            }

            println ("Sum of even numbers doubled is $sum")

            LoginInputTextField(
                inputText,
                onValueChange = {
                    inputText = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                label = "Login",
                placeholder = "Enter your username"
            )
            Spacer(modifier = Modifier.padding(10.dp))

            PasswordInputTextField(
                inputPassword,
                onValueChange = {
                    inputPassword = it
                },
                submit = { Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show() },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                label = "Password",
                placeholder = "Enter your password"
            )

            Button(
                onClick = {
                    Log.d(
                        "TAG",
                        "LoginField: ${username} ${password}  req ${inputText} ${inputPassword}"
                    )
                    if (inputText == username && inputPassword == password) {
                        Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                        onLogin()
                    } else {
                        Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show()
                    }


                }, modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {

                Text(text = "Submit")
            }

        }
    }

}

@Composable
fun LoginInputTextField(
    value: String, onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String, placeholder: String
) {
    val focusManager = LocalFocusManager.current
    val leadingIcon = @Composable {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "Email Icon",
            tint = MaterialTheme.colorScheme.primary
        )

    }
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        leadingIcon = leadingIcon,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(focusDirection = androidx.compose.ui.focus.FocusDirection.Down) }
        ),
        textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurface),
        label = {
            Text(text = label)
        },
        placeholder = {
            Text(text = placeholder)
        },
        visualTransformation = VisualTransformation.None,


        )
}

@Composable
fun PasswordInputTextField(
    value: String,
    onValueChange: (String) -> Unit,
    submit: () -> Unit,
    modifier: Modifier = Modifier,
    label: String,
    placeholder: String
) {

    var isPasswordVisible by remember { mutableStateOf(false) }
    val leadingIcon = @Composable {
        Icon(
            imageVector = Icons.Default.Key,
            contentDescription = "Password Icon",
            tint = MaterialTheme.colorScheme.primary
        )

    }
    val trailingIcon = @Composable {
        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
            Icon(
                if (isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = { submit() }
        ),
        textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurface),
        label = {
            Text(text = label)
        },
        placeholder = {
            Text(text = placeholder)
        },
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),

        )
}

