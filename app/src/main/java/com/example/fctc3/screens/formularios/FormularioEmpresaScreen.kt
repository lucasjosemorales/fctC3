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


    val keyboardController = LocalSoftwareKeyboardController.current
    val scrollState = rememberScrollState()
    val fpInformatica = listOf(
        "Sistemas Microinformáticos y Redes G.M.",
        "Administración de Sistemas Informáticos en Red G.S.",
        "Desarrollo de Aplicaciones Web G.S.",
        "Desarrollo de Aplicaciones Multiplataforma G.S."
    )
    val fpAdministracion = listOf(
        "Gestión Administrativa G.M.",
        "Administración y Finanzas G.S.",
        "Asistencia a la dirección Bilingüe G.S.",
    )
    val fpComercio = listOf(
        "Actividades Comerciales G.M",
        "Transporte y Logística G.S.",
        "Comercio Internacional Bilingüe G.S.",
        "Marketing y Publicidad G.S.",
    )

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

        /*
        Text(
            text = "FORMULARIO\nNUEVA EMPRESA",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold
        )*/

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
            item{
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        navController.navigate(route = AppScreens.EmpresasScreen.route)
                    },
                    modifier = Modifier.padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(4.dp),
                ) {
                    Text("Guardar")
                }
            }

        }
    }
}

/*
@Composable
fun ListaDeCursosFormulario(ciclo: String)
{
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Stepper(value = 0, onValueChange = { newValue ->  })
        Spacer(modifier = Modifier.width(16.dp))
        Text(ciclo, style = MaterialTheme.typography.bodyLarge)
    }
}*/

/*
@Composable
fun Stepper(
    value: Int,
    onValueChange: (Int) -> Unit,
    minValue: Int = Int.MIN_VALUE,
    maxValue: Int = Int.MAX_VALUE
) {
    Row{
        Button(
            onClick = { if (value > minValue) onValueChange(value - 1) },
            enabled = value > minValue,
            shape = RectangleShape,
            modifier = Modifier.graphicsLayer(scaleX = 0.8f, scaleY = 0.8f)
        ) {
            Text("-")
        }
        Spacer(Modifier.width(4.dp))
        Text(
            value.toString(),
            modifier = Modifier.alignByBaseline(),
            fontSize = 24.sp)
        Spacer(Modifier.width(4.dp))
        Button(
            onClick = { if (value < maxValue) onValueChange(value + 1) },
            enabled = value < maxValue,
            shape = RectangleShape,
            modifier = Modifier.graphicsLayer(scaleX = 0.8f, scaleY = 0.8f)
        ) {
            Text("+")
        }

        Spacer(Modifier.width(16.dp))

    }
}*/

