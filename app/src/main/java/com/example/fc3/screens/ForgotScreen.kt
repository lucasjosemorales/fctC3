package com.example.fc3.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fc3.navigation.AppScreens
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotScreen(navController: NavController){

    val context = LocalContext.current
    var email by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top= 30.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            item {
                //Titulo de Olvido contraseña
                Text(
                    text = "Olvidó su contraseña",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 24.sp, fontWeight = FontWeight.Bold,
                    color = Color.DarkGray, textAlign = TextAlign.Center
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
                        containerColor = Color.LightGray
                    ),
                    visualTransformation = VisualTransformation.None


                )

                //Espacio
                Spacer(modifier = Modifier.height(30.dp))

                //Botón enviar recuperar contraseña
                OutlinedButton(
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
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray
                    )
                ){
                    Text(text = "Recuperar contraseña")
                }
            }//item
        }//lazzycolumn
    }//Scafold

}//ForgotScreen
