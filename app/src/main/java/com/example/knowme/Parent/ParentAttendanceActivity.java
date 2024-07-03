package com.example.knowme.Parent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.example.knowme.MainActivity;
import com.example.knowme.R;
import com.example.knowme.SharedPrefManager;
import com.example.knowme.Teacher.StudentViewAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ParentAttendanceActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,ref;
    private TextView tvPercent;
    private ListView lvAtt;
    ArrayList<AttModel> alist;
    int count,total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_attendance);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigationView1);
        SharedPrefManager sharedPrefManager=new SharedPrefManager(getApplicationContext());
        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.attendance);
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

        tvPercent=findViewById(R.id.tvPercent);
        lvAtt=findViewById(R.id.lvAtt);
        firebaseDatabase=FirebaseDatabase.getInstance();
        alist=new ArrayList<>();
        databaseReference=firebaseDatabase.getReference("Details").child(sharedPrefManager.getRmobile()).child("Attendance");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot s: snapshot.getChildren()){
                        count++;
                        AttModel attModel=new AttModel();
                        attModel.setDate(s.getKey());
                        attModel.setStatus(s.getValue(String.class));
                        alist.add(attModel);
                        if(s.getValue(String.class).equals("Present")){
                            total++;
                        }
                    }
                    double per=(total/Double.valueOf(count))*100;
                    tvPercent.setText("YOUR ATTENDANCE IS : "+String.valueOf(per));
                    AttendanceAdapter studentViewAdapter=new AttendanceAdapter(getApplicationContext(),alist);

                    lvAtt.setAdapter(studentViewAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}