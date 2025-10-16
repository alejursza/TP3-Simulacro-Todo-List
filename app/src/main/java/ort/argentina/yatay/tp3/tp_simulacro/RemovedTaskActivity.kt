package ort.argentina.yatay.tp3.tp_simulacro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import ort.argentina.yatay.tp3.tp_simulacro.ui.theme.TP_simulacroTheme

class RemovedTaskActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TP_simulacroTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RemovedTaskScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun RemovedTaskScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "REMOVED TASK - Tareas Eliminadas",
            fontSize = 24.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RemovedTaskScreenPreview() {
    TP_simulacroTheme {
        RemovedTaskScreen()
    }
}

