package com.example.fctc3.screens.formularios

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.fctc3.models.Empresa
import com.example.fctc3.navigation.AppScreens
import com.example.fctc3.viewmodels.screens.EmpresaViewModel
import com.example.fctc3.R
import com.example.fctc3.models.Ciclo
import com.example.fctc3.viewmodels.bbdd.CicloViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun FormularioEmpresaScreen(navController: NavController, empresa: Empresa?) {

    val viewModel: EmpresaViewModel = viewModel()

    val nombreEmpresa: String by viewModel.nombreEmpresa.observeAsState(initial = empresa?.name ?: "")
    val nif: String by viewModel.nif.observeAsState(initial = empresa?.nif ?: "")
    val localidad: String by viewModel.localidad.observeAsState(initial = empresa?.localidad ?: "")
    val personaContacto: String by viewModel.personaContacto.observeAsState(initial = empresa?.personaContacto ?: "")
    val telefonoContacto: String by viewModel.telefonoContacto.observeAsState(initial = empresa?.tfnoContacto ?: "")
    val correoElectronico: String by viewModel.correoElectronico.observeAsState(initial = empresa?.email ?: "")
    val repetidor: Boolean by viewModel.practicasAnt.observeAsState(initial = empresa?.repetidor ?: false)
    val contratar: Boolean by viewModel.contratar.observeAsState(initial = empresa?.contratar ?: false)
    val dual: Boolean by viewModel.practicasAnt.observeAsState(initial = empresa?.dual ?: false)
    val observaciones: String by viewModel.observaciones.observeAsState(initial = empresa?.observaciones ?: "")

    /* FIREBASE */
    val loginEnable: Boolean by viewModel.loginEnable.observeAsState(initial = false)
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    val context = LocalContext.current
    val cicloViewModel: CicloViewModel = viewModel()


    val keyboardController = LocalSoftwareKeyboardController.current
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier.background(color = Color.White)
    )
    {

        Image(
            painter = painterResource(id = R.drawable.c3),
            contentDescription = "C3 logo",

            // Ajusta el tamaño del logo según sea necesario
            modifier = Modifier.fillMaxWidth().height(100.dp)
        )

        if (empresa == null)
        {
            Text(
                text = "FORMULARIO \nNUEVA EMPRESA",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                fontWeight = FontWeight.Bold
            )
        }
        else
        {
            Text(
                text = "DETALLE DE LA EMPRESA",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            item {
                OutlinedTextField(
                    value = nif,
                    onValueChange = { viewModel.setNif(it) },
                    label = { Text("NIF") },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = nombreEmpresa,
                    onValueChange = { viewModel.setNombreEmpresa(it) },
                    label = { Text("Nombre de la empresa") },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = localidad,
                    onValueChange = { viewModel.setLocalidad(it) },
                    label = { Text("Localidad") },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = personaContacto,
                    onValueChange = { viewModel.setPersonaContacto(it) },
                    label = { Text("Persona de contacto") },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = telefonoContacto,
                    onValueChange = { viewModel.setTelefonoContacto(it) },
                    label = { Text("Teléfono de contacto") },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = correoElectronico,
                    onValueChange = { viewModel.setCorreoElectronico(it) },
                    label = { Text("Correo electrónico") },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() })
                )
                Spacer(modifier = Modifier.height(16.dp))

            }
            item{
                RowSwitch("¿Ha tenido alumnos en prácticas previamente?", 1, empresa, repetidor)
                RowSwitch("¿Tiene intención de contratar al finalizar la FCT?", 2, empresa, contratar)
                RowSwitch("¿Le interesaría conocer el programa dual?", 3, empresa, dual)
            }
            item{

                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = observaciones ,
                    onValueChange = { viewModel.setObservaciones(it) },
                    label = { Text("Observaciones") },
                    maxLines = 4,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
                )
            }

            //Comprobamos todos los campos y habilitamos el boton
            viewModel.habilitarBoton(nif,nombreEmpresa,localidad,personaContacto,telefonoContacto,correoElectronico,observaciones)


            item{
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        //Añadir empresa
                        val aux = Empresa(
                            nif = nif,
                            name = nombreEmpresa,
                            localidad = localidad,
                            personaContacto = personaContacto,
                            tfnoContacto = telefonoContacto,
                            email = correoElectronico,
                            repetidor = repetidor,
                            contratar = contratar,
                            dual = dual,
                            observaciones = observaciones
                        )
                        viewModel.addEmpresa(aux)
                        viewModel.añadirEmpresa(aux)
                        viewModel.añadirEmpresas()

                        navController.navigate(route = AppScreens.EmpresasScreen.route)
                    },
                    modifier = Modifier.padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(4.dp),
                    enabled = loginEnable
                ) {
                    Text("Guardar")
                }
            }

        }
    }
}

@Composable
fun RowSwitch(pregunta: String, pos:Int, empresa: Empresa?, valor:Boolean)
{
    Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
    ){
        Row {

            val viewModel: EmpresaViewModel = viewModel()

            var checked by remember { mutableStateOf(valor) }

            val practicasAnt: Boolean by viewModel.practicasAnt.observeAsState(initial = empresa?.repetidor ?: false)
            val contratar: Boolean by viewModel.contratar.observeAsState(initial = empresa?.contratar ?: false)
            val dual: Boolean by viewModel.dual.observeAsState(initial = empresa?.dual ?: false)

            Switch(
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color(0xFF364F59),
                    checkedTrackColor = Color(0xFF647C87),
                    uncheckedThumbColor = Color(0xFF364F59),
                    uncheckedTrackColor = Color.White,
                    checkedBorderColor = Color(0xFF364F59),
                    uncheckedBorderColor = Color(0xFF364F59)
                ),
                checked = checked,
                onCheckedChange = {
                    checked = it

                    if (checked && pos == 1) viewModel.setPracticasAnt(true) else viewModel.setPracticasAnt(false)
                    if (checked && pos == 2) viewModel.setContratar(true) else viewModel.setContratar(false)
                    if (checked && pos == 3) viewModel.setDual(true) else viewModel.setDual(false)
                },
                modifier = Modifier.graphicsLayer(scaleX = 0.8f, scaleY = 0.8f)
            )
            Spacer(
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            Text(
                text=pregunta,
                fontSize = 18.sp
            )
        }

    }
}