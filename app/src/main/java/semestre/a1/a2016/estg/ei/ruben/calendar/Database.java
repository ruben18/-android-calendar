package semestre.a1.a2016.estg.ei.ruben.calendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import semestre.a1.a2016.estg.ei.ruben.calendar.model.User;

/**
 * Created by Ruben on 10/01/2017.
 */

public class Database extends SQLiteOpenHelper {
    private static final String DB_NAME="Calendar.db";
    private static final int DB_VERSION=1;

    public Database(Context context){ super(context, DB_NAME, null, DB_VERSION); }


    private static class UsersTable{
        //Table name
        public static final String TABLE_NAME="Users";

        //Table columns
        public static final String ID="_id";
        public static final String USERNAME="username";
        public static final String PASSWORD="password";

        //Variable to get all columns
        public static final String[] ALL={ID, USERNAME, PASSWORD};

        //Variable with the command to create the table
        public static final String CREATE_COMMAND="create table "+TABLE_NAME+" ("+
                ID+" integer PRIMARY KEY AUTOINCREMENT NOT NULL, "+
                USERNAME+" VARCHAR, "+
                PASSWORD+" VARCHAR )";

        //Variable with the commando to delete de table
        public static final String DELETE_COMMAND="DROP TABLE "+TABLE_NAME;
    }

    //CREATE DATABASE
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UsersTable.CREATE_COMMAND);
        Log.d("DATABASE", "Users table created.");
    }

    //DELETE TABLES AND CALL ONCREATE METHOD TO CREATE TABLES
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(UsersTable.DELETE_COMMAND);
        onCreate(db);
    }

    public long addUser(User user){
        SQLiteDatabase db =getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(UsersTable.ID, user.getId());
        cv.put(UsersTable.USERNAME, user.getUsername());
        cv.put(UsersTable.PASSWORD, user.getPassword());

        long id=db.insert(UsersTable.TABLE_NAME, null, cv);

        user.setId(id);

        return id;
    }

    public int updateUser(User newUser){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(UsersTable.USERNAME, newUser.getUsername());
        cv.put(UsersTable.PASSWORD, newUser.getPassword());

        int countRows = db.update(UsersTable.TABLE_NAME, cv, UsersTable.ID + "=?",
                new String[]{Long.toString(newUser.getId())});

        return countRows;
    }

    public int deleteUser(long id){
        SQLiteDatabase db = getWritableDatabase();
        int rows = db.delete(UsersTable.TABLE_NAME, UsersTable.ID + "=?",
                new String[]{Long.toString(id)});
        db.close();

        return rows;
    }
}
