package com.example.enroll.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.enroll.R;

public class LoginActivity extends AppCompatActivity {
    EditText username,password;
    Button login, register;
    MyDBHandler db = new MyDBHandler(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        register = (Button) findViewById(R.id.register);
        login = (Button) findViewById(R.id.login);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(myIntent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                Intent i = new Intent(LoginActivity.this, WelcomeActivity.class);
                String account_type1 = db.checkType(user);
                Bundle bundle = new Bundle();
                bundle.putString("KEY1",user);

                i.putExtra("KEY2",account_type1);
                i.putExtras(bundle);

                startActivity(i);

                if ((user.equals("")) || pass.equals("")) {
                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    boolean checkUser = db.checkUsernamePassword(user, pass);
                    if ((user.equals("admin") )&&(pass.equals("admin123"))){
                        Toast.makeText(LoginActivity.this, "Sign In Successful", Toast.LENGTH_SHORT).show();
                        Intent mIntent;
                        mIntent = new Intent(getApplicationContext(), AdminActivity.class);
                        startActivity(mIntent);
                    }
                    if (checkUser) {
                        Toast.makeText(LoginActivity.this, "Sign In Successful", Toast.LENGTH_SHORT).show();

                        String account_type = db.checkAccountType(user);

                        Intent myIntent;
                        myIntent = new Intent(getApplicationContext(), WelcomeActivity.class);

                        startActivity(myIntent);


                    } else {
                        Toast.makeText(LoginActivity.this, "Sign In Unsuccessful", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
    }
}



