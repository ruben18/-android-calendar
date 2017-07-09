package semestre.a1.a2016.estg.ei.ruben.calendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import semestre.a1.a2016.estg.ei.ruben.calendar.model.User;
import semestre.a1.a2016.estg.ei.ruben.calendar.model.UserController;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Database db = new Database(this);

        UserController.INSTANCIA.setContext(this);
    }

    public void registerActivity(View view) {
        Intent intent = new Intent(HomeActivity.this, RegisterActivity.class);
        HomeActivity.this.startActivity(intent);
    }

    public void login(View view) {
        EditText username = (EditText) findViewById(R.id.editTextUsername);
        EditText password= (EditText) findViewById(R.id.editTextPassword);

        User user= UserController.INSTANCIA.getUserByUsername(username.getText().toString());

        //if(user!=null && user.getPassword().equals(password.getText().toString())){
            Intent intent=new Intent(HomeActivity.this, CalendarActivity.class);
            Bundle extras=new Bundle();
            //extras.putString("USERNAME", username.getText().toString());
            extras.putString("USERNAME", "ruben18");

            intent.putExtras(extras);
            startActivity(intent);
        /*}else{
            Toast.makeText(this, "Credentials dont match, try again!",
                    Toast.LENGTH_LONG).show();
        }*/
    }
}
