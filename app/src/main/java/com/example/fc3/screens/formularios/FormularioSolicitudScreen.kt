package com.example.fc3.screens.formularios

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fc3.navigation.AppScreens
import com.example.fctc3.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun FormularioSolicitudScreen(navController: NavController) {
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

    Column()
    {

        Image(
            painter = painterResource(id = R.drawable.c3),
            contentDescription = "C3 logo",
            // Ajusta el tamaño del logo según sea necesario
            modifier = Modifier.fillMaxWidth().height(100.dp)
        )

        Text(
            text = "FORMULARIO NUEVA SOLICITUD FCT",
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
                //Debe autorellenarse con la información de la empresa
                TextField(
                    value = nif,
                    onValueChange = { nif = it },
                    label = { Text("NIF") },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
                )
                Spacer(modifier = Modifier.height(8.dp))

                //Debe autorellenarse con la información de la empresa
                TextField(
                    value = nombreEmpresa,
                    onValueChange = { nombreEmpresa = it },
                    label = { Text("Nombre de la empresa") },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
                )
                Spacer(modifier = Modifier.height(16.dp))

            }
            item{
                Text(
                    "Selecciona las necesidades de alumnado",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(Modifier.height(16.dp))
            }
            item {
                ExpandableSection(fpInformatica, "Informática")
                ExpandableSection(fpAdministracion, "Administración")
                ExpandableSection(fpComercio, "Comercio")
            }
            item{
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = funciones ,
                    onValueChange = { funciones = it },
                    label = { Text("Funciones a realizar") },
                    maxLines = 4,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
                )

                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = horarioLaboral ,
                    onValueChange = { horarioLaboral = it },
                    label = { Text("Horario Laboral") },
                    maxLines = 4,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
                )

                Spacer(modifier = Modifier.height(16.dp))

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
            item{
                Spacer(modifier =  Modifier.height(72.dp))
            }

        }
    }
}


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
}

@Composable
fun Stepper(
    value: Int,
    onValueChange: (Int) -> Unit,
    minValue: Int = Int.MIN_VALUE,
    maxValue: Int = Int.MAX_VALUE
) {

    var counter by rememberSaveable{ mutableIntStateOf(0) }

    Row{

        Button(
            onClick = { if (counter > 0) counter -=1 },
            enabled = value > minValue,
            shape = RectangleShape,
            modifier = Modifier.graphicsLayer(scaleX = 0.7f, scaleY = 0.7f)
        ) {
            Text("-")
        }
        Spacer(Modifier.width(4.dp))
        Text(
            counter.toString(),
            modifier = Modifier.alignByBaseline(),
            fontSize = 24.sp)
        Spacer(Modifier.width(4.dp))
        Button(
            onClick = { counter += 1 },
            enabled = value < maxValue,
            shape = RectangleShape,
            modifier = Modifier.graphicsLayer(scaleX = 0.7f, scaleY = 0.7f)
        ) {
            Text("+")
        }

        Spacer(Modifier.width(16.dp))

    }
}

/*
@Composable
fun RowSwitch(pregunta: String)
{
    Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
    ){
        Row {
            Switch(
                checked = false,
                onCheckedChange = {},
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
}*/

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
}


