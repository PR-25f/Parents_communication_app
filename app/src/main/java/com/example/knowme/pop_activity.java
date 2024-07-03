package com.example.knowme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class pop_activity extends AppCompatActivity {
    Button btnNext;
    TextView tvPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);

        tvPopup=findViewById(R.id.tvPopup);
        btnNext=findViewById(R.id.btnNext);

        Intent i=getIntent();
        String user=i.getStringExtra("name");
        String type=i.getStringExtra("type");
        String msg="You are logged in Mr. "+user+" Welcome Back...";
        tvPopup.setText(msg);
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width=dm.widthPixels;
        int height=dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type.equals("teacher")){
                    Intent j=new Intent(getApplicationContext(),TeacherDashboardActivity.class);
                    startActivity(j);
                    finish();
                }else{
                    Intent j=new Intent(getApplicationContext(),ParentLoginActivity.class);
                    startActivity(j);
                    finish();

                }
            }
        });
    }
}