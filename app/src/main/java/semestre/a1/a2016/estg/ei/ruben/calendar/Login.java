package semestre.a1.a2016.estg.ei.ruben.calendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import semestre.a1.a2016.estg.ei.ruben.calendar.model.User;
import semestre.a1.a2016.estg.ei.ruben.calendar.model.UserController;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Database db = new Database(this);

        UserController.INSTANCIA.setContext(this);
    }

    public void registerActivity(View view) {
        Intent intent = new Intent(Login.this, Register.class);
        Login.this.startActivity(intent);
    }

    public void login(View view) {
        EditText username = (EditText) findViewById(R.id.editTextUsername);
        EditText password= (EditText) findViewById(R.id.editTextPassword);


    }
}
