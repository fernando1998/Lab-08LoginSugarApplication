package com.cruz.sugarapplication.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cruz.sugarapplication.Model.User;
import com.cruz.sugarapplication.R;
import com.cruz.sugarapplication.Repository.UserRepository;

/**
 * Created by FERNANDO on 16/10/2017.
 */

public class RegisterActivity extends AppCompatActivity {


    private EditText fullnameInput;
    private EditText emailInput;
    private EditText passwordInput;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullnameInput = (EditText)findViewById(R.id.fullname_input);
        emailInput = (EditText)findViewById(R.id.email_input);
        passwordInput = (EditText)findViewById(R.id.password_input);

        // init SharedPreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String username = this.getIntent().getExtras().getString("IngresarUss");
        String password = this.getIntent().getExtras().getString("IngresaPass");

        emailInput.setText(username);
        passwordInput.setText(password);


    }

    public void callRegister(View view){
        String fullname = fullnameInput.getText().toString();
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();

        if(fullname.isEmpty() || email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Debe completar todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        UserRepository.create(fullname, email, password);

        goDashboard();


    }

    private void goDashboard(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}


