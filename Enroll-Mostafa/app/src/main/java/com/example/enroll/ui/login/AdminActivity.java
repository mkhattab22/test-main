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


public class AdminActivity extends AppCompatActivity {

    MyDBHandler db = new MyDBHandler(this);
    EditText course_id, course_name, username;
    Button create_course, edit_course, delete_course, delete_account;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        course_id = (EditText) findViewById(R.id.course_id);
        course_name = (EditText) findViewById(R.id.course_name);
        username = (EditText) findViewById(R.id.username);

        create_course = (Button) findViewById(R.id.create_course);
        edit_course = (Button) findViewById(R.id.edit_course);
        delete_course = (Button) findViewById(R.id.delete_course);
        delete_account = (Button) findViewById(R.id.delete_account);

        create_course.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                String id = course_id.getText().toString();
                String name = course_name.getText().toString();

                if((id.equals("")) || ( name.equals(""))){
                    Toast.makeText(AdminActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else{
                    boolean checkSuccess = db.createCourse(id, name);

                    if(checkSuccess){
                        Toast.makeText(AdminActivity.this, "Course creation successful!", Toast.LENGTH_SHORT).show();

                    } else{
                        Toast.makeText(AdminActivity.this, "Course creation unsuccessful.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        edit_course.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent myIntent = new Intent(getApplicationContext(), EditCourseActivity.class);
                startActivity(myIntent);
            }
        });

        delete_course.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                String id = course_id.getText().toString();
                String name = course_name.getText().toString();

                if((id.equals("")) || ( name.equals(""))){
                    Toast.makeText(AdminActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else{
                    boolean checkSuccess = db.deleteCourse(id, name);

                    if(checkSuccess){
                        Toast.makeText(AdminActivity.this, "Course deletion successful!", Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(AdminActivity.this, "Course deletion unsuccessful.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        delete_account.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                String name = username.getText().toString();

                if(name.equals("")){
                    Toast.makeText(AdminActivity.this, "Please enter the required field.", Toast.LENGTH_SHORT).show();
                } else{
                    boolean checkSuccess = db.deleteAccount(name);

                    if(checkSuccess){
                        Toast.makeText(AdminActivity.this, "Account deletion successful!", Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(AdminActivity.this, "Account deletion unsuccessful.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}
