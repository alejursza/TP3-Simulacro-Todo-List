# Guía de uso de Room - TP3 Simulacro Todo List

## 📚 Estructura de Persistencia con Room

### Archivos creados:

1. **models/Task.kt** - Entidad de Room (ya actualizada con @Entity)
2. **database/TaskDao.kt** - Data Access Object con todas las queries
3. **database/Converters.kt** - Convierte TaskStatus a String para Room
4. **database/TaskDatabase.kt** - Base de datos Room
5. **repository/TaskRepository.kt** - Capa de repositorio
6. **viewmodel/TaskViewModel.kt** - ViewModel con todas las operaciones

---

## 🚀 Cómo usar en CreateTaskActivity

```kotlin
class CreateTaskActivity : ComponentActivity() {
    private val viewModel: TaskViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // ... tu código de UI
            
            // Para crear una tarea:
            Button(onClick = {
                viewModel.addTask(
                    name = taskName,
                    description = taskDescription,
                    status = TaskStatus.TODO
                )
                finish() // Volver a la pantalla anterior
            })
        }
    }
}
```

---

## ✏️ Cómo usar en EditTaskActivity

```kotlin
class EditTaskActivity : ComponentActivity() {
    private val viewModel: TaskViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Obtener el ID de la tarea desde el Intent
        val taskId = intent.getIntExtra("TASK_ID", 0)
        
        setContent {
            var task by remember { mutableStateOf<Task?>(null) }
            
            // Cargar la tarea
            LaunchedEffect(taskId) {
                task = viewModel.getTaskById(taskId)
            }
            
            // ... tu código de UI con los campos prellenados
            
            // Para actualizar la tarea:
            Button(onClick = {
                task?.let { currentTask ->
                    viewModel.updateTask(
                        currentTask.copy(
                            name = newName,
                            description = newDescription
                        )
                    )
                }
                finish()
            })
        }
    }
}
```

**Importante:** Para navegar a EditTaskActivity desde MainTodoActivity, usar:
```kotlin
val intent = Intent(this, EditTaskActivity::class.java)
intent.putExtra("TASK_ID", task.id)
startActivity(intent)
```

---

## 🗑️ Cómo usar en RemovedTaskActivity

```kotlin
class RemovedTaskActivity : ComponentActivity() {
    private val viewModel: TaskViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Observar tareas eliminadas
            val removedTasks by viewModel.removedTasks.collectAsState()
            
            LazyColumn {
                items(removedTasks) { task ->
                    TaskComponent(
                        task = task,
                        onEdit = { /* No permitir editar eliminadas */ },
                        onRemove = {
                            // Eliminar permanentemente
                            viewModel.deleteTask(task)
                        }
                    )
                }
            }
        }
    }
}
```

---

## 📋 Operaciones disponibles en TaskViewModel

```kotlin
// Crear tarea
viewModel.addTask(name = "...", description = "...", status = TaskStatus.TODO)

// Actualizar tarea completa
viewModel.updateTask(task)

// Eliminar tarea permanentemente
viewModel.deleteTask(task)

// Marcar como completada
viewModel.markTaskAsFinished(taskId)

// Marcar como eliminada (mover a papelera)
viewModel.markTaskAsRemoved(taskId)

// Volver a marcar como pendiente
viewModel.markTaskAsTodo(taskId)

// Obtener tarea por ID (suspend function)
val task = viewModel.getTaskById(taskId)
```

---

## 🔄 Observar cambios en tiempo real

```kotlin
// En cualquier Activity/Composable
val todoTasks by viewModel.todoTasks.collectAsState()
val finishedTasks by viewModel.finishedTasks.collectAsState()
val removedTasks by viewModel.removedTasks.collectAsState()

// Las listas se actualizan automáticamente cuando hay cambios en la BD
```

---

## ✅ Ya está funcionando en MainTodoActivity

Los botones de **Completar** y **Eliminar** ya están conectados:
- Al presionar "Completar" ✓, la tarea se mueve a "Tareas Completadas"
- Al presionar "Eliminar" ✕, la tarea se marca como REMOVED
- La lista se actualiza automáticamente

---

## 🔧 Notas importantes

1. **No es necesario llamar a `Sync()`** - Room usa Flows que se actualizan automáticamente
2. **TaskStatus** tiene 3 estados: `TODO`, `FINISHED`, `REMOVED`
3. **El ID se genera automáticamente** - no es necesario especificarlo al crear
4. **Todas las operaciones son asíncronas** - se ejecutan en coroutines
5. **El ViewModel se encarga de todo** - solo úsenlo en sus Activities

---

## 📝 Ejemplo completo de CreateTaskActivity

Ver el archivo de ejemplo que se creará a continuación...

