package com.rkcoding.expensetrackerapplication.app_features.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rkcoding.expensetrackerapplication.app_features.data.converter.DateConverter
import com.rkcoding.expensetrackerapplication.app_features.data.local.entity.AccountEntity
import com.rkcoding.expensetrackerapplication.app_features.data.local.entity.TransactionEntity

@TypeConverters(value = [DateConverter::class])
@Database(
    entities = [AccountEntity::class, TransactionEntity::class],
    version = 1,
    exportSchema = true
)
abstract class TransactionDatabase: RoomDatabase() {

    abstract val dao: TransactionDao

}