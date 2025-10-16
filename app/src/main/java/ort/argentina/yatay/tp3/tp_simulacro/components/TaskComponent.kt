package ort.argentina.yatay.tp3.tp_simulacro.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        when (task.status) {
            TaskStatus.TODO -> {
                // RadioButton para tareas pendientes
                RadioButton(
                    selected = false,
                    onClick = { onComplete(task) },
                    modifier = Modifier.padding(end = 16.dp)
                )
            }
            TaskStatus.FINISHED -> {
                // Checkmark verde para tareas completadas
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Completada",
                    tint = Color(0xFF4CAF50),
                    modifier = Modifier
                        .size(24.dp)
                        .padding(end = 16.dp)
                )
            }
            TaskStatus.REMOVED -> {
                // Para tareas eliminadas (por si se usa después)
                RadioButton(
                    selected = false,
                    onClick = { },
                    enabled = false,
                    modifier = Modifier.padding(end = 16.dp)
                )
            }
        }

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = task.name,
                style = MaterialTheme.typography.bodyLarge,
                color = if (task.status == TaskStatus.FINISHED) {
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                } else {
                    MaterialTheme.colorScheme.onSurface
                },
                textDecoration = if (task.status == TaskStatus.FINISHED) {
                    TextDecoration.LineThrough
                } else {
                    TextDecoration.None
                }
            )

            if (task.description.isNotEmpty() && task.status != TaskStatus.FINISHED) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = task.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // Botones de acción con íconos
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Botón Editar
            IconButton(
                onClick = { onEdit(task) },
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = "Editar",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Botón Completado (solo para tareas TODO)
            if (task.status == TaskStatus.TODO) {
                IconButton(
                    onClick = { onComplete(task) },
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Check,
                        contentDescription = "Completar",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Botón Eliminar
            IconButton(
                onClick = { onRemove(task) },
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Close,
                    contentDescription = "Eliminar",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
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
                    name = "Hacer la compra semanal",
                    description = "Comprar frutas, verduras y lácteos",
                    status = TaskStatus.TODO
                )
            )

            HorizontalDivider()

            TaskComponent(
                task = Task(
                    id = 2,
                    name = "Revisar el informe del proyecto",
                    description = "",
                    status = TaskStatus.TODO
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            TaskComponent(
                task = Task(
                    id = 3,
                    name = "Enviar la presentación de marketing",
                    description = "",
                    status = TaskStatus.FINISHED
                )
            )

            TaskComponent(
                task = Task(
                    id = 4,
                    name = "Reservar cita con el dentista",
                    description = "",
                    status = TaskStatus.FINISHED
                )
            )
        }
    }
}
