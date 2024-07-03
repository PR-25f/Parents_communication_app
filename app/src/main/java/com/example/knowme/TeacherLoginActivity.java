package com.example.knowme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TeacherLoginActivity extends AppCompatActivity {
    private EditText etTeacherUname,etTeacherUpass;
    private Button btnTeacherLogin;

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
        setContentView(R.layout.activity_teacher_login);
        initialise();


        // below line is used to get the
        // instance of our FIrebase database.
        firebaseDatabase = FirebaseDatabase.getInstance();

        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("Teacher");

        btnTeacherLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname=etTeacherUname.getText().toString().trim();
                String upass=etTeacherUpass.getText().toString().trim();
                if (uname.matches("")||upass.matches("")){
                    Toast.makeText(TeacherLoginActivity.this, "Credentials Missing", Toast.LENGTH_SHORT).show();
                }else{
                    ref=databaseReference.child(uname);
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                Toast.makeText(TeacherLoginActivity.this, "Checked", Toast.LENGTH_SHORT).show();
                                teacher=snapshot.getValue(TeacherModel.class);
                                String pwd=teacher.getEmpno();
                                if(pwd.equals(upass)){
//                                    Toast.makeText(TeacherLoginActivity.this, teacher.getCls(), Toast.LENGTH_SHORT).show();
                                    SharedPrefManager sharedPrefManager=new SharedPrefManager(getApplicationContext());
                                    sharedPrefManager.userLogin(teacher.getName(),teacher.getMobile(),teacher.getEmpno(),teacher.getCls(),teacher.getType());
                                    Intent i =new Intent(getApplicationContext(), pop_activity.class);
                                    i.putExtra("name",teacher.getName());
                                    i.putExtra("type","teacher");
                                    startActivity(i);
                                    finish();
                                     }else{
                                    Toast.makeText(TeacherLoginActivity.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
                                }
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
        etTeacherUname=findViewById(R.id.etTeacherUname);
        etTeacherUpass=findViewById(R.id.etTeacherUpass);
        btnTeacherLogin=findViewById(R.id.btnTeacherLogin);
        SharedPrefManager sharedPrefManager=new SharedPrefManager(getApplicationContext());
        if(sharedPrefManager.isLoggedIn()){
            Intent i =new Intent(getApplicationContext(), TeacherDashboardActivity.class);
            startActivity(i);
            finish();
        }
    }
}