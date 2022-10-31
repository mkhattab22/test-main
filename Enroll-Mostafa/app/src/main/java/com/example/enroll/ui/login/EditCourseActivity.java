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

public class EditCourseActivity extends AppCompatActivity {

    EditText og_course_id, og_course_name, new_course_id, new_course_name;
    Button edit_course;
    MyDBHandler db = new MyDBHandler(this);

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        og_course_id = (EditText) findViewById(R.id.original_course_id);
        og_course_name = (EditText) findViewById(R.id.original_course_name);
        new_course_id = (EditText) findViewById(R.id.new_course_id);
        new_course_name = (EditText) findViewById(R.id.new_course_name);

        edit_course = (Button) findViewById(R.id.edit_course);

        edit_course.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                String id1 = og_course_id.getText().toString();
                String name1 = og_course_name.getText().toString();

                String id2 = new_course_id.getText().toString();
                String name2 = new_course_name.getText().toString();

                if((id1.equals("")) || ( name1.equals("")) || (id2.equals("")) || ( name2.equals(""))){
                    Toast.makeText(EditCourseActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else{
                    boolean checkSuccess = db.editCourse(id1, name1, id2, name2);

                    if(checkSuccess){
                        Toast.makeText(EditCourseActivity.this, "Course edit successful!", Toast.LENGTH_SHORT).show();

                    } else{
                        Toast.makeText(EditCourseActivity.this, "Course edit unsuccessful.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}
