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

public class AddStudentActivity extends AppCompatActivity {
    private EditText etParentMobile,etStudentName,etStudentReg,etStudentCls;
    private Button btnAddStudent;

    // creating a variable for our
    // Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference,ref,cref;

    // creating a variable for
    // our object class
    StudentModel student;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        initialise();


        // below line is used to get the
        // instance of our FIrebase database.
        firebaseDatabase = FirebaseDatabase.getInstance();

        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("Student");

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=etStudentName.getText().toString().trim();
                String mobile=etParentMobile.getText().toString().trim();
                String cls=etStudentCls.getText().toString().trim();
                String reg=etStudentReg.getText().toString().trim();
                String type="Student";
                if (name.matches("")||mobile.matches("")||cls.matches("")||reg.matches("")||type.matches("")){
                    Toast.makeText(AddStudentActivity.this, "Values Missing", Toast.LENGTH_SHORT).show();
                }else{
                    student=new StudentModel(name,mobile,reg,cls,type);
                    ref=databaseReference.child(mobile);
                    cref=ref.child(reg);
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                cref.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (!snapshot.exists()){
                                            cref.setValue(student);
                                            Intent i=new Intent(getApplicationContext(),AdminDashboardActivity.class);
                                            startActivity(i);
                                            finish();
                                        }else{
                                            Toast.makeText(AddStudentActivity.this, "Student Already Exists", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }else{
                                cref.setValue(student);
                                Intent i=new Intent(getApplicationContext(),AdminDashboardActivity.class);
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
        etParentMobile=findViewById(R.id.etParentMobile);
        etStudentName=findViewById(R.id.etStudentName);
        etStudentReg=findViewById(R.id.etStudentReg);
        etStudentCls=findViewById(R.id.etStudentCls);
        btnAddStudent=findViewById(R.id.btnAddStudent);
    }
}