@Composable
fun RowSwitch(pregunta: String, pos:Int, empresa: Empresa?, valor:Boolean)
{
    Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
    ){
        Row {

            val viewModel: EmpresaViewModel = viewModel()

            var checked by remember { mutableStateOf(valor) }
            //var practicasAnt by remember { mutableStateOf(false) }
            //var contratar by remember { mutableStateOf(false) }
            //var dual by remember { mutableStateOf(false) }

            //val checked: Boolean by viewModel.checked.observeAsState(initial = false)
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
                    //if (checked && pos == 1) practicasAnt = true else practicasAnt = false
                    // if (checked && pos == 2) contratar = true else contratar = false
                    // if (checked && pos == 3) dual = true else dual = false

                    //viewModel.setChecked(it)
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

/*
@Composable
fun ExpandableSection(ciclos:List<String>, fp: String) {
    val (expanded, setExpanded) = remember { mutableStateOf(false) }
    val image1: Painter = painterResource(id = R.drawable.expand_more)
    val image2: Painter = painterResource(id = R.drawable.expand_less)

    Column(
        modifier = Modifier.padding(horizontal = 16.dp))
    {
        Button(
            onClick = { setExpanded(!expanded) },
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier.fillMaxWidth()
        )
        {
            Text(text = fp)
            Icon(
                painter = if (expanded) image2 else image1,
                contentDescription = if (expanded) "Colapsar" else "Expandir"
            )
        }

        AnimatedVisibility(
            visible = expanded)
        {
            Column(
                modifier = Modifier.fillMaxHeight()
            )
            {
                for(ciclo in ciclos)
                    ListaDeCursosFormulario(ciclo)
            }

        }
    }
}*/




/*package com.example.fc3.screens.formularios

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fc3.navigation.AppScreens
import com.example.fctc3.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun FormularioEmpresaScreen(navController: NavController) {
    var nombreEmpresa by remember { mutableStateOf("") }
    var nif by remember { mutableStateOf("") }
    var localidad by remember { mutableStateOf("") }
    var personaContacto by remember { mutableStateOf("") }
    var telefonoContacto by remember { mutableStateOf("") }
    var correoElectronico by remember { mutableStateOf("") }
    var funciones by remember { mutableStateOf("") }
    var horarioLaboral by remember { mutableStateOf("") }
    var observaciones by remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current
    val scrollState = rememberScrollState()
    val fpInformatica = listOf(
        "Sistemas Microinformáticos y Redes G.M.",
        "Administración de Sistemas Informáticos en Red G.S.",
        "Desarrollo de Aplicaciones Web G.S.",
        "Desarrollo de Aplicaciones Multiplataforma G.S."
    )
    val fpAdministracion = listOf(
        "Gestión Administrativa G.M.",
        "Administración y Finanzas G.S.",
        "Asistencia a la dirección Bilingüe G.S.",
    )
    val fpComercio = listOf(
        "Actividades Comerciales G.M",
        "Transporte y Logística G.S.",
        "Comercio Internacional Bilingüe G.S.",
        "Marketing y Publicidad G.S.",
    )

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

        Text(
            text = "FORMULARIO\nNUEVA EMPRESA",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold
        )

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
                    onValueChange = { nif = it },
                    label = { Text("NIF") },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = nombreEmpresa,
                    onValueChange = { nombreEmpresa = it },
                    label = { Text("Nombre de la empresa") },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = localidad,
                    onValueChange = { localidad = it },
                    label = { Text("Localidad") },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = personaContacto,
                    onValueChange = { personaContacto = it },
                    label = { Text("Persona de contacto") },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = telefonoContacto,
                    onValueChange = { telefonoContacto = it },
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
                    onValueChange = { correoElectronico = it },
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
                RowSwitch("¿Ha tenido alumnos en prácticas previamente?", 1)
                RowSwitch("¿Tiene intención de contratar al finalizar la FCT?", 2)
                RowSwitch("¿Le interesaría conocer el programa dual?", 3)
            }
            item{

                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = observaciones ,
                    onValueChange = { observaciones = it },
                    label = { Text("Observaciones") },
                    maxLines = 4,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
                )
            }
            item{
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        navController.navigate(route = AppScreens.EmpresasScreen.route)
                    },
                    modifier = Modifier.padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(4.dp),
                ) {
                    Text("Guardar")
                }
            }

        }
    }
}

/*
@Composable
fun ListaDeCursosFormulario(ciclo: String)
{
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Stepper(value = 0, onValueChange = { newValue ->  })
        Spacer(modifier = Modifier.width(16.dp))
        Text(ciclo, style = MaterialTheme.typography.bodyLarge)
    }
}*/

/*
@Composable
fun Stepper(
    value: Int,
    onValueChange: (Int) -> Unit,
    minValue: Int = Int.MIN_VALUE,
    maxValue: Int = Int.MAX_VALUE
) {
    Row{
        Button(
            onClick = { if (value > minValue) onValueChange(value - 1) },
            enabled = value > minValue,
            shape = RectangleShape,
            modifier = Modifier.graphicsLayer(scaleX = 0.8f, scaleY = 0.8f)
        ) {
            Text("-")
        }
        Spacer(Modifier.width(4.dp))
        Text(
            value.toString(),
            modifier = Modifier.alignByBaseline(),
            fontSize = 24.sp)
        Spacer(Modifier.width(4.dp))
        Button(
            onClick = { if (value < maxValue) onValueChange(value + 1) },
            enabled = value < maxValue,
            shape = RectangleShape,
            modifier = Modifier.graphicsLayer(scaleX = 0.8f, scaleY = 0.8f)
        ) {
            Text("+")
        }

        Spacer(Modifier.width(16.dp))

    }
}*/

@Composable
fun RowSwitch(pregunta: String, pos:Int)
{
    Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
    ){
        Row {

            var checked by remember { mutableStateOf(false) }
            var practicasAnt by remember { mutableStateOf(false) }
            var contratar by remember { mutableStateOf(false) }
            var dual by remember { mutableStateOf(false) }

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
                    if (checked && pos == 1) practicasAnt = true else practicasAnt = false
                    if (checked && pos == 2) contratar = true else contratar = false
                    if (checked && pos == 3) dual = true else dual = false
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

/*
@Composable
fun ExpandableSection(ciclos:List<String>, fp: String) {
    val (expanded, setExpanded) = remember { mutableStateOf(false) }
    val image1: Painter = painterResource(id = R.drawable.expand_more)
    val image2: Painter = painterResource(id = R.drawable.expand_less)

    Column(
        modifier = Modifier.padding(horizontal = 16.dp))
    {
        Button(
            onClick = { setExpanded(!expanded) },
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier.fillMaxWidth()
        )
        {
            Text(text = fp)
            Icon(
                painter = if (expanded) image2 else image1,
                contentDescription = if (expanded) "Colapsar" else "Expandir"
            )
        }

        AnimatedVisibility(
            visible = expanded)
        {
            Column(
                modifier = Modifier.fillMaxHeight()
            )
            {
                for(ciclo in ciclos)
                    ListaDeCursosFormulario(ciclo)
            }

        }
    }
}*/*/


