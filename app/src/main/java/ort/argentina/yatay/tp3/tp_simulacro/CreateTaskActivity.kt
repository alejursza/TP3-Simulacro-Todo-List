package ort.argentina.yatay.tp3.tp_simulacro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ort.argentina.yatay.tp3.tp_simulacro.models.TaskStatus
import ort.argentina.yatay.tp3.tp_simulacro.ui.theme.TP_simulacroTheme
import ort.argentina.yatay.tp3.tp_simulacro.viewmodel.TaskViewModel

class CreateTaskActivity : ComponentActivity() {
    private val viewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TP_simulacroTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CreateTaskScreen(
                        modifier = Modifier.padding(innerPadding),
                        onSaveTask = { name, description ->
                            viewModel.addTask(
                                name = name,
                                description = description,
                                status = TaskStatus.TODO
                            )
                            finish()
                        },
                        onCancel = {
                            finish()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CreateTaskScreen(
    modifier: Modifier = Modifier,
    onSaveTask: (String, String) -> Unit = { _, _ -> },
    onCancel: () -> Unit = {}
) {
    var taskName by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Crear Nueva Tarea",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Campo de nombre
        OutlinedTextField(
            value = taskName,
            onValueChange = {
                taskName = it
                showError = false
            },
            label = { Text("Nombre de la tarea") },
            placeholder = { Text("Ej: Hacer la compra") },
            modifier = Modifier.fillMaxWidth(),
            isError = showError && taskName.isBlank(),
            supportingText = {
                if (showError && taskName.isBlank()) {
                    Text("El nombre es obligatorio")
                }
            },
            singleLine = true
        )

        // Campo de descripción
        OutlinedTextField(
            value = taskDescription,
            onValueChange = { taskDescription = it },
            label = { Text("Descripción (opcional)") },
            placeholder = { Text("Detalles de la tarea...") },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            maxLines = 5
        )

        Spacer(modifier = Modifier.weight(1f))

        // Botones
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(
                onClick = onCancel,
                modifier = Modifier.weight(1f)
            ) {
                Text("Cancelar")
            }

            Button(
                onClick = {
                    if (taskName.isNotBlank()) {
                        onSaveTask(taskName.trim(), taskDescription.trim())
                    } else {
                        showError = true
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Guardar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateTaskScreenPreview() {
    TP_simulacroTheme {
        CreateTaskScreen()
    }
}
