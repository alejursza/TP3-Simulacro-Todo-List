package ort.argentina.yatay.tp3.tp_simulacro.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ort.argentina.yatay.tp3.tp_simulacro.models.Task
import ort.argentina.yatay.tp3.tp_simulacro.models.TaskStatus
import ort.argentina.yatay.tp3.tp_simulacro.ui.theme.TP_simulacroTheme

@Composable
fun TaskComponent(
    task: Task,
    onEdit: (Task) -> Unit = {},
    onRemove: (Task) -> Unit = {},
    onComplete: (Task) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = task.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = task.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Botón Editar
                Button(
                    onClick = { onEdit(task) },
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Text("Editar")
                }

                // Botón Eliminar
                OutlinedButton(
                    onClick = { onRemove(task) },
                    modifier = Modifier.padding(end = if (task.status == TaskStatus.TODO) 8.dp else 0.dp)
                ) {
                    Text("Eliminar")
                }

                // Botón Completado (solo para tareas TODO)
                if (task.status == TaskStatus.TODO) {
                    FilledTonalButton(
                        onClick = { onComplete(task) }
                    ) {
                        Text("Completado")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskComponentPreview() {
    TP_simulacroTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            TaskComponent(
                task = Task(
                    id = 1,
                    name = "Tarea de prueba",
                    description = "Esta es una descripción de ejemplo",
                    status = TaskStatus.TODO
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            TaskComponent(
                task = Task(
                    id = 2,
                    name = "Tarea completada",
                    description = "Esta tarea ya está terminada",
                    status = TaskStatus.FINISHED
                )
            )
        }
    }
}

