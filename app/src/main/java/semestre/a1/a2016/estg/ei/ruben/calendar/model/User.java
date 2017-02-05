package semestre.a1.a2016.estg.ei.ruben.calendar.model;

/**
 * Created by Ruben on 10/01/2017.
 */

public class User {
    public long id;
    public String username;
    public String password;

    public User(long id, String username, String password){
        this.id=id;
        this.username=username;
        this.password=password;
    }

    public User() { this(-1, "", ""); }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
