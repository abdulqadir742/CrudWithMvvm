package com.example.crudoperation_mvvm_architecture

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.crudoperation_mvvm_architecture.User

@Dao
interface UserDao {
    @Insert
    suspend fun addUser(user: User)

    @Query("SElECT * FROM user_table")
    fun readAllData():LiveData<List<User>>

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)
}