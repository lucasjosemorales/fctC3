package com.example.fc3.screens.formularios

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.fc3.navigation.AppScreens
import com.example.fc3.viewmodels.AlumnoViewModel
import com.example.fc3.viewmodels.AlumnoViewModelFactory
import com.example.fct.models.Alumno
import com.example.fctc3.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioAlumnoScreen(navController: NavHostController, alumno: Alumno?)
{
    var (name, setName) = remember { mutableStateOf("") }
    val (email, setEmail) = remember { mutableStateOf("") }
    val (phoneNumber, setPhoneNumber) = remember { mutableStateOf("") }
    val (grupo, setGrupo) = remember { mutableStateOf("") }

    Column (
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.c3),
            contentDescription = "C3 logo",
            // Ajusta el tamaño del logo según sea necesario
            modifier = Modifier.fillMaxWidth().height(100.dp)
        )

        Text(
            text = "INGRESA LA INFORMACIÓN DEL ALUMNO",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            fontWeight = FontWeight.Bold
        )

        if (alumno != null)
        {
            OutlinedTextField(
                value = name,
                onValueChange = setName,
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            OutlinedTextField(
                value = alumno.email,
                onValueChange = setEmail,
                label = { Text("Email") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            OutlinedTextField(
                value = alumno.phoneNumber,
                onValueChange = setPhoneNumber,
                label = { Text("Número de Teléfono") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            OutlinedTextField(
                value = alumno.grupo,
                onValueChange = setGrupo,
                label = { Text("Grupo") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )
        }
        else
        {
            OutlinedTextField(
                value = name,
                onValueChange = setName,
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            OutlinedTextField(
                value = email,
                onValueChange = setEmail,
                label = { Text("Email") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            OutlinedTextField(
                value = phoneNumber,
                onValueChange = setPhoneNumber,
                label = { Text("Número de Teléfono") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            OutlinedTextField(
                value = grupo,
                onValueChange = setGrupo,
                label = { Text("Grupo") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        //¿Hacer invisible si el texto no se ha modificado?

        Button(
            onClick = {
                navController.navigate(AppScreens.AlumnosScreen.route)
            },
            modifier = Modifier.padding(horizontal = 16.dp),
            shape = RoundedCornerShape(4.dp),
        ) {
            Text("Guardar")
        }
    }
}


