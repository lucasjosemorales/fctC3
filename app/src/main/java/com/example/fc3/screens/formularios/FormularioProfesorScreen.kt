package com.example.fc3.screens.formularios

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
import com.example.fc3.navigation.AppScreens
import com.example.fc3.viewmodels.ProfesorViewModel
import com.example.fct.models.Profesor
import com.example.fctc3.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioProfesorScreen(navController: NavHostController, profesor: Profesor?) {
    val viewModel: ProfesorViewModel = viewModel()

    //val (name, setName) = remember { mutableStateOf("") }
    //val (email, setEmail) = remember { mutableStateOf("") }
    //val (phoneNumber, setPhoneNumber) = remember { mutableStateOf("") }
    //val (tutoria, setTutoria) = remember { mutableStateOf("") }
    //val familia by remember { mutableStateOf("") }

    val name: String by viewModel.name.observeAsState(initial = profesor?.name ?: "")
    val email: String by viewModel.email.observeAsState(initial = profesor?.phoneNumber ?: "")
    val phoneNumber: String by viewModel.phoneNumber.observeAsState(initial = profesor?.email ?: "")
    //val tutoria: String by viewModel.tutoria.observeAsState(initial = profesor?.tutoria ?: "")


    /** ELIMINAR HARDCODED TEXT !!*/

    /* ASOCIAR DESCRIPCIÓN CON SIGLAS - METER GRUPOS

        "Sistemas Microinformáticos y Redes G.M." --> SMR
        "Administración de Sistemas Informáticos en Red G.S." --> ASIR
        "Desarrollo de Aplicaciones Web G.S." --> DAW
        "Desarrollo de Aplicaciones Multiplataforma G.S. --> DAM
        "Gestión Administrativa G.M." -->
        "Administración y Finanzas G.S." -->
        "Asistencia a la dirección Bilingüe G.S." -->
        "Actividades Comerciales G.M" -->
        "Transporte y Logística G.S." -->
        "Comercio Internacional Bilingüe G.S." -->
        "Marketing y Publicidad G.S." -->


     */

    val listaGrupos = listOf(
        "Sistemas Microinformáticos y Redes G.M.",
        "Administración de Sistemas Informáticos en Red G.S.",
        "Desarrollo de Aplicaciones Web G.S.",
        "Desarrollo de Aplicaciones Multiplataforma G.S.",
        "Gestión Administrativa G.M.",
        "Administración y Finanzas G.S.",
        "Asistencia a la dirección Bilingüe G.S.",
        "Actividades Comerciales G.M",
        "Transporte y Logística G.S.",
        "Comercio Internacional Bilingüe G.S.",
        "Marketing y Publicidad G.S."
    )

    Column(
        modifier = Modifier.fillMaxWidth().background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.c3),
            contentDescription = "C3 logo",
            // Ajusta el tamaño del logo según sea necesario
            modifier = Modifier.fillMaxWidth().height(100.dp)
        )

        if (profesor == null)
        {
            Text(
                text = "INGRESA LA INFORMACIÓN DEL PROFESOR",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                fontWeight = FontWeight.Bold
            )
        }
        else
        {
            Text(
                text = "DETALLE DEL PROFESOR",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { viewModel.setName(it) },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { viewModel.setEmail(it) },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { viewModel.setPhoneNumber(it) },
            label = { Text("Número de Teléfono") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        //Demo_ExposedDropdownMenuBox(listaGrupos)
        Demo_ExposedDropdownMenuBox(listaGrupos, viewModel, profesor?.tutoria)

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate(AppScreens.ProfesoresScreen.route)
            },
            modifier = Modifier.padding(horizontal = 16.dp),
            shape = RoundedCornerShape(4.dp),
        ) {
            Text("Guardar")
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Demo_ExposedDropdownMenuBox(lista: List<String>, viewModel: ProfesorViewModel, tutoria: String?)
{
    //var expanded by remember { mutableStateOf(false) }
    //var selectedText by remember { mutableStateOf(lista[0]) }
    val expanded: Boolean by viewModel.expanded.observeAsState(initial = false)
    val selectedText: String by viewModel.selectedText.observeAsState(initial = tutoria ?: lista[0])  //selectedText seria el equivalente al atributo profesor.tutoria

    Box(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
    )
    {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                //expanded = !expanded
                viewModel.setExpanded(!expanded)
            }
        ) {
            OutlinedTextField(
                value = selectedText,
                onValueChange = { viewModel.setTutoria(it) },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor().fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                //onDismissRequest = { expanded = false },
                onDismissRequest = { viewModel.setExpanded(false) },
            ) {
                lista.forEach { item ->
                    DropdownMenuItem(
                        modifier = Modifier.background(color = Color.White),
                        text = { Text(text = item) },
                        onClick = {
                            //selectedText = item
                            viewModel.setSelectedText(item)
                            //expanded = false
                            viewModel.setExpanded(false)
                        }
                    )
                    Divider()
                }
            }
        }
    }
}
