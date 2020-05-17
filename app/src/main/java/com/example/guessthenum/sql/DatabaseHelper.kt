//package com.example.guessthenum.sql
//
//import android.content.Context
//import android.database.sqlite.SQLiteOpenHelper
//
//abstract class DatabaseHelper (context: Context) : SQLiteOpenHelper(context,
//    DATABASE_NAME, null,
//    DATABASE_VERSION) {
//
//
//        companion object {
//
//        // Database Version
//        private val DATABASE_VERSION = 1
//
//        // Database Name
//        private val DATABASE_NAME = "UserManager.db"
//
//        // User table name
//        private val TABLE_USER = "user"
//
//        // User Table Columns names
//        private val COLUMN_USER_ID = "user_id"
//        private val COLUMN_USER_NAME = "user_name"
//        private val COLUMN_USER_PASSWORD = "user_password"
//    }
//}