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
import com.example.knowme.Teacher.FeeModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ParentFeesActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ArrayList<FeeModel> flist;
    int total,pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_fees);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigationView2);
        SharedPrefManager sharedPrefManager=new SharedPrefManager(getApplicationContext());



        firebaseDatabase=FirebaseDatabase.getInstance();
        flist=new ArrayList<>();
        databaseReference=firebaseDatabase.getReference("Details").child(sharedPrefManager.getRmobile()).child("Fee");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot s:snapshot.getChildren()){
                        FeeModel feeModel=s.getValue(FeeModel.class);
                        flist.add(feeModel);
                        total=Integer.valueOf(feeModel.getTot());
                        pay=pay+Integer.valueOf(feeModel.getPay());
                    }
                    TextView tvBalance=findViewById(R.id.tvBalance);
                    tvBalance.setText("Your Balnece Till Now is : "+String.valueOf(total-pay));
                    FeeAdapter studentViewAdapter=new FeeAdapter(getApplicationContext(),flist);

                    ListView lvFee=findViewById(R.id.lvFee);

                    lvFee.setAdapter(studentViewAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.fees);
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

    }
}