package com.example.knowme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvParent,tvTeacher,tvAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvAdmin=findViewById(R.id.tvAdmin);
        tvAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), AdminLoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        tvTeacher=findViewById(R.id.tvTeacher);
        tvTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), TeacherLoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        tvParent=findViewById(R.id.tvParent);
        tvParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), ParentLoginActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}