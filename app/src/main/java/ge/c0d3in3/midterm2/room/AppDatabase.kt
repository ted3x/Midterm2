package ge.c0d3in3.midterm2.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ge.c0d3in3.midterm2.presentation.users.model.Address
import ge.c0d3in3.midterm2.presentation.users.model.Company
import ge.c0d3in3.midterm2.presentation.users.model.User
import ge.c0d3in3.midterm2.room.converter.Converter

@Database(entities = [User::class],version = 1)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
}