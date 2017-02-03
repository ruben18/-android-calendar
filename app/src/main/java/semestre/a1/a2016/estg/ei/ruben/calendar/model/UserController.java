package semestre.a1.a2016.estg.ei.ruben.calendar.model;

import android.content.Context;

import semestre.a1.a2016.estg.ei.ruben.calendar.Database;

/**
 * Created by Ruben on 19/01/2017.
 */

public class UserController {
    public static UserController INSTANCIA = new
            UserController();

    private Database database;

    public void setContext(Context ctx) { database = new Database(ctx); }

    public void add(User user){ database.addUser(user); }

    public void update(User newUser, long id){
        newUser.setId(id);
        database.updateUser(newUser);
    }

    public void delete(long id){ database.deleteUser(id); }
}
