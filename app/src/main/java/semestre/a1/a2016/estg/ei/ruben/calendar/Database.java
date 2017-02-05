package semestre.a1.a2016.estg.ei.ruben.calendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

import semestre.a1.a2016.estg.ei.ruben.calendar.model.Date;
import semestre.a1.a2016.estg.ei.ruben.calendar.model.Task;
import semestre.a1.a2016.estg.ei.ruben.calendar.model.User;

/**
 * Created by Ruben on 10/01/2017.
 */

public class Database extends SQLiteOpenHelper {
    private static final String DB_NAME="Calendar.db";
    private static final int DB_VERSION=8;

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

    private static class TasksTable{
        //Table name
        public static final String TABLE_NAME="Tasks";

        //Table columns
        public static final String ID="_id";
        public static final String USERID="userId";
        public static final String NAME="name";
        public static final String DATE="date";
        public static final String DESCRIPTION="description";
        public static final String COMPLETE="complete";

        //Variable to get all columns
        public static final String[] ALL={ID, USERID, NAME, DATE, DESCRIPTION, COMPLETE};

        //Variable with the command to create the table
        public static final String CREATE_COMMAND="create table "+TABLE_NAME+" ("+
                ID+" integer PRIMARY KEY AUTOINCREMENT NOT NULL, "+
                USERID+" integer, "+
                NAME+" VARCHAR, "+
                DATE+" VARCHAR, "+
                DESCRIPTION +" VARCHAR, "+
                COMPLETE+" VARCHAR )";

        //Variable with the commando to delete de table
        public static final String DELETE_COMMAND="DROP TABLE "+TABLE_NAME;
    }

    //CREATE DATABASE
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UsersTable.CREATE_COMMAND);
        Log.d("DATABASE", "Users table created.");
        db.execSQL(TasksTable.CREATE_COMMAND);
        Log.d("DATABASE", "Taks table created.");
    }

    //DELETE TABLES AND CALL ONCREATE METHOD TO CREATE TABLES
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(UsersTable.DELETE_COMMAND);
        db.execSQL(TasksTable.DELETE_COMMAND);
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

    public User getUserByUsername(String username) {
        SQLiteDatabase db = getReadableDatabase();
        User user=null;
        Cursor res = db.query(UsersTable.TABLE_NAME, UsersTable.ALL,
                UsersTable.USERNAME+"=?", new String[]{username}, null, null, null);
        if(res.moveToFirst()){
            user=convertLinesCursorToUser(res);
        }

        res.close();
        db.close();

        return user;
    }

    private User convertLinesCursorToUser(Cursor res) {
        return new User(res.getLong(res.getColumnIndex(UsersTable.ID)),
            res.getString(res.getColumnIndex(UsersTable.USERNAME)),
            res.getString(res.getColumnIndex(UsersTable.PASSWORD)));
    }

    public int deleteUser(long id){
        SQLiteDatabase db = getWritableDatabase();
        int rows = db.delete(UsersTable.TABLE_NAME, UsersTable.ID + "=?",
                new String[]{Long.toString(id)});
        db.close();

        return rows;
    }

    public long addTask(Task task) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TasksTable.USERID, task.getUserId());
        cv.put(TasksTable.NAME, task.getName());
        cv.put(TasksTable.DATE, task.getDate().toString());
        cv.put(TasksTable.DESCRIPTION, task.getDescription());
        cv.put(TasksTable.COMPLETE, task.getComplete());

        long id=db.insert(TasksTable.TABLE_NAME, null, cv);

        task.setId(id);

        db.close();

        return id;
    }

    public List<Task> getTasksByUserId(long userID) {
        SQLiteDatabase db =getReadableDatabase();

        Cursor res = db.query(TasksTable.TABLE_NAME, TasksTable.ALL,
                TasksTable.USERID + "=?", new String[]{Long.toString(userID)}, null, null, null);

        List<Task> result = convertCursorForTaskList(res);

        res.close();
        db.close();

        return result;

    }

    private List<Task> convertCursorForTaskList(Cursor res) {
        LinkedList<Task> tasks=new LinkedList<>();
        if(res.moveToFirst()){
            do{
                Task task=convertLinesCursorForTask(res);
                tasks.add(task);
            }while (res.moveToNext());
        }
        return tasks;
    }

    private Task convertLinesCursorForTask(Cursor res) {
        return new Task(res.getLong(res.getColumnIndex(TasksTable.ID)),
                res.getLong(res.getColumnIndex(TasksTable.USERID)),
                res.getString(res.getColumnIndex(TasksTable.NAME)),
                Date.parseDate(res.getString(res.getColumnIndex(TasksTable.DATE))),
                res.getString(res.getColumnIndex(TasksTable.DESCRIPTION)),
                res.getInt(res.getColumnIndex(TasksTable.COMPLETE)));
    }
}
