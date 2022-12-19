package com.example.crudoperation_mvvm_architecture

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val FirstName:String,
    val LastName:String,
    val Age:Int
    ):Parcelable
