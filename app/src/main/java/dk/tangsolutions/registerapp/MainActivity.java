package dk.tangsolutions.registerapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ConstraintLayout loginLayout;
    private EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        loadSharedPrefData();
    }


    private void loadSharedPrefData() {
        SharedPreferences preferences = getSharedPreferences(getString(R.string.shared_pref), Context.MODE_PRIVATE);
        String password = preferences.getString("password", null);
        String username = preferences.getString("username", null);
        if (username != null) {
            this.password.setText(password);
            this.username.setText(username);
        }

    }


    private void init() {
        this.loginLayout = findViewById(R.id.loginLayout);
        this.password = findViewById(R.id.password);
        this.username = findViewById(R.id.userName);
        loadSharedPrefData();

    }

    public void login(View view) {
        SharedPreferences preferences = getSharedPreferences(getString(R.string.shared_pref), Context.MODE_PRIVATE);
        String password = preferences.getString("password", null);
        String username = preferences.getString("username", null);
        if (this.username.getText().toString().equals(username) && this.password.getText().toString().equals(password)) {
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        }
    }

    public void register(View view) {
        SharedPreferences.Editor editor = getSharedPreferences(getString(R.string.shared_pref), MODE_PRIVATE).edit();
        editor.putString("username", this.username.getText().toString());
        editor.putString("password", this.password.getText().toString());
        editor.apply();
        Toast.makeText(this, "You have now been registered", Toast.LENGTH_LONG).show();
    }


}
