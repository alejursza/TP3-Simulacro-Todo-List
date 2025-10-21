package ort.argentina.yatay.tp3.tp_simulacro.database

import androidx.room.TypeConverter
import ort.argentina.yatay.tp3.tp_simulacro.models.TaskStatus

class Converters {

    @TypeConverter
    fun fromTaskStatus(value: TaskStatus): String {
        return value.name
    }

    @TypeConverter
    fun toTaskStatus(value: String): TaskStatus {
        return TaskStatus.valueOf(value)
    }
}