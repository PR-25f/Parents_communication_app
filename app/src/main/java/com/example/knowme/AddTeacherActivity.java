package com.example.knowme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddTeacherActivity extends AppCompatActivity {
    private EditText etTeacherMobile,etTeacherName,etTeacherEmp,etTeacherCls;
    private Button btnAddTeacher;

    // creating a variable for our
    // Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference,ref;

    // creating a variable for
    // our object class
    TeacherModel teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);

        initialise();
        // below line is used to get the
        // instance of our FIrebase database.
        firebaseDatabase = FirebaseDatabase.getInstance();

        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("Teacher");

        btnAddTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=etTeacherName.getText().toString().trim();
                String mobile=etTeacherMobile.getText().toString().trim();
                String cls=etTeacherCls.getText().toString().trim();
                String emp=etTeacherEmp.getText().toString().trim();
                String type="Teacher";
                if(name.matches("")||mobile.matches("")||cls.matches("")||emp.matches("")||type.matches("")){
                    Toast.makeText(AddTeacherActivity.this, "Fill Out all boxes", Toast.LENGTH_SHORT).show();
                }else{
                    teacher=new TeacherModel(mobile,name,emp,cls,type);
                    ref=databaseReference.child(mobile);
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(!snapshot.exists()){
                                ref.setValue(teacher);
                                Intent i=new Intent(getApplicationContext(), AdminDashboardActivity.class);
                                startActivity(i);
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

    }

    private void initialise() {
        etTeacherMobile=findViewById(R.id.etTeacherMobile);
        etTeacherName=findViewById(R.id.etTeacherName);
        etTeacherEmp=findViewById(R.id.etTeacherEmp);
        etTeacherCls=findViewById(R.id.etTeacherCls);
        btnAddTeacher=findViewById(R.id.btnAddTeacher);
    }
}