package com.cruz.sugarapplication.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cruz.sugarapplication.Model.User;
import com.cruz.sugarapplication.R;
import com.cruz.sugarapplication.Repository.UserRepository;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private EditText usernameInput;
    private EditText passwordInput;

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int REGISTER_FORM_REQUEST = 100;

    //private RecyclerView usersList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameInput = (EditText)findViewById(R.id.lg_user);
        passwordInput = (EditText)findViewById(R.id.lg_password);

        // init SharedPreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // username remember
       /* String username = sharedPreferences.getString("username", null);
        if(username != null){
            usernameInput.setText(username);
            passwordInput.requestFocus();
        }*/

        // islogged remember
        if(sharedPreferences.getBoolean("islogged", false)){
            // Go to Dashboard
            goDashboard();
        }

    }



    public void goLogin(View view){

        String email = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();

        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Tienes que completar todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Login logic
        User user = UserRepository.login(email, password);

        if(user == null){
            Intent intn = new Intent(MainActivity.this, RegisterActivity.class);
            //Envías el usuario hacia el menu


            String usrr=email;
            String pass=password;

            intn.putExtra("IngresarUss", usrr);
            intn.putExtra("IngresaPass",pass);
            startActivity(intn);

            Toast.makeText(this, "Creando usuario,nuevo", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "Welcome " + user.getFullname(), Toast.LENGTH_SHORT).show();

        // Save to SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean success = editor
                .putString("username", user.getFullname())
                .putString("password", user.getPassword())
                .putString("email", user.getEmail())
                .putBoolean("islogged", true)
                .commit();

        // Go to Dashboard
        goDashboard();

    }

    private void goDashboard(){
        startActivity(new Intent(this, MenuActivity.class));
        finish();
    }

}
