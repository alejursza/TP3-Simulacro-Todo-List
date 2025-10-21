package ort.argentina.yatay.tp3.tp_simulacro

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ort.argentina.yatay.tp3.tp_simulacro.ui.theme.TP_simulacroTheme
import ort.argentina.yatay.tp3.tp_simulacro.ui.TaskDetailScreen   // <-- IMPORTA TU PANTALLA

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TP_simulacroTheme {
                TaskDetailScreen(
                    onBack = { finish() },
                    onDelete = {
                        Toast.makeText(this, "Tarea eliminada", Toast.LENGTH_SHORT).show()
                        finish()
                    },
                    onSave = { name, _, date, prio ->
                        Toast.makeText(this, "Guardado: $name • $date • $prio", Toast.LENGTH_SHORT).show()
                    },
                    onCancel = { finish() }
                )
            }
        }
    }
}