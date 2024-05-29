package com.ahmed_apps.notes_app_testing.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * @author Ahmed Guedmioui
 */
@Database(
    entities = [NoteEntity::class],
    version = 1
)
abstract class NoteDb: RoomDatabase() {
    abstract val noteDao: NoteDao
}