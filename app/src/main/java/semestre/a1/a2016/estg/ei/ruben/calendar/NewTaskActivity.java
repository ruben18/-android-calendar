package semestre.a1.a2016.estg.ei.ruben.calendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
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

        if(getIntent().hasExtra("PARAM_TASK")){
            Task task=TaskController.INSTANCIA.getTaskById(
                    getIntent().getLongExtra("PARAM_TASK",0));

            EditText name=(EditText) findViewById(R.id.editTextName);
            EditText date=(EditText) findViewById(R.id.editTextDate);
            EditText description=(EditText) findViewById(R.id.editTextDescription);
            CheckBox complete=(CheckBox) findViewById(R.id.checkBoxComplete);
            complete.setEnabled(true);

            name.setText(task.getName());
            date.setText(task.getDate().toString());
            description.setText(task.getDescription());

            if(task.getComplete()==0){
                complete.setChecked(false);
            }else{
                complete.setChecked(true);
            }
        }
    }

    public void saveTask(View view) {
        Task task = new Task();

        EditText name=(EditText) findViewById(R.id.editTextName);
        EditText dateTask=(EditText) findViewById(R.id.editTextDate);
        EditText description=(EditText) findViewById(R.id.editTextDescription);
        CheckBox complete=(CheckBox) findViewById(R.id.checkBoxComplete);

        Date date = new Date();
        date=date.parseDate(dateTask.getText().toString());
        Log.d("date", dateTask.getText().toString());

        long userId=1;
        int completeValue;

        if(complete.isChecked()){
            completeValue=1;
        }else{
            completeValue=0;
        }
        task.setUserId(userId);
        task.setName(name.getText().toString());
        task.setDate(date);
        task.setDescription(description.getText().toString());
        task.setComplete(completeValue);

        if(getIntent().hasExtra("PARAM_TASK")){
            TaskController.INSTANCIA.updateTask(task, getIntent().getLongExtra("PARAM_TASK",0));
            Toast.makeText(this, "Task saved.",
                    Toast.LENGTH_LONG).show();
        }else {
            TaskController.INSTANCIA.add(task);
            Toast.makeText(this, "Task created.",
                    Toast.LENGTH_LONG).show();
        }
        setResult(RESULT_OK);

        finish();

    }
}
