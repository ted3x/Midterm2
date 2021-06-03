package ge.c0d3in3.midterm2

import android.app.Application
import androidx.room.Room
import ge.c0d3in3.midterm2.room.AppDatabase

class App : Application() {

    companion object {
        lateinit var roomDatabase: AppDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()
        roomDatabase =
            Room.databaseBuilder(applicationContext, AppDatabase::class.java, "Midterm-Db")
                .allowMainThreadQueries()
                .build()
    }
}
