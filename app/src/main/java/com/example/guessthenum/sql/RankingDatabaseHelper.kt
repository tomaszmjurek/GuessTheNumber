//package com.example.guessthenum.sql
//
//import android.content.Context
//import android.database.sqlite.SQLiteDatabase
//import android.database.sqlite.SQLiteOpenHelper
//
//class RankingDatabaseHelper (context: Context) : SQLiteOpenHelper(context,
//    DATABASE_NAME, null,
//    DATABASE_VERSION
//) {
//
//    // create table sql query
//    private val CREATE_RANKING_TABLE = ("CREATE TABLE " + RANKING_TABLE + "("
//            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
//            + " TEXT," + COLUMN_USER_SCORE + " TEXT" + ")")
//
//    // drop table sql query
//    private val DROP_USER_TABLE = "DROP TABLE IF EXISTS $RANKING_TABLE"
//
//    override fun onCreate(db: SQLiteDatabase) {
//        db.execSQL(CREATE_RANKING_TABLE)
//    }
//
//    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
//
//        //Drop User Table if exist
//        db.execSQL(DROP_RANKING_TABLE)
//
//        // Create tables again
//        onCreate(db)
//    }
//
//    /**
//     * This method is to return top 10 scores
//     *
//     * @return list
//     */
//    fun getAllUser(): List<User> {
//
//        // array of columns to fetch
//        val columns = arrayOf(COLUMN_USER_ID, COLUMN_USER_NAME, COLUMN_USER_PASSWORD)
//
//        // sorting orders
//        val sortOrder = "$COLUMN_USER_NAME ASC"
//        val userList = ArrayList<User>()
//
//        val db = this.readableDatabase
//
//        // query the user table
//        val cursor = db.query(TABLE_USER,
//            columns,            //columns to return
//            null,     //columns for the WHERE clause
//            null,  //The values for the WHERE clause
//            null,      //group the rows
//            null,       //filter by row groups
//            sortOrder)         //The sort order
//        if (cursor.moveToFirst()) {
//            do {
//                val user = User(
//                    id = cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID)).toInt(),
//                    name = cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)),
//                    password = cursor.getString(cursor.getColumnIndex(COLUMN_USER_SCORE))
//                )
//
//                userList.add(user)
//            } while (cursor.moveToNext())
//        }
//        cursor.close()
//        db.close()
//        return userList
//    }
//
//    companion object {
//        // Database Version
//        private val DATABASE_VERSION = 1
//
//        // Database Name
//        private val DATABASE_NAME = "UserManager.db"
//
//        // User table name
//        private val RANKING_TABLE = "ranking"
//
//        // User Table Columns names
//        private val COLUMN_USER_ID = "user_id"
//        private val COLUMN_USER_NAME = "user_name"
//        private val COLUMN_USER_SCORE = "user_score"
//    }
//}