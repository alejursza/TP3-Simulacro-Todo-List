package ort.argentina.yatay.tp3.tp_simulacro

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ort.argentina.yatay.tp3.tp_simulacro.components.TaskComponent
import ort.argentina.yatay.tp3.tp_simulacro.models.Task
import ort.argentina.yatay.tp3.tp_simulacro.models.TaskStatus
import ort.argentina.yatay.tp3.tp_simulacro.ui.theme.TP_simulacroTheme

class MainTodoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TP_simulacroTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        BtnAdd(onClick = {
                            val intent = Intent(this, CreateTaskActivity::class.java)
                            startActivity(intent)
                        })
                    }
                ) { innerPadding ->
                    MainTodoScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainTodoScreen(modifier: Modifier = Modifier) {
    // Listas de tareas (por ahora con datos de ejemplo)
    var listToDoTasks by remember {
        mutableStateOf(
            listOf(
                Task(1, "Hacer la compra semanal", "", TaskStatus.TODO),
                Task(2, "Revisar el informe del proyecto", "", TaskStatus.TODO),
                Task(3, "Llamar al fontanero", "", TaskStatus.TODO)
            )
        )
    }

    var listFinishedTasks by remember {
        mutableStateOf(
            listOf(
                Task(4, "Enviar la presentación de marketing", "", TaskStatus.FINISHED),
                Task(5, "Reservar cita con el dentista", "", TaskStatus.FINISHED)
            )
        )
    }

    // Estado para controlar si la sección de completadas está expandida
    var isCompletedExpanded by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        // Título principal
        item {
            Text(
                text = "Mis Tareas",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp)
            )
        }

        // Sección: Tareas Pendientes
        item {
            Text(
                text = "Tareas Pendientes",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }

        if (listToDoTasks.isEmpty()) {
            item {
                Text(
                    text = "No hay tareas pendientes",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        } else {
            itemsIndexed(listToDoTasks) { index, task ->
                TaskComponent(
                    task = task,
                    onEdit = {
                        // TODO: Navegar a EditTaskActivity
                    },
                    onRemove = {
                        // TODO: Eliminar tarea
                    },
                    onComplete = {
                        // TODO: Marcar como completada
                    }
                )
                // Agregar divisor entre tareas (excepto después de la última)
                if (index < listToDoTasks.size - 1) {
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
                }
            }
        }

        // Espaciado entre secciones
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Sección: Tareas Completadas (Colapsable)
        item {
            Surface(
                onClick = { isCompletedExpanded = !isCompletedExpanded },
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Tareas Completadas",
                        style = MaterialTheme.typography.titleLarge
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Solo mostrar "Ver tareas finalizadas" cuando está colapsado
                        if (!isCompletedExpanded) {
                            Text(
                                text = "Ver tareas finalizadas (${listFinishedTasks.size})",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                        }
                        Icon(
                            imageVector = if (isCompletedExpanded)
                                Icons.Default.KeyboardArrowUp
                            else
                                Icons.Default.KeyboardArrowDown,
                            contentDescription = if (isCompletedExpanded) "Ocultar" else "Mostrar"
                        )
                    }
                }
            }
        }

        // Lista de tareas completadas (solo si está expandido)
        if (isCompletedExpanded) {
            item {
                // Contenedor con fondo gris para toda la sección expandida
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
                        .padding(vertical = 8.dp)
                ) {
                    if (listFinishedTasks.isEmpty()) {
                        Text(
                            text = "No hay tareas completadas",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    } else {
                        listFinishedTasks.forEachIndexed { index, task ->
                            TaskComponent(
                                task = task,
                                onEdit = {
                                    // TODO: Navegar a EditTaskActivity
                                },
                                onRemove = {
                                    // TODO: Eliminar tarea
                                }
                            )
                            // Agregar divisor entre tareas (excepto después de la última)
                            if (index < listFinishedTasks.size - 1) {
                                HorizontalDivider(
                                    modifier = Modifier.padding(horizontal = 16.dp),
                                    color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                                )
                            }
                        }
                    }
                }
            }
        }

        // Espaciado inferior para el FAB
        item {
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}

@Composable
fun BtnAdd(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Agregar nueva tarea"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainTodoScreenPreview() {
    TP_simulacroTheme {
        MainTodoScreen()
    }
}
