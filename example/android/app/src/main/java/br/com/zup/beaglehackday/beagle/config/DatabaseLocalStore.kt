/*
 * Copyright (c) 2020 ZUP IT SERVICOS EM TECNOLOGIA E INOVACAO SA.
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package br.com.zup.beaglehackday.beagle.config

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import br.com.zup.beagle.android.store.LocalStore
import br.com.zup.beaglehackday.AppApplication

internal object ScreenEntry : BaseColumns {
    const val TABLE_NAME = "KeyValueCache"
    const val KEY_COLUMN_NAME = "key"
    const val VALUE_COLUMN_NAME = "value"
}

internal class DatabaseLocalStore(
    private val contentValuesFactory: ContentValuesFactory = ContentValuesFactory(),
    private val database: SQLiteDatabase = BeagleDatabaseManager.getDatabase(AppApplication.APPLICATION!!)
) : LocalStore {

    override fun save(key: String, value: String) {
        val values = contentValuesFactory.make().apply {
            put(ScreenEntry.KEY_COLUMN_NAME, key)
            put(ScreenEntry.VALUE_COLUMN_NAME, value)
        }


        val newRowId = database.insertWithOnConflict(ScreenEntry.TABLE_NAME, null, values,
            SQLiteDatabase.CONFLICT_REPLACE)
        if (newRowId == -1L) {
            BeagleMessageLogs.logDataNotInsertedOnDatabase(key, value)
        }
    }

    override fun restore(key: String): String? {
        return executeRestoreQueryForKey(key).use { cursor ->
            if (cursor.count > 0) {
                cursor.moveToFirst()
                cursor.getString(cursor.getColumnIndexOrThrow(ScreenEntry.VALUE_COLUMN_NAME))
            } else {
                null
            }
        }
    }

    override fun delete(key: String) {
        database.delete(ScreenEntry.TABLE_NAME, "${ScreenEntry.KEY_COLUMN_NAME}=?", arrayOf(key))
    }

    override fun getAll(): Map<String, String> {
        val columnsToReturn = arrayOf(ScreenEntry.KEY_COLUMN_NAME, ScreenEntry.VALUE_COLUMN_NAME)
        val columnsForWhereClause = ""
        val valuesForWhereClause = arrayOf<String>()
        val cursor = database.query(
            ScreenEntry.TABLE_NAME,
            columnsToReturn,
            columnsForWhereClause,
            valuesForWhereClause,
            null,
            null,
            null
        )

        val returnMap = mutableMapOf<String, String>()
        if (cursor.count > 0) {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                returnMap[cursor.getString(cursor.getColumnIndexOrThrow(ScreenEntry.KEY_COLUMN_NAME))] =
                    cursor.getString(cursor.getColumnIndexOrThrow(ScreenEntry.VALUE_COLUMN_NAME))

                cursor.moveToNext()
            }
        }
        cursor.close()

        return returnMap
    }

    private fun executeRestoreQueryForKey(key: String): Cursor {
        val columnsToReturn = arrayOf(ScreenEntry.VALUE_COLUMN_NAME)
        val columnsForWhereClause = "${ScreenEntry.KEY_COLUMN_NAME}=?"
        val valuesForWhereClause = arrayOf(key)
        return database.query(
            ScreenEntry.TABLE_NAME,
            columnsToReturn,
            columnsForWhereClause,
            valuesForWhereClause,
            null,
            null,
            null
        )
    }
}

internal class ContentValuesFactory {
    fun make(): ContentValues = ContentValues()
}

internal object BeagleDatabaseManager {

    private const val DATABASE_NAME = "BeagleDefaultStore.db"
    private const val DATABASE_VERSION = 2

    private lateinit var database: SQLiteDatabase

    fun getDatabase(context: Context): SQLiteDatabase {
        if (!::database.isInitialized) {
            database = BeagleSQLiteDatabase(
                context,
                DATABASE_NAME,
                DATABASE_VERSION
            ).writableDatabase
        }
        return database
    }
}

internal open class BeagleSQLiteDatabase(
    context: Context,
    databaseName: String,
    databaseVersion: Int
) : SQLiteOpenHelper(
    context,
    databaseName,
    null,
    databaseVersion
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE ${ScreenEntry.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${ScreenEntry.KEY_COLUMN_NAME} TEXT NOT NULL UNIQUE," +
                "${ScreenEntry.VALUE_COLUMN_NAME} TEXT NOT NULL" +
                ")"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val deleteTableQuery = "DROP TABLE IF EXISTS ${ScreenEntry.TABLE_NAME}"
        db?.execSQL(deleteTableQuery)
        onCreate(db)
    }
}