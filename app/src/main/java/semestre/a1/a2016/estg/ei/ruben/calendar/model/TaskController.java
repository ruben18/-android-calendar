package semestre.a1.a2016.estg.ei.ruben.calendar.model;

import android.content.Context;

import java.util.List;

import semestre.a1.a2016.estg.ei.ruben.calendar.Database;

/**
 * Created by Ruben on 05-02-2017.
 */

public class TaskController {
    public static TaskController INSTANCIA = new
            TaskController();

    private Database database;

    public void setContext(Context ctx) { database=new Database(ctx); }

    public void add(Task task){ database.addTask(task); }

    public List<Task> getTasksByUserId(long userID){
        return database.getTasksByUserId(userID);
    }

    public Task getTaskById(long id) { return database.getTaskById(id);    }

    public void updateTask(Task newTask, long id) {
        newTask.setId(id);
        database.updateTask(newTask);
    }
}
