package com.example.fctc3.screens.login

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.fctc3.bbdd.Firebase
import com.example.fc3.viewmodels.LoginViewModel
import com.example.fctc3.R
import com.example.fctc3.screens.principal.SetStatusBarColor
import kotlinx.coroutines.launch
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.constraintlayout.compose.Visibility
import androidx.navigation.NavHostController
import com.example.fctc3.bbdd.AuthRes
import com.example.fctc3.navigation.AppScreens


@ExperimentalMaterial3Api
@Composable
fun LoginScreen(navController: NavHostController)
{
    val viewModel: LoginViewModel = viewModel()

    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val passwordVisibility : Boolean by viewModel.passwordVisibility.observeAsState(initial = false)
    val loginEnable: Boolean by viewModel.loginEnable.observeAsState(initial = false)
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)

    val context = LocalContext.current

    val auth= Firebase(context)
    val scope = rememberCoroutineScope()

    SetStatusBarColor()

    if(isLoading){
        Box(Modifier.fillMaxSize()){
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }else{
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
                        value = email,
                        onValueChange = { viewModel.setEmail(it) },
                        label = { Text("Email") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    )

                    OutlinedTextField(
                        value = password,
                        onValueChange = { viewModel.setPassword(it) },
                        label = { Text("Password") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        trailingIcon = {     //Icono de mostrar y ocultar contraseña
                            IconButton(
                                onClick = {
                                    viewModel.setPasswordVisibility(passwordVisibility)
                                }
                            ) {
                                Icon(
                                    imageVector = if (passwordVisibility) {
                                        Icons.Filled.Visibility
                                    } else {
                                        Icons.Filled.VisibilityOff
                                    },
                                    contentDescription = "Mostrar/ocultar contraseña"
                                )
                            }
                        },
                        visualTransformation = if (passwordVisibility) {      //Muestra/oculta contraseña
                            VisualTransformation.None
                        } else {
                            PasswordVisualTransformation()
                        }
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

                    //Comprobamos todos los campos y habilitamos el boton
                    viewModel.habilitarBoton(email, password)

                    Button(
                        onClick = {
                            scope.launch {
                                emailPassSignIn(email, password, auth, context, navController)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        enabled = loginEnable
                    ) {
                        Text("Login")
                    }

                    Button(
                        onClick = { navController.navigate(AppScreens.ScaffoldScreen.route) },
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
                }
            }
        }
    }


}

//INICIAR SESION CON EMAIL-CONTRASEÑA
private suspend fun emailPassSignIn(
    email: String,
    password: String,
    auth: Firebase,
    context: Context,
    navigation: NavController,
) {
    if (email.isNotEmpty() && password.isNotEmpty()) {
        when (val result = auth.signInWithEmailAndPassword(email, password)) {
            is AuthRes.Success -> {

                navigation.navigate(AppScreens.ScaffoldScreen.route)

                /*navigation.navigate(AppScreens.ScaffoldScreen.route) {
                    popUpTo(AppScreens.LoginScreen.route) {
                       /* Al establecer inclusive en true, indica que la pantalla de Login debe ser eliminada de la pila de navegación.
                       Esto significa que, una vez se realice la navegación, la pantalla de Login no estará disponible en la pila de navegación,
                       es decir, si el usuario presiona el botón de regreso, no regresará a la pantalla de Login.*/
                        inclusive = true
                    }
                }*/
            }

            is AuthRes.Error -> {
                Toast.makeText(context, "Error en la autenticación: Usuario y/o Contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    } else {
        Toast.makeText(context, "Existen campos vacios", Toast.LENGTH_SHORT).show()
    }
}

