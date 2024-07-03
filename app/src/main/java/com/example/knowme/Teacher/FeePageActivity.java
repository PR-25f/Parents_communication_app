package com.example.knowme.Teacher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.knowme.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FeePageActivity extends AppCompatActivity {
    private EditText etsReg,etSsem,etSfee,etSpay;
    private Button btnAddFee;
    String regno;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee_page);

        initialise();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Details");
        regno= getIntent().getStringExtra("reg");
        etsReg.setText(regno);
        btnAddFee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sem=etSsem.getText().toString().trim();
                String tfee=etSfee.getText().toString().trim();
                String fee=etSpay.getText().toString().trim();
                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                String formattedDate = df.format(c);
                ref=databaseReference.child(regno).child("Fee").child(formattedDate);
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(!snapshot.exists()){
                            FeeModel feeModel=new FeeModel(regno,sem,tfee,fee,formattedDate);
                            ref.setValue(feeModel);
                            Intent i =new Intent(getApplicationContext(), AddFeeActivity.class);
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
        etsReg=findViewById(R.id.etsReg);
        etSsem=findViewById(R.id.etSsem);
        etSfee=findViewById(R.id.etSfee);
        etSpay=findViewById(R.id.etSpay);
        btnAddFee=findViewById(R.id.btnAddFee);
    }
}