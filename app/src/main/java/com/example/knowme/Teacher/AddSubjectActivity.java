package com.example.knowme.Teacher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.knowme.R;
import com.example.knowme.TeacherDashboardActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddSubjectActivity extends AppCompatActivity {
    private EditText etSubname,etSubcode,etSmobile,etTmark,etSubsem,etSubcls;
    private Button btnAddSub;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference,ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        initialise();
        firebaseDatabase=FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference("Subjects");

        btnAddSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sname=etSubname.getText().toString().trim();
                String scode=etSubcode.getText().toString().trim();
                String smobile=etSmobile.getText().toString().trim();
                String stmark=etTmark.getText().toString().trim();
                String sem=etSubsem.getText().toString().trim();
                String cls=etSubcls.getText().toString().trim();
                if(sname.matches("")||scode.matches("")||smobile.matches("")
                        ||stmark.matches("")||sem.matches("")||cls.matches("")){
                    Toast.makeText(AddSubjectActivity.this, "Enter All Field", Toast.LENGTH_SHORT).show();
                }else{
                    ref=reference.child(cls).child(sem).child(scode);
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(!snapshot.exists()){
                                SubjectModel subjectModel=new SubjectModel(scode,sname,smobile,stmark,sem,cls);
                                ref.setValue(subjectModel);
                                Intent i=new Intent(getApplicationContext(), TeacherDashboardActivity.class);
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
        etSubname=findViewById(R.id.etSubname);
        etSubcode=findViewById(R.id.etSubcode);
        etSmobile=findViewById(R.id.etSmobile);
        etTmark=findViewById(R.id.etTmark);
        etSubsem=findViewById(R.id.etSubsem);
        etSubcls=findViewById(R.id.etSubcls);
        btnAddSub=findViewById(R.id.btnAddSub);
    }
}