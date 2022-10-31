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

public class WelcomeActivity extends AppCompatActivity {
    Button con;
    MyDBHandler db = new MyDBHandler(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        con = (Button) findViewById(R.id.con);

        Intent i = getIntent();
        Bundle values = i.getExtras();
        String user = i.getStringExtra("KEY1");
        //line that causes error
        //String account_type = values.getString("KEY2");

        TextView t = (TextView) findViewById(R.id.welcome);
        t.setText("Welcome " + user + "!, you are logged in as instructor" );
        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), Enrol.class);

                startActivity(myIntent);

            }
        });


    }
}

