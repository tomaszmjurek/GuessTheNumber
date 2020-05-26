package com.example.guessthenum.sql

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.guessthenum.login.User

class UserDatabaseHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    // create table sql query
    private val CREATE_USER_TABLE = ("CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_NAME + " TEXT, "
            + COLUMN_USER_PASSWORD + " TEXT, "
            + COLUMN_USER_SCORE + " INTEGER)")

    // drop table sql query
    private val DROP_USER_TABLE = "DROP TABLE IF EXISTS $TABLE_USER"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_USER_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE)
        // Create tables again
        onCreate(db)
    }

    /**
     * This method fetches best 10 scores to use in RankingActivity
     */
    fun getTenBestUsers() : List<User> {
        // array of columns to fetch
        val columns = arrayOf(COLUMN_USER_ID, COLUMN_USER_NAME, COLUMN_USER_PASSWORD, COLUMN_USER_SCORE)

        // sorting orders
        val sortOrder = "$COLUMN_USER_SCORE DESC"
        val userList = ArrayList<User>()
        val db = this.readableDatabase

        // query the user table
        val cursor = db.query(TABLE_USER,
            columns,            //columns to return
            null,     //columns for the WHERE clause
            null,  //The values for the WHERE clause
            null,      //group the rows
            null,       //filter by row groups
            sortOrder)         //The sort order
        if (cursor.moveToFirst()) {
            var i = 0
            do {
                val user = User(
                    id = cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID)).toInt(),
                    name = cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)),
                    password = cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)),
                    score = cursor.getString(cursor.getColumnIndex(COLUMN_USER_SCORE)).toInt()
                )
                userList.add(user)
                i++
            } while (cursor.moveToNext() && i < 10)
        }
        cursor.close()
        db.close()
        return userList
    }

    /**
     * This method is to create user record
     * @param user
     */
    fun addUser(user: User) {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(COLUMN_USER_NAME, user.name)
        values.put(COLUMN_USER_PASSWORD, user.password)
        values.put(COLUMN_USER_SCORE, user.score)

        // Inserting Row
        db.insert(TABLE_USER, null, values)
        db.close()
    }


    /**
     * This method to check user exist or not
     * @param name
     * @return true/false
     */
    fun userExists(name: String): Boolean {

        // array of columns to fetch
        val columns = arrayOf(COLUMN_USER_ID)
        val db = this.readableDatabase

        // selection criteria
        val selection = "$COLUMN_USER_NAME = ?"

        // selection argument
        val selectionArgs = arrayOf(name)

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE name = 'jackAndroid';
         */
        val cursor = db.query(TABLE_USER, //Table to query
            columns,        //columns to return
            selection,      //columns for the WHERE clause
            selectionArgs,  //The values for the WHERE clause
            null,  //group the rows
            null,   //filter by row groups
            null)  //The sort order

        val cursorCount = cursor.count
        cursor.close()
        db.close()

        if (cursorCount > 0) {
            return true
        }

        return false
    }

    /**
     * This method to check user exist or not
     *
     * @param name
     * @param password
     * @return true/false
     */
    fun userExists(name: String, password: String): Boolean {
        // array of columns to fetch
        val columns = arrayOf(COLUMN_USER_NAME)
        val db = this.readableDatabase

        // selection criteria
        val selection = "$COLUMN_USER_NAME = ? AND $COLUMN_USER_PASSWORD = ?"

        // selection arguments
        val selectionArgs = arrayOf(name, password)

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        val cursor = db.query(TABLE_USER, //Table to query
            columns, //columns to return
            selection, //columns for the WHERE clause
            selectionArgs, //The values for the WHERE clause
            null,  //group the rows
            null, //filter by row groups
            null) //The sort order

        val cursorCount = cursor.count
        cursor.close()
        db.close()

        if (cursorCount > 0)
            return true

        return false
    }

    /**
     * This method return score for given user name
     * @param name - userName in DB
     */
    fun getUserScore(name: String) : Int {
        val columns = arrayOf(COLUMN_USER_SCORE)
        val db = this.readableDatabase

        val selection = "$COLUMN_USER_NAME = ?"
        val selectionArgs = arrayOf(name)

        val cursor = db.query(TABLE_USER,
            columns,
            selection,
            selectionArgs,
            null,
            null,
            null)

        var userScore = -1
        if (cursor.moveToFirst()) {
            userScore = cursor.getString(cursor.getColumnIndex(COLUMN_USER_SCORE)).toInt()
        }
        cursor.close()
        db.close()

        return userScore
    }

    /**
     * This method updates user score with current global score
     * @param name - user name in DB
     * @param newScore - current global user score
     */
    fun updateUserScore(name: String, newScore: Int) {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(COLUMN_USER_SCORE, newScore)

        db.update(TABLE_USER, values, "$COLUMN_USER_NAME = ?",
            arrayOf(name))

        db.close()
    }

    /**
     * This method to update user record
     * @param user
     */
    fun updateUser(user: User) {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(COLUMN_USER_NAME, user.name)
        values.put(COLUMN_USER_PASSWORD, user.password)
        values.put(COLUMN_USER_SCORE, user.score)

        // updating row
        db.update(TABLE_USER, values, "$COLUMN_USER_ID = ?",
            arrayOf(user.id.toString()))
        db.close()
    }

    /**
     * This method is to delete user record
     * @param user
     */
    fun deleteUser(user: User) {
        val db = this.writableDatabase
        // delete user record by id
        db.delete(TABLE_USER, "$COLUMN_USER_ID = ?",
            arrayOf(user.id.toString()))
        db.close()
    }

    /**
     * This method is to fetch all user and return the list of user records
     * @return list
     */
    fun getAllUser(): List<User> {
        // array of columns to fetch
        val columns = arrayOf(COLUMN_USER_ID, COLUMN_USER_NAME, COLUMN_USER_PASSWORD, COLUMN_USER_SCORE)

        // sorting orders
        val sortOrder = "$COLUMN_USER_NAME ASC"
        val userList = ArrayList<User>()
        val db = this.readableDatabase

        // query the user table
        val cursor = db.query(TABLE_USER,
            columns,            //columns to return
            null,     //columns for the WHERE clause
            null,  //The values for the WHERE clause
            null,      //group the rows
            null,       //filter by row groups
            sortOrder)         //The sort order
        if (cursor.moveToFirst()) {
            do {
                val user = User(
                    id = cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID)).toInt(),
                    name = cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)),
                    password = cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)),
                    score = cursor.getString(cursor.getColumnIndex(COLUMN_USER_SCORE)).toInt()
                )
                userList.add(user)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return userList
    }

    companion object {
        // Database Version
        private val DATABASE_VERSION = 3

        // Database Name
        private val DATABASE_NAME = "UserManager.db"

        // User table name
        private val TABLE_USER = "user"

        // User Table Columns names
        private val COLUMN_USER_ID = "user_id"
        private val COLUMN_USER_NAME = "user_name"
        private val COLUMN_USER_PASSWORD = "user_password"
        private val COLUMN_USER_SCORE = "user_score"
    }

}