package com.example.fctc3.screens.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.fctc3.navigation.AppScreens
import com.example.fctc3.screens.principal.SetStatusBarColor
import com.example.fctc3.R
import com.example.fctc3.viewmodels.bbdd.AuthViewModel



@Composable
fun comprobarLoginCorrecto(correcto: Boolean, navController: NavController)
{
    if (correcto)
        navController.navigate(route = AppScreens.ScaffoldScreen.route)
    else
        Toast.makeText(LocalContext.current, "Usuario o contraseña incorrecta", Toast.LENGTH_LONG).show()
}

@ExperimentalMaterial3Api
@Composable
fun LoginScreen(navController: NavController)
{
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    var isSignedIn by remember { mutableStateOf(false) }
    var correcto by remember {(mutableStateOf(false)) }

    val authViewModel: AuthViewModel = viewModel()

    SetStatusBarColor()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    )
    {

        LazyColumn(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center
        )
        {
            item {
                Image(
                    painter = painterResource(id = R.drawable.c3),
                    contentDescription = "C3 logo",
                    // Ajusta el tamaño del logo según sea necesario
                    modifier = Modifier.fillMaxWidth().height(100.dp)
                )

                Text(
                    text = "Login",
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = email.value,
                    onValueChange = { email.value = it },
                    label = { Text("Email") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )

                OutlinedTextField(
                    value = password.value,
                    onValueChange = { password.value = it },
                    label = { Text("Password") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )

                Text(
                    text = "¿Olvidaste tu contraseña?",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .clickable { navController.navigate(route = AppScreens.ForgotScreen.route) },
                    style = TextStyle(
                        color = Color.Blue,
                        textDecoration = TextDecoration.Underline
                    ),
                    textAlign = TextAlign.Center
                )

                Button(
                    onClick = {
                                    authViewModel.signIn(email.value, password.value) { isSignedIn = it }
                                    correcto = isSignedIn
                              },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text("Login")
                }

                Button(
                    onClick = { navController.navigate(route = AppScreens.ScaffoldScreen.route)},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    contentPadding = PaddingValues()  // Elimina el padding predeterminado para alinear los elementos internos
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        // Asume que el logo de Google está en res/drawable/google_logo.xml
                        Image(
                            painter = painterResource(id = R.drawable.google),
                            contentDescription = "Google logo",
                            modifier = Modifier.size(24.dp)  // Ajusta el tamaño del logo según sea necesario
                        )
                        Spacer(modifier = Modifier.width(8.dp))  // Espacio entre el logo y el texto
                        Text("Login con Google")
                    }
                }

                comprobarLoginCorrecto(correcto, navController)

            }
        }


    }
}