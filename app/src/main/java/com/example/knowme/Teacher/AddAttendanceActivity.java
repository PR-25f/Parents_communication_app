package com.example.knowme.Teacher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import com.example.knowme.R;
import com.example.knowme.SharedPrefManager;
import com.example.knowme.StudentModel;
import com.google.firebase.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddAttendanceActivity extends AppCompatActivity {
    ArrayList<StudentModel> slist;

    private ListView lvStudent;

    DatabaseReference reference;
    FirebaseDatabase firebaseDatabase;
    private String cls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_attendance);

        firebaseDatabase=FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference("Student");
        SharedPrefManager sharedPrefManager=new SharedPrefManager(getApplicationContext());
        cls=sharedPrefManager.getCls();

        slist=new ArrayList<>();
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for(DataSnapshot ds: snapshot.getChildren()){
                        for(DataSnapshot s:ds.getChildren()){
                            StudentModel studentModel=s.getValue(StudentModel.class);
                            if(cls.equals(studentModel.getCls())){
                                slist.add(studentModel);
                            }
                        }
                    }
                }
                StudentViewAdapter studentViewAdapter=new StudentViewAdapter(getApplicationContext(),slist);
                lvStudent=findViewById(R.id.lvStudent);
                lvStudent.setAdapter(studentViewAdapter);
//                studentViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}