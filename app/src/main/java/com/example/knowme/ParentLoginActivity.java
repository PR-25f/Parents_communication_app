package com.example.knowme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.knowme.Parent.ParentDashboardActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ParentLoginActivity extends AppCompatActivity {
    private Button btnParentLogin;
    private EditText etParentUname,etParentUpass;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,ref;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_login);
        initialise();

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Student");

        btnParentLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mob=etParentUname.getText().toString().trim();
                String regno=etParentUpass.getText().toString().trim();
                if(mob.matches("")||regno.matches("")){
                    Toast.makeText(ParentLoginActivity.this, "Enter All Credentials", Toast.LENGTH_SHORT).show();
                }else{
                    ref=databaseReference.child(mob).child(regno);
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                StudentModel studentModel=snapshot.getValue(StudentModel.class);
                                sharedPrefManager.userLogin(studentModel.getName(), studentModel.getMobile(), studentModel.getReg(), studentModel.getCls(),studentModel.getType());

                                Intent i =new Intent(getApplicationContext(), pop_activity.class);
                                i.putExtra("name",studentModel.getName());
                                i.putExtra("type","parent");startActivity(i);
                                finish();
                            }else{
                                Toast.makeText(ParentLoginActivity.this, "User Not Exist", Toast.LENGTH_SHORT).show();
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
        btnParentLogin=findViewById(R.id.btnParentLogin);
        etParentUname=findViewById(R.id.etParentUname);
        etParentUpass=findViewById(R.id.etParentUpass);
        sharedPrefManager=new SharedPrefManager(getApplicationContext());
        if(sharedPrefManager.isLoggedIn()){
            Intent i=new Intent(getApplicationContext(), ParentDashboardActivity.class);
            startActivity(i);
            finish();
        }
    }
}