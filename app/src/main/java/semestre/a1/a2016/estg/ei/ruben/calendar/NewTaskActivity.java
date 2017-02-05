package semestre.a1.a2016.estg.ei.ruben.calendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import semestre.a1.a2016.estg.ei.ruben.calendar.model.Date;
import semestre.a1.a2016.estg.ei.ruben.calendar.model.Task;
import semestre.a1.a2016.estg.ei.ruben.calendar.model.TaskController;

public class NewTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        TaskController.INSTANCIA.setContext(this);
    }

    public void saveTask(View view) {
        Task task = new Task();

        EditText name=(EditText) findViewById(R.id.editTextName);
        EditText dateTask=(EditText) findViewById(R.id.editTextDate);
        EditText description=(EditText) findViewById(R.id.editTextDescription);

        Date date = new Date();
        date=date.parseDate(dateTask.getText().toString());
        Log.d("date", dateTask.getText().toString());

        long userId=1;
        Integer complete=0;

        task.setUserId(userId);
        task.setName(name.getText().toString());
        task.setDate(date);
        task.setDescription(description.getText().toString());
        task.setComplete(complete);

        TaskController.INSTANCIA.add(task);

        Toast.makeText(this, "Task created.",
                Toast.LENGTH_LONG).show();
    }
}
