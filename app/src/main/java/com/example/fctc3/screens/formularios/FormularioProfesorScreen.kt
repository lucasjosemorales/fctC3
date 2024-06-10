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
import com.example.fctc3.viewmodels.screens.ProfesorViewModel
import com.example.fct.models.Profesor
import com.example.fctc3.R
import com.example.fctc3.models.Ciclo
import com.example.fctc3.viewmodels.bbdd.CicloViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioProfesorScreen(navController: NavHostController, profesor: Profesor)
{
    val viewModel: ProfesorViewModel = viewModel()
    val cicloViewModel: CicloViewModel = viewModel()

    val name: String by viewModel.name.observeAsState(initial = profesor?.name ?: "")
    val email: String by viewModel.email.observeAsState(initial = profesor?.email ?: "")
    val phoneNumber: String by viewModel.phoneNumber.observeAsState(initial = profesor?.phoneNumber ?: "")
    val tutoria: String by viewModel.tutoria.observeAsState(initial = profesor?.tutoria ?: "")
    val admin: Boolean by viewModel.admin.observeAsState(initial = profesor?.admin ?: false)

    //BBDD
    val ciclos: List<Ciclo> by cicloViewModel.ciclos.observeAsState(initial = emptyList())
    val listaGrupos = ciclos

    /*val listaGrupos = listOf(
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
    )*/

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

        Text(
            text = "INFORMACIÓN DEL PROFESOR",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            fontWeight = FontWeight.Bold
        )

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

        Demo_ExposedDropdownMenuBox(listaGrupos, viewModel, profesor)

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.Center,  // Centra los elementos horizontalmente
            verticalAlignment = Alignment.CenterVertically  // Alinea los elementos verticalmente al centro
        )
        {

            Text(
                text = "¿admin?",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )

            //Spacer(modifier = Modifier.width(16.dp))

            //Admin
            Switch(
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color(0xFF364F59),
                    checkedTrackColor = Color(0xFF647C87),
                    uncheckedThumbColor = Color(0xFF364F59),
                    uncheckedTrackColor = Color.White,
                    checkedBorderColor = Color(0xFF364F59),
                    uncheckedBorderColor = Color(0xFF364F59)
                ),
                checked = admin,
                onCheckedChange = {viewModel.setAdmin(it)}
            )

        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val profesorAux = Profesor(email = email, name =  name, phoneNumber = phoneNumber,
                    tutoria = viewModel.tutoria.value!!, admin = admin)
                viewModel.añadirProfesor(profesorAux)
                viewModel.addProfesor(profesorAux)
                viewModel.añadirProfesores()

                //navController.navigate(AppScreens.ProfesoresScreen.route)
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
fun Demo_ExposedDropdownMenuBox(lista: List<Ciclo>, viewModel: ProfesorViewModel, profesor: Profesor)
{
    val expanded: Boolean by viewModel.expanded.observeAsState(initial = false)
    val selectedText: String by viewModel.selectedText.observeAsState(initial = profesor.tutoria)  //selectedText seria el equivalente al atributo profesor.tutoria

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
                onValueChange = {
                                    viewModel.setTutoria(it)
                                },
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
                        text = { Text(text = item.nombreLargo) },
                        onClick = {
                            //selectedText = item
                            viewModel.setSelectedText(item.nombreLargo)
                            //expanded = false
                            viewModel.setExpanded(false)
                            viewModel.setTutoria(item.nombreLargo)
                        }
                    )
                    HorizontalDivider()
                }
            }
        }
    }
}
