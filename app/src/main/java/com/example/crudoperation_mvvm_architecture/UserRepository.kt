package com.example.crudoperation_mvvm_architecture

import androidx.lifecycle.LiveData
import com.example.crudoperation_mvvm_architecture.User
import com.example.crudoperation_mvvm_architecture.UserDao

class UserRepository(private val userDao: UserDao) {
    val readAllData:LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User)
    {
        userDao.deleteUser(user)
    }
}