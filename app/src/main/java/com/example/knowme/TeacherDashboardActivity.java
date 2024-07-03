package com.example.knowme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.knowme.Teacher.AddAnnouncementsActivity;
import com.example.knowme.Teacher.AddAttendanceActivity;
import com.example.knowme.Teacher.AddFeeActivity;
import com.example.knowme.Teacher.AddMarksActivity;
import com.example.knowme.Teacher.AddSubjectActivity;

public class TeacherDashboardActivity extends AppCompatActivity {
    private TextView tvAddAnnouncements,tvAddAttendance,tvAddFee,tvAddsem,tvAddIAMarks,tvTlogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);
        initialise();

        tvAddAnnouncements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), AddAnnouncementsActivity.class);
                startActivity(i);
            }
        });

        tvAddAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getApplicationContext(), AddAttendanceActivity.class);
                startActivity(i);
            }
        });

        tvAddFee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getApplicationContext(), AddFeeActivity.class);
                startActivity(i);
            }
        });

        tvAddsem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getApplicationContext(), AddSubjectActivity.class);
                startActivity(i);
            }
        });

        tvAddIAMarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getApplicationContext(), AddMarksActivity.class);
                startActivity(i);
            }
        });

        tvTlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefManager sharedPrefManager=new SharedPrefManager(getApplicationContext());
                sharedPrefManager.logout();
                Intent i =new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void initialise() {
        tvAddAnnouncements=findViewById(R.id.tvAddAnnouncements);
        tvAddAttendance=findViewById(R.id.tvAddAttendance);
        tvAddFee=findViewById(R.id.tvAddFee);
        tvAddsem=findViewById(R.id.tvAddsem);
        tvAddIAMarks=findViewById(R.id.tvAddIAMarks);
        tvTlogout=findViewById(R.id.tvTlogout);
    }
}