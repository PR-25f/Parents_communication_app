package com.example.knowme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AdminDashboardActivity extends AppCompatActivity {
    private TextView tvAddParent,tvAddTeacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        tvAddTeacher=findViewById(R.id.tvAddTeacher);
        tvAddTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), AddTeacherActivity.class);
                startActivity(i);
            }
        });

        tvAddParent=findViewById(R.id.tvAddParent);
        tvAddParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), AddStudentActivity.class);
                startActivity(i);
            }
        });
    }
}