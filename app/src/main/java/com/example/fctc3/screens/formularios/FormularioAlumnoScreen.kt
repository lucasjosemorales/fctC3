package com.example.fctc3.screens.formularios

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.fctc3.navigation.AppScreens
import com.example.fctc3.viewmodels.screens.AlumnoViewModel
import com.example.fct.models.Alumno
import com.example.fctc3.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioAlumnoScreen(navController: NavHostController, alumno: Alumno?)
{
    val viewModel: AlumnoViewModel = viewModel()

    val name: String by viewModel.name.observeAsState(initial = alumno?.name ?: "")
    val phoneNumber: String by viewModel.phoneNumber.observeAsState(initial = alumno?.phoneNumber ?: "")
    val email: String by viewModel.email.observeAsState(initial = alumno?.email ?: "")
    val grupo: String by viewModel.grupo.observeAsState(initial = alumno?.grupo ?: "")

    Column (
        modifier = Modifier.fillMaxWidth().background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.c3),
            contentDescription = "C3 logo",
            // Ajusta el tamaño del logo según sea necesario
            modifier = Modifier.fillMaxWidth().height(100.dp)
        )

        if (alumno == null)
        {
            Text(
                text = "INGRESA LA INFORMACIÓN DEL ALUMNO",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                fontWeight = FontWeight.Bold
            )
        }
        else
        {
            Text(
                text = "DETALLE DEL ALUMNO",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            //value = alumno.name,
            value = name,
            onValueChange = { viewModel.setName(it) },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)/*,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                                        containerColor = Color(0xFF789BA3),
                                        textColor = Color.White,
                                        focusedLabelColor = Color(0xFF364F59),
                                        unfocusedLabelColor = Color(0xFF789BA3))*/
        )

        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(
            //value = alumno.email,
            value = email,
            onValueChange = {viewModel.setEmail(it)},
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(
            //value = alumno.phoneNumber,
            value = phoneNumber,
            onValueChange = {viewModel.setPhoneNumber(it)},
            label = { Text("Número de Teléfono") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(
            //value = alumno.grupo,
            value = grupo,
            onValueChange = {viewModel.setGrupo(it)},
            label = { Text("Grupo") },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        )

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


