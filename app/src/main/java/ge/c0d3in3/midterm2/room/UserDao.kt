package ge.c0d3in3.midterm2.room

import androidx.room.*
import ge.c0d3in3.midterm2.presentation.users.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users : List<User>)

    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("DELETE FROM user")
    fun clear()
}