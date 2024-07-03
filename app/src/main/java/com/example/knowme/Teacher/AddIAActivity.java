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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddIAActivity extends AppCompatActivity {
    String sem,code,regno;
    private EditText etSubMark;
    private Button btnEMark;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_iaactivity);
        initialise();

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Details");
        ref=databaseReference.child(regno).child("Mark").child(sem).child(code);
        btnEMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String mark=etSubMark.getText().toString().trim();
                        if(mark.matches("")){
                            Toast.makeText(AddIAActivity.this, "ENter Mark", Toast.LENGTH_SHORT).show();
                        }else{
                            ref.setValue(mark);
                            Toast.makeText(AddIAActivity.this, "Mark Added", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(getApplicationContext(), AddMarksActivity.class);
                            startActivity(i);
                            finish();
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
        Intent j=getIntent();
        sem=j.getStringExtra("sem");
        code=j.getStringExtra("code");
        regno=j.getStringExtra("regno");
        etSubMark=findViewById(R.id.etSubMark);
        btnEMark=findViewById(R.id.btnEMark);
    }
}