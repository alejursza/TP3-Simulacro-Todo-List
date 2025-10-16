package ort.argentina.yatay.tp3.tp_simulacro

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
                Task(1, "Completar el TP", "Finalizar el trabajo práctico de Android", TaskStatus.TODO),
                Task(2, "Estudiar Compose", "Repasar los conceptos de Jetpack Compose", TaskStatus.TODO),
                Task(3, "Hacer ejercicios", "Practicar con los ejemplos del curso", TaskStatus.TODO)
            )
        )
    }

    var listFinishedTasks by remember {
        mutableStateOf(
            listOf(
                Task(4, "Configurar proyecto", "Crear el proyecto en Android Studio", TaskStatus.FINISHED),
                Task(5, "Instalar dependencias", "Agregar las librerías necesarias", TaskStatus.FINISHED)
            )
        )
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Título principal
        item {
            Text(
                text = "Mis tareas",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }

        // Sección: Tareas Pendientes
        item {
            Text(
                text = "Tareas pendientes",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }

        if (listToDoTasks.isEmpty()) {
            item {
                Text(
                    text = "No hay tareas pendientes",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        } else {
            items(listToDoTasks) { task ->
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
            }
        }

        // Espaciado entre secciones
        item {
            Spacer(modifier = Modifier.height(24.dp))
        }

        // Sección: Tareas Completadas
        item {
            Text(
                text = "Tareas completadas",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }

        if (listFinishedTasks.isEmpty()) {
            item {
                Text(
                    text = "No hay tareas completadas",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        } else {
            items(listFinishedTasks) { task ->
                TaskComponent(
                    task = task,
                    onEdit = {
                        // TODO: Navegar a EditTaskActivity
                    },
                    onRemove = {
                        // TODO: Eliminar tarea
                    }
                )
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
