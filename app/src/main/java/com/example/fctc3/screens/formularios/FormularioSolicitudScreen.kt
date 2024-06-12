package com.example.fctc3.screens.formularios

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.fctc3.models.Solicitud
import com.example.fctc3.navigation.AppScreens
import com.example.fctc3.viewmodels.screens.SolicitudViewModel
import com.example.fctc3.R
import com.example.fctc3.models.Ciclo
import com.example.fctc3.viewmodels.bbdd.CicloViewModel

@Composable
fun FormularioSolicitudScreen(navController: NavController, solicitud: Solicitud?)
{
    val viewModel: SolicitudViewModel = viewModel()
    val cicloViewModel: CicloViewModel = viewModel()

    val nombreEmpresa by viewModel.nombreEmpresa.observeAsState(initial = solicitud?.empresa ?: "")
    val nif by viewModel.nif.observeAsState(initial = solicitud?.nif ?: "")
    //¿Rellenar automáticamente?
    //val convocatoria: String = "Marzo",
    //¿Rellenar automáticamente?
    //val curso: String = "2023-2024",
    val funciones by viewModel.funciones.observeAsState(initial = solicitud?.funciones ?: "")
    val horario by viewModel.horarioLaboral.observeAsState(initial = solicitud?.horario ?: "")
    val plazas by viewModel.plazas.observeAsState(initial = solicitud?.plazas ?: mutableMapOf())
    val estado by viewModel.estado.observeAsState(initial = solicitud?.estado ?: "")
    //Email
    val coordinador by viewModel.coordinador.observeAsState(initial = solicitud?.coordinador ?: "")
    //Email
    val alumnos by viewModel.alumnos.observeAsState(initial = solicitud?.alumnos ?: "")


    val keyboardController = LocalSoftwareKeyboardController.current
    val scrollState = rememberScrollState()
    /*val fpInformatica = listOf(
        "Sistemas Microinformáticos y Redes G.M.",
        "Administración de Sistemas Informáticos en Red G.S.",
        "Desarrollo de Aplicaciones Web G.S.",
        "Desarrollo de Aplicaciones Multiplataforma G.S."
    )*/
    val fpCiclos: List<Ciclo> by cicloViewModel.ciclos.observeAsState(initial = emptyList())
    val fpInformatica: MutableList<String> = mutableListOf()

    for(ciclo in fpCiclos)
    {
        fpInformatica.add(ciclo.nombreLargo)
    }

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

    /* FIREBASE */
    val loginEnable: Boolean by viewModel.loginEnable.observeAsState(initial = false)
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    val context = LocalContext.current

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
                OutlinedTextField(
                    value = nif,
                    onValueChange = { viewModel.setNif(it) },
                    label = { Text("NIF") },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
                )
                Spacer(modifier = Modifier.height(8.dp))

                //Debe autorellenarse con la información de la empresa
                OutlinedTextField(
                    value = nombreEmpresa,
                    onValueChange = { viewModel.setNombreEmpresa(it) },
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
                ExpandableSection(fpInformatica, "Informática", viewModel, plazas)
                //ExpandableSection(fpAdministracion, "Administración", viewModel, plazas)
                //ExpandableSection(fpComercio, "Comercio", viewModel, plazas)
            }
            item{
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = funciones ,
                    onValueChange = { viewModel.setFunciones(it) },
                    label = { Text("Funciones a realizar") },
                    maxLines = 4,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
                )

                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = horario ,
                    onValueChange = { viewModel.setHorarioLaboral(it)},
                    label = { Text("Horario Laboral") },
                    maxLines = 4,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
                )

                Spacer(modifier = Modifier.height(16.dp))

            }

            item{
                Text(
                    "Alumnos",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(Modifier.height(16.dp))


                Column {
                    if(solicitud?.alumnos?.isEmpty()!!){
                        Text("No hay alumnos")
                    }else{
                        solicitud.alumnos.forEach{ alumno ->
                            Text(alumno)
                        }
                    }
                }
            }

            //Comprobamos todos los campos y habilitamos el boton
            viewModel.habilitarBoton(nif,nombreEmpresa,funciones,horario)

            item{
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        //Añadir solicitud
                        val aux = Solicitud(
                            nif = nif,
                            convocatoria = "Marzo",
                            curso = "2023-2024",
                            empresa = nombreEmpresa,
                            funciones = funciones,
                            horario = horario,
                            plazas = plazas,
                            estado = "Nueva",
                            coordinador = coordinador,
                            checked = (!coordinador.isNullOrBlank())
                        )
                        viewModel.addSolicitud(aux)
                        viewModel.añadirSolicitud(aux)
                        viewModel.añadirSolicitudes()

                        navController.navigate(route = AppScreens.EmpresasScreen.route)
                    },
                    modifier = Modifier.padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(4.dp),
                    enabled = loginEnable
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
fun ListaDeCursosFormulario(ciclo: String, viewModel: SolicitudViewModel, plazas:MutableMap<String, Long>)
{
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Stepper(value = plazas[ciclo] ?: 0L , onValueChange = { plazas.set(ciclo, it) })
        Spacer(modifier = Modifier.width(16.dp))
        Text(ciclo, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun Stepper(
    value: Long,
    onValueChange: (Long) -> Unit,
    minValue: Long = 0L,
    maxValue: Long = 10L
) {

    var counter by rememberSaveable{ mutableLongStateOf(value) }

    onValueChange(counter)

    Row{

        Button(
            onClick = { if (counter > minValue) counter -=1 },
            enabled = value >= minValue,
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
            onClick = { if (counter < maxValue) counter += 1 },
            enabled = value < maxValue,
            shape = RectangleShape,
            modifier = Modifier.graphicsLayer(scaleX = 0.7f, scaleY = 0.7f)
        ) {
            Text("+")
        }

        Spacer(Modifier.width(16.dp))

    }
}

@Composable
fun ExpandableSection(ciclos:List<String>, fp: String, viewModel: SolicitudViewModel, plazas:MutableMap<String, Long>) {
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
                    ListaDeCursosFormulario(ciclo, viewModel, plazas)
            }

        }
    }
}


