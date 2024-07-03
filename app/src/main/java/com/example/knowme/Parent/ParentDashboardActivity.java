package com.example.knowme.Parent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.knowme.MainActivity;
import com.example.knowme.R;
import com.example.knowme.SharedPrefManager;
import com.example.knowme.TeacherModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

public class ParentDashboardActivity extends AppCompatActivity {
    private ImageView ivAnnouncement,ivPhone,ivMail;

    String link="";
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String tname,tmail,tphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_dashboard);

        SharedPrefManager sharedPrefManager=new SharedPrefManager(getApplicationContext());
        ivAnnouncement=findViewById(R.id.ivAnnouncement);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigationView);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.home);

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


        StorageReference listRef = FirebaseStorage.getInstance().getReference().child("images").child(sharedPrefManager.getCls());
        listRef.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {
                for (StorageReference s:listResult.getItems()){
                    s.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            link=uri.toString();
                        }
                    }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Glide.with(getApplicationContext()).load(link).into(ivAnnouncement);
                        }
                    });
                }
                Toast.makeText(ParentDashboardActivity.this, "Link is "+link, Toast.LENGTH_SHORT).show();
                Glide.with(getApplicationContext()).load(link).into(ivAnnouncement);
            }
        });

        ivMail=findViewById(R.id.ivMail);
        ivPhone=findViewById(R.id.ivPhone);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Teacher");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot t:snapshot.getChildren()){
                        TeacherModel teacherModel=t.getValue(TeacherModel.class);
                        if (teacherModel.getCls().equals(sharedPrefManager.getCls())){
                            tname=teacherModel.getName();
                            tphone=teacherModel.getMobile();
                            tmail=teacherModel.getEmpno();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ivPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ParentDashboardActivity.this, tphone, Toast.LENGTH_LONG).show();
            }
        });

        ivMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ParentDashboardActivity.this, tmail, Toast.LENGTH_SHORT).show();
            }
        });

    }
}