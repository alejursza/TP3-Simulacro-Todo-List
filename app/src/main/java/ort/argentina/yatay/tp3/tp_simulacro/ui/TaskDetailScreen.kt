package ort.argentina.yatay.tp3.tp_simulacro.ui

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.Calendar

private val Blue   = Color(0xFF007AFF)
private val Bg     = Color(0xFFF2F2F7)
private val High   = Color(0xFFFF3B30)
private val Medium = Color(0xFFFF9500)
private val Low    = Color(0xFF34C759)

enum class Priority { BAJA, MEDIA, ALTA }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(
    onBack: () -> Unit = {},
    onDelete: () -> Unit = {},
    onSave: (String, String, String, Priority) -> Unit = { _,_,_,_ -> },
    onCancel: () -> Unit = {}
) {
    var name by remember { mutableStateOf(TextFieldValue("Diseñar la nueva landing page")) }
    var description by remember {
        mutableStateOf(
            TextFieldValue(
                "Crear un diseño moderno y atractivo que siga las nuevas guías de estilo de la marca. " +
                        "Incluir secciones para características, testimonios y precios."
            )
        )
    }
    var dateText by remember { mutableStateOf("2024-08-15") }
    var prio by remember { mutableStateOf(Priority.MEDIA) }

    val ctx = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Detalle de Tarea", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = Blue)
                    }
                },
                actions = {
                    IconButton(onClick = onDelete) {
                        Icon(Icons.Outlined.Delete, contentDescription = "Eliminar", tint = High)
                    }
                }
            )
        },
        bottomBar = {
            Column(
                Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp)
            ) {
                Button(
                    onClick = { onSave(name.text, description.text, dateText, prio) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Blue)
                ) { Text("Guardar") }

                Spacer(Modifier.height(8.dp))
                TextButton(
                    onClick = onCancel,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp)
                ) { Text("Cancelar") }
            }
        }
    ) { inner ->
        Column(
            Modifier
                .padding(inner)
                .fillMaxSize()
                .background(Bg)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Nombre
            Text("Nombre", fontWeight = FontWeight.SemiBold)
            OutlinedTextField(
                value = name, onValueChange = { name = it },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp)
            )

            // Descripción
            Text("Descripción", fontWeight = FontWeight.SemiBold)
            OutlinedTextField(
                value = description, onValueChange = { description = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 140.dp),
                shape = RoundedCornerShape(12.dp)
            )

            // Fecha
            Text("Fecha", fontWeight = FontWeight.SemiBold)
            OutlinedTextField(
                value = dateText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    Icon(Icons.Outlined.CalendarToday, contentDescription = "Calendario")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clickable {
                        val cal = Calendar.getInstance()
                        val dlg = DatePickerDialog(
                            ctx,
                            { _, y, m, d -> dateText = "%04d-%02d-%02d".format(y, m + 1, d) },
                            cal.get(Calendar.YEAR),
                            cal.get(Calendar.MONTH),
                            cal.get(Calendar.DAY_OF_MONTH)
                        )
                        dlg.show()
                    },
                shape = RoundedCornerShape(12.dp)
            )

            // Prioridad
            Text("Prioridad", fontWeight = FontWeight.SemiBold)
            PrioritySegmented(
                selected = prio,
                onSelect = { prio = it }
            )
        }
    }
}

@Composable
private fun PriorityChip(
    text: String,
    selected: Boolean,
    color: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .height(44.dp)
            .background(
                color = if (selected) color else MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 12.dp)
            .wrapContentWidth()
            .wrapContentHeight(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (selected) Color.White else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun PrioritySegmented(selected: Priority, onSelect: (Priority) -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(12.dp))
            .padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(Modifier.weight(1f).clickable { onSelect(Priority.BAJA) }) {
            PriorityChip("Baja", selected == Priority.BAJA, Low, Modifier.fillMaxWidth())
        }
        Box(Modifier.weight(1f).clickable { onSelect(Priority.MEDIA) }) {
            PriorityChip("Media", selected == Priority.MEDIA, Medium, Modifier.fillMaxWidth())
        }
        Box(Modifier.weight(1f).clickable { onSelect(Priority.ALTA) }) {
            PriorityChip("Alta", selected == Priority.ALTA, High, Modifier.fillMaxWidth())
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewTaskDetail() {
    MaterialTheme { TaskDetailScreen() }
}