package semestre.a1.a2016.estg.ei.ruben.calendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Database db = new Database(this);

    }

    public void registerActivity(View view) {
        Intent intent = new Intent(HomeActivity.this, RegisterActivity.class);
        HomeActivity.this.startActivity(intent);
    }

    public void login(View view) {
        EditText username = (EditText) findViewById(R.id.editTextUsername);
        EditText password= (EditText) findViewById(R.id.editTextPassword);

        

    }
}
