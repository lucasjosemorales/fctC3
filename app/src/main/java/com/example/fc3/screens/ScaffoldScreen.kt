package com.example.fc3.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fc3.navigation.AppScreens
import com.example.fctc3.R

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ScaffoldScreen(navController: NavController)
{
    Scaffold(
        content = { paddingValues -> MyContent(modifier = Modifier.padding(paddingValues))
        },
        topBar = { ExampleTopAppBar() },
        bottomBar = {
            BottomAppBar(
                //containerColor = com.example.fctc3.ui.theme,
                //contentColor = Color.White
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                )
                {
                    IconButton(onClick = { }) {
                        Icon(painter = painterResource(id = R.drawable.home), contentDescription = "Empresas")
                    }
                    IconButton(onClick = { }) {
                        Icon(painter = painterResource(id = R.drawable.empresas), contentDescription = "Empresas")
                    }
                    IconButton(onClick = { }) {
                        Icon(painter = painterResource(id = R.drawable.profesores), contentDescription = "Profesores")
                    }
                    IconButton(onClick = { }) {
                        Icon(painter = painterResource(id = R.drawable.alumnos), contentDescription = "Alumnos")
                    }
                    IconButton(onClick = { }) {
                        Icon(painter = painterResource(id = R.drawable.solicitudes), contentDescription = "Solicitudes")
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { })
            {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Añadir"
                )
            }
        }
    )
}

/*
{ innerPadding -> Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                modifier = Modifier.padding(8.dp).align(Alignment.CenterHorizontally),
                text = "Pantalla principal para la gestión de la FCT".trimIndent(),
            )
        }
    }
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ExampleTopAppBar() {
    TopAppBar(
        title = { Text(text = "FCT C3") },
        actions = {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Buscar")
            }
            IconButton(onClick = { }) {
                Icon(painter = painterResource(id = R.drawable.filtrar), contentDescription = "Ordenar")
            }
            IconButton(onClick = { }) {
                Icon(painter = painterResource(id = R.drawable.ordenar), contentDescription = "Filtrar")
            }
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Más")
            }
        }
    )
}
@Composable
fun MyContent(modifier: Modifier){
    Box()
    {
        Text(
            text = "Esto es una prueba"
        )
    }
}