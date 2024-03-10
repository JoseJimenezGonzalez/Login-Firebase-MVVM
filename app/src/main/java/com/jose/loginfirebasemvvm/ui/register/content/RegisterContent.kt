package com.jose.loginfirebasemvvm.ui.register.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jose.loginfirebasemvvm.ui.register.viewmodel.RegisterViewModel

@Composable
fun RegisterContent(
    registerViewModel: RegisterViewModel,
    paddingValues: PaddingValues,
    name: String,
    onNameChange: (String) -> Unit,
    nameError: String,
    surname: String,
    onSurnameChange: (String) -> Unit,
    surnameError: String,
    email: String,
    emailError: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    passwordError: String,
    repeatPassword: String,
    onRepeatPasswordChange: (String) -> Unit,
    repeatPasswordError: String,
){

    Column(
        modifier = Modifier
            .padding(paddingValues = paddingValues)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(fontSize = 26.sp, text = "Registro")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Completa este formulario")
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp),
            value = name,
            onValueChange = onNameChange,
            singleLine = true,
            isError = nameError.isNotEmpty(),
            supportingText = {
                if(nameError.isNotEmpty()){
                    Text(
                        text = nameError,
                        color = Color.Red
                    )
                }
            },
            label = {
                Text(text = "Nombre")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp),
            value = surname,
            onValueChange = onSurnameChange,
            singleLine = true,
            isError = surnameError.isNotEmpty(),
            supportingText = {
                if(surnameError.isNotEmpty()){
                    Text(
                        text = surnameError,
                        color = Color.Red
                    )
                }
            },
            label = {
                Text(text = "Apellidos")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp),
            value = email,
            onValueChange = onEmailChange,
            singleLine = true,
            isError = emailError.isNotEmpty(),
            supportingText = {
                if(emailError.isNotEmpty()){
                    Text(
                        text = emailError,
                        color = Color.Red
                    )
                }
            },
            label = {
                Text(text = "Correo")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp),
            value = password,
            onValueChange = onPasswordChange,
            singleLine = true,
            isError = passwordError.isNotEmpty(),
            supportingText = {
                if(passwordError.isNotEmpty()){
                    Text(
                        text = passwordError,
                        color = Color.Red
                    )
                }
            },
            label = {
                Text(text = "Contraseña")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp),
            value = repeatPassword,
            onValueChange = onRepeatPasswordChange,
            singleLine = true,
            isError = repeatPasswordError.isNotEmpty(),
            supportingText = {
                if(repeatPasswordError.isNotEmpty()){
                    Text(
                        text = repeatPasswordError,
                        color = Color.Red
                    )
                }
            },
            label = {
                Text(text = "Repetir contraseña")
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp),
            onClick = { registerViewModel.validateAll() },
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(text = "REGISTRARSE")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Ir a iniciar sesión")
    }
}