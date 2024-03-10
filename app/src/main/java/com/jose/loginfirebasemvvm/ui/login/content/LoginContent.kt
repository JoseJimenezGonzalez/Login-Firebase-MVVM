package com.jose.loginfirebasemvvm.ui.login.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.jose.loginfirebasemvvm.R

@Composable
fun LoginContent(
    paddingValues: PaddingValues,
    email: String,
    password: String,
    emailError: String,
    passwordError: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    validateFields: () -> Unit,
    onRegisterClick: () -> Unit
){

    Column(
        modifier = Modifier
            .padding(paddingValues = paddingValues)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Image(
            modifier = Modifier
                .width(350.dp)
                .height(150.dp),
            painter = painterResource(id = R.drawable.logo_login_mtg),
            contentDescription = "Imagen logo magic"
        )

        Text(text = "Inicia sesión para comprar cartas")

        Spacer(modifier = Modifier.height(32.dp))

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

        Spacer(modifier = Modifier.height(32.dp))

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

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp),
            onClick = { validateFields() },
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(text = "Iniciar sesión")
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(text = "¿Aún no tienes cuenta?")

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Crear cuenta",
            fontStyle = FontStyle.Italic,
            modifier = Modifier.clickable {
                onRegisterClick()
            }
        )

    }

}