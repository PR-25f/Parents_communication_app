package com.example.knowme.Parent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.knowme.MainActivity;
import com.example.knowme.R;
import com.example.knowme.SharedPrefManager;
import com.example.knowme.Teacher.SubjectModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ParentSemesterActivity extends AppCompatActivity {

    private TextView tvCGPA;
    private EditText etSemesterNo;
    private Button btnMarkEnter;
    private ScrollView svMarks;
    private ListView lvMark;

    ArrayList<ExamModel> elist;

    FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseReference,ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_semester);
        initialise();

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigationView3);
        SharedPrefManager sharedPrefManager=new SharedPrefManager(getApplicationContext());
        elist=new ArrayList<>();
        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.sem);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id=item.getItemId();
                if(id==R.id.home){
                    Intent i=new Intent(getApplicationContext(), ParentDashboardActivity.class);
                    startActivity(i);
                    finish();
                }else if(id==R.id.attendance){
                    Intent i=new Intent(getApplicationContext(), ParentAttendanceActivity.class);
                    startActivity(i);
                    finish();
                }else if(id==R.id.fees){
                    Intent i=new Intent(getApplicationContext(), ParentFeesActivity.class);
                    startActivity(i);
                    finish();
                }else if(id==R.id.sem){
                    Intent i=new Intent(getApplicationContext(), ParentSemesterActivity.class);
                    startActivity(i);
                    finish();
                }else if(id==R.id.logout){
                    sharedPrefManager.logout();
                    Intent i=new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    finish();
                }

                return false;
            }
        });

        btnMarkEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnMarkEnter.setVisibility(View.GONE);
                svMarks.setVisibility(View.VISIBLE);
                tvCGPA.setVisibility(View.VISIBLE);
                etSemesterNo.setVisibility(View.GONE);
                String sem=etSemesterNo.getText().toString().trim();
                firebaseDatabase=FirebaseDatabase.getInstance();
                databaseReference=firebaseDatabase.getReference("Details").child(sharedPrefManager.getRmobile()).child("Mark").child(sem);
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            for (DataSnapshot d:snapshot.getChildren()){
                                String scode=d.getKey();
                                String mark=d.getValue(String.class);
                                ref=firebaseDatabase.getReference("Subjects").child(sharedPrefManager.getCls()).child(sem).child(scode);
                                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if(snapshot.exists()){
                                            SubjectModel subjectModel=snapshot.getValue(SubjectModel.class);
                                            ExamModel examModel=new ExamModel(subjectModel.getCode(),subjectModel.getName(),mark,subjectModel.getTotal(),subjectModel.getSname());
                                            elist.add(examModel);
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }

                            ExamAdapter studentViewAdapter=new ExamAdapter(getApplicationContext(),elist);

                            lvMark.setAdapter(studentViewAdapter);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }

    private void initialise() {
        tvCGPA=findViewById(R.id.tvCGPA);
        etSemesterNo=findViewById(R.id.etSemesterNo);
        btnMarkEnter=findViewById(R.id.btnMarkEnter);
        svMarks=findViewById(R.id.svMarks);
        lvMark=findViewById(R.id.lvMark);
    }
}