package com.nirali.auth_design.presentation.screens

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nirali.auth_design.presentation.navigation.Routes
import com.nirali.auth_design.presentation.viewmodel.UserState
import com.nirali.expensetracker.core.presentation.home.screens.CustomTextField


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(onSubmit: (UserState) -> Unit) {


    Box {
        Surface(color = Color(0xFF42A974), modifier = Modifier.fillMaxSize()) {
            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.offset(y = -30.dp)

            ) {

            }


        }
        MainScreen(onSubmit)


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(onSubmit: (UserState) -> Unit) {
    var username by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var phone by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }
    var confirmPassword by remember {
        mutableStateOf("")
    }

    var isPasswordVisible by remember {
        mutableStateOf(false)
    }
    Surface(
        color = Color.White, modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .padding(bottom = 100.dp),
        shape = RoundedCornerShape(60.dp)
            .copy(
                topStart = RoundedCornerShape(0.dp).topStart,
                topEnd = RoundedCornerShape(0.dp).topEnd
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Registration Screen",
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.ExtraBold),
                modifier = Modifier.padding(10.dp)
            )
            CustomTextField(
                value = username,
                onValueChange = { username = it },
                label = "Username",
                placeholder = "Enter your username",
                modifier = Modifier.fillMaxWidth()

            )
            CustomTextField(
                value = email,
                onValueChange = { email = it },
                label = "Email",
                placeholder = "Enter your email",
                modifier = Modifier.fillMaxWidth()
            )

            CustomTextField(
                value = phone,
                onValueChange = { phone = it },
                label = "Enter your phone number",
                modifier = Modifier.fillMaxWidth(),
                keyboardType = KeyboardType.Number
            )

            OutlinedTextField(value = password,
                onValueChange = {
                    password = it
                },
                label = {
                    Text(
                        text = "Enter your password",
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                modifier = Modifier
                    .wrapContentSize()
                    .padding(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color(0xFF42A974),
                    focusedLabelColor = Color(0xFF42A974),
                    containerColor = Color.Transparent,
                    unfocusedLabelColor = Color.Gray,
                    cursorColor = Color(0xFF42A974),
                    errorLabelColor = Color.Red,
                    disabledLabelColor = Color.Gray,
                ),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Key, contentDescription = "Password")
                },
                trailingIcon = {
                    Icon(imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "Password",
                        modifier =
                        Modifier.clickable {
                            isPasswordVisible = !isPasswordVisible
                        })
                },
                shape = RoundedCornerShape(10.dp),
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                visualTransformation = if (isPasswordVisible) androidx.compose.ui.text.input.VisualTransformation.None else androidx.compose.ui.text.input.PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.run {
                    Default.copy(
                        imeAction = ImeAction.Next,

                        )
                }
            )

            OutlinedTextField(value = confirmPassword, onValueChange = {
                confirmPassword = it
            }, label = {
                Text(
                    text = "Re-Enter your password",
                    color = MaterialTheme.colorScheme.primary
                )
            },
                modifier = Modifier
                    .wrapContentSize()
                    .padding(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color(0xFF42A974),
                    focusedLabelColor = Color(0xFF42A974),
                    containerColor = Color.Transparent,
                    unfocusedLabelColor = Color.Gray,
                    cursorColor = Color(0xFF42A974),
                    errorLabelColor = Color.Red,
                    disabledLabelColor = Color.Gray,
                ),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Key, contentDescription = "Password")
                },
                trailingIcon = {
                    Icon(imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "Password",
                        modifier =
                        Modifier.clickable {
                            isPasswordVisible = !isPasswordVisible
                        })
                },
                shape = RoundedCornerShape(10.dp),
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                visualTransformation = if (isPasswordVisible) androidx.compose.ui.text.input.VisualTransformation.None else androidx.compose.ui.text.input.PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,

                    )
            )

            Button(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .wrapContentSize()
                    .align(Alignment.CenterHorizontally),
                onClick = { onSubmit(UserState( email, phone,username, password)) },
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF42A974),
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Register",
                    Modifier
                        .padding(horizontal = 90.dp)
                        .align(Alignment.CenterVertically)
                )

            }


        }
    }

}

@Preview
@Composable
fun RegistrationMain(onSubmit: () -> Unit) {
    RegistrationScreen({

    })

}
