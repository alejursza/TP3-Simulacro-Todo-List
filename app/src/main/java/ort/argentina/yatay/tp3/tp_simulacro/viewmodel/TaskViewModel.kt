package ort.argentina.yatay.tp3.tp_simulacro.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ort.argentina.yatay.tp3.tp_simulacro.database.TaskDatabase
import ort.argentina.yatay.tp3.tp_simulacro.models.Task
import ort.argentina.yatay.tp3.tp_simulacro.models.TaskStatus
import ort.argentina.yatay.tp3.tp_simulacro.repository.TaskRepository

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TaskRepository

    // StateFlows para observar las listas de tareas
    val todoTasks: StateFlow<List<Task>>
    val finishedTasks: StateFlow<List<Task>>
    val removedTasks: StateFlow<List<Task>>

    init {
        val taskDao = TaskDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(taskDao)

        // Convertir Flows a StateFlows para Compose
        todoTasks = repository.todoTasks.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

        finishedTasks = repository.finishedTasks.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

        removedTasks = repository.removedTasks.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    }

    // Funciones para operaciones CRUD
    fun addTask(name: String, description: String, status: TaskStatus = TaskStatus.TODO) {
        viewModelScope.launch {
            val task = Task(
                name = name,
                description = description,
                status = status
            )
            repository.insertTask(task)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            repository.updateTask(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }

    fun markTaskAsFinished(taskId: Int) {
        viewModelScope.launch {
            repository.markTaskAsFinished(taskId)
        }
    }

    fun markTaskAsRemoved(taskId: Int) {
        viewModelScope.launch {
            repository.markTaskAsRemoved(taskId)
        }
    }

    fun markTaskAsTodo(taskId: Int) {
        viewModelScope.launch {
            repository.markTaskAsTodo(taskId)
        }
    }

    suspend fun getTaskById(taskId: Int): Task? {
        return repository.getTaskById(taskId)
    }
}

