package semestre.a1.a2016.estg.ei.ruben.calendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import semestre.a1.a2016.estg.ei.ruben.calendar.model.User;
import semestre.a1.a2016.estg.ei.ruben.calendar.model.UserController;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        UserController.INSTANCIA.setContext(this);

    }

    public void createUser(View view) {
        User user =new User();
        EditText username = (EditText) findViewById(R.id.editTextUsername);
        EditText password = (EditText) findViewById(R.id.editTextPassword);
        EditText confirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);
        if(username.length()==0) {
            Toast.makeText(this, "Insert name.",
                    Toast.LENGTH_LONG).show();
        }else {
            if (password.getText().toString().equals(confirmPassword.getText().toString())) {
                user.setUsername(username.getText().toString());
                user.setPassword(password.getText().toString());

                UserController.INSTANCIA.add(user);
                Toast.makeText(this, "User created.",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Passwords dont match.",
                        Toast.LENGTH_LONG).show();
            }
        }


    }
}
