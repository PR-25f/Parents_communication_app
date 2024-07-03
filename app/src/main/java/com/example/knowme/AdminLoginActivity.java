package com.example.knowme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLoginActivity extends AppCompatActivity {
    private EditText etAdminUname,etAdminUpass;
    private Button btnAdminLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        initialise();

        btnAdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname=etAdminUname.getText().toString().trim().toLowerCase();
                String upass=etAdminUpass.getText().toString().trim().toLowerCase();
                if (uname.matches("") || upass.matches("")){
                    Toast.makeText(AdminLoginActivity.this, "Wrong Admin Credentials", Toast.LENGTH_SHORT).show();
                }else{
                    if(uname.equals("admin") && upass.equals("admin")){
                        Intent i=new Intent(getApplicationContext(), AdminDashboardActivity.class);
                        startActivity(i);
                        finish();
                    }
                }
            }
        });
    }

    private void initialise() {
        etAdminUname=findViewById(R.id.etAdminUname);
        etAdminUpass=findViewById(R.id.etAdminUpass);
        btnAdminLogin=findViewById(R.id.btnAdminLogin);
    }
}