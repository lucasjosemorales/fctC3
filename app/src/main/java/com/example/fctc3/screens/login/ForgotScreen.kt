package com.example.fctc3.screens.login

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fctc3.navigation.AppScreens
import com.example.fctc3.screens.principal.SetStatusBarColor
import com.example.fctc3.screens.principal.appBarColors
import com.example.fctc3.R


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotScreen(navController: NavController)
{
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    SetStatusBarColor()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopAppBar(
            title = { },
            actions = {
                Row(
                    modifier = Modifier.fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                }
            },
            modifier = Modifier.height(56.dp),
            navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
            },
            colors = appBarColors()
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top= 30.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            item {
                Image(
                    painter = painterResource(id = R.drawable.c3),
                    contentDescription = "C3 logo",
                    // Ajusta el tamaño del logo según sea necesario
                    modifier = Modifier.fillMaxWidth().height(100.dp)
                )

                Text(
                    text = "ESCRIBA SU CORREO ELECTRÓNICO",
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    fontWeight = FontWeight.Bold
                )

                //Espacio
                Spacer(modifier = Modifier.height(50.dp))

                //Introduce el email
                OutlinedTextField(
                    label = {Text(text = "Email")},
                    value = email,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    onValueChange = {email = it},
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Email") },
                    singleLine = true, maxLines = 1,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White
                    ),
                    visualTransformation = VisualTransformation.None


                )

                //Espacio
                Spacer(modifier = Modifier.height(30.dp))

                Button(
                    onClick = {
                        navController.navigate(route = AppScreens.ScaffoldScreen.route)
                    },
                    modifier = Modifier.padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(4.dp),
                ) {
                    Text("Recuperar contraseña")
                }

                //Botón enviar recuperar contraseña
                /*OutlinedButton(
                    onClick = {
                        navController.navigate(route = AppScreens.ScaffoldScreen.route)
                        /*scope.launch {
                            when(val res = auth.resetPassword(email)) {
                                is AuthRes.Success -> {
                                    Toast.makeText(context, "Correo enviado", Toast.LENGTH_SHORT).show()
                                    navigation.navigate(Routes.LOGIN.route)
                                }
                                is AuthRes.Error -> {
                                    Toast.makeText(context, "Error al enviar el correo", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }*/
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ){
                    Text(text = "Recuperar contraseña")
                }*/
            }//item
        }//lazzycolumn
    }//Scafold

}//ForgotScreen
