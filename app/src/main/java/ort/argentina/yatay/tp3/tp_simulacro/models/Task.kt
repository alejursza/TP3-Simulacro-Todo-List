package ort.argentina.yatay.tp3.tp_simulacro.models

enum class TaskStatus {
    TODO,
    FINISHED,
    REMOVED
}

data class Task(
    val id: Int,
    val name: String,
    val description: String,
    val status: TaskStatus
)

