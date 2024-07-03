package com.example.knowme.Teacher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.knowme.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GetSemActivity extends AppCompatActivity {

    private EditText etTsem,etTcls,etTreg;
    private Button btnMNext;

    private LinearLayout llSem2,llSem,llSem3;

    private String regno,cls,sem;
    private ArrayList<SubjectModel> slist;
    private ListView lvStudent;

    DatabaseReference reference,ref;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_sem);

        initialise();
        etTreg.setText(regno);
        etTcls.setText(cls);

        firebaseDatabase=FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference("Subjects");

        slist=new ArrayList<>();
        btnMNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llSem.setVisibility(View.GONE);
                llSem2.setVisibility(View.VISIBLE);
                sem=etTsem.getText().toString().trim();
                ref=reference.child(cls).child(sem);

                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            for (DataSnapshot s:snapshot.getChildren()){
                                SubjectModel subjectModel=s.getValue(SubjectModel.class);
                                slist.add(subjectModel);
                            }
                            SubjectViewAdapter studentViewAdapter=new SubjectViewAdapter(getApplicationContext(),slist,regno);
                            lvStudent=findViewById(R.id.lvSubject);
                            lvStudent.setAdapter(studentViewAdapter);
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
        regno=getIntent().getStringExtra("regno");
        cls=getIntent().getStringExtra("cls");
        etTreg=findViewById(R.id.etTreg);
        etTcls=findViewById(R.id.etTcls);
        etTsem=findViewById(R.id.etTsem);
        llSem2=findViewById(R.id.llSem2);
        llSem3=findViewById(R.id.llSem3);
        llSem=findViewById(R.id.llSem);
        btnMNext=findViewById(R.id.btnMNext);
    }
}