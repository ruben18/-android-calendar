package semestre.a1.a2016.estg.ei.ruben.calendar.model;

/**
 * Created by Ruben on 05-02-2017.
 */

public class Task {
    public long id;
    public long userId;
    public String name;
    public Date date;
    public String description;
    public Integer complete;

    public Task(long id, long userId, String name, Date date, String description, Integer complete) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.date = date;
        this.description= description;
        this.complete=complete;
    }

    public Task() { this(-1,-1,"", null,"", -1); }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getComplete() {
        return complete;
    }

    public void setComplete(Integer complete) {
        this.complete = complete;
    }
}
