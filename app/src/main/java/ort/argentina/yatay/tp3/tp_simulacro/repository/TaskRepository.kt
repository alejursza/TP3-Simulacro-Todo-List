package ort.argentina.yatay.tp3.tp_simulacro.repository

import kotlinx.coroutines.flow.Flow
import ort.argentina.yatay.tp3.tp_simulacro.database.TaskDao
import ort.argentina.yatay.tp3.tp_simulacro.models.Task
import ort.argentina.yatay.tp3.tp_simulacro.models.TaskStatus

class TaskRepository(private val taskDao: TaskDao) {

    // Flows para observar cambios en las listas
    val todoTasks: Flow<List<Task>> = taskDao.getTodoTasks()
    val finishedTasks: Flow<List<Task>> = taskDao.getFinishedTasks()
    val removedTasks: Flow<List<Task>> = taskDao.getRemovedTasks()

    // Operaciones CRUD
    suspend fun insertTask(task: Task): Long {
        return taskDao.insertTask(task)
    }

    suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }

    suspend fun deleteTaskById(taskId: Int) {
        taskDao.deleteTaskById(taskId)
    }

    suspend fun getTaskById(taskId: Int): Task? {
        return taskDao.getTaskById(taskId)
    }

    // Operaciones específicas
    suspend fun markTaskAsFinished(taskId: Int) {
        taskDao.updateTaskStatus(taskId, TaskStatus.FINISHED)
    }

    suspend fun markTaskAsRemoved(taskId: Int) {
        taskDao.updateTaskStatus(taskId, TaskStatus.REMOVED)
    }

    suspend fun markTaskAsTodo(taskId: Int) {
        taskDao.updateTaskStatus(taskId, TaskStatus.TODO)
    }
}

