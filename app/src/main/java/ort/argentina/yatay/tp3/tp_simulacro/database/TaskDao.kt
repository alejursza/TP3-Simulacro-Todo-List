package ort.argentina.yatay.tp3.tp_simulacro.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ort.argentina.yatay.tp3.tp_simulacro.models.Task
import ort.argentina.yatay.tp3.tp_simulacro.models.TaskStatus

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks WHERE status = 'TODO' ORDER BY id DESC")
    fun getTodoTasks(): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE status = 'FINISHED' ORDER BY id DESC")
    fun getFinishedTasks(): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE status = 'REMOVED' ORDER BY id DESC")
    fun getRemovedTasks(): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE id = :taskId")
    suspend fun getTaskById(taskId: Int): Task?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task): Long

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("DELETE FROM tasks WHERE id = :taskId")
    suspend fun deleteTaskById(taskId: Int)

    @Query("UPDATE tasks SET status = :newStatus WHERE id = :taskId")
    suspend fun updateTaskStatus(taskId: Int, newStatus: TaskStatus)
}

