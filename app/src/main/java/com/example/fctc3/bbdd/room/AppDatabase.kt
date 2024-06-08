package com.example.fctc3.bbdd.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.impl.WorkDatabaseMigrations.MIGRATION_1_2
import com.example.fct.models.Profesor
import com.example.fctc3.models.Ciclo

@Database(entities = [Profesor::class, Ciclo::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun profesorDao(): ProfesorDAO
    abstract fun cicloDao(): CicloDAO



    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Asegúrate de usar el SQL correcto para crear la nueva tabla
                database.execSQL("""
            CREATE TABLE IF NOT EXISTS ciclos (
                nombreCorto TEXT PRIMARY KEY NOT NULL,
                nombreLargo TEXT NOT NULL,
                familia TEXT NOT NULL
            )
        """)
            }
        }

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).addMigrations(MIGRATION_1_2)
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}