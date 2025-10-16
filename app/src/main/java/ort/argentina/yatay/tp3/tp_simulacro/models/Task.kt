package ort.argentina.yatay.tp3.tp_simulacro.models

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class TaskStatus {
    TODO,
    FINISHED,
    REMOVED
}

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String,
    val status: TaskStatus
)
