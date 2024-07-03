package com.example.knowme.Teacher;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.knowme.R;
import com.example.knowme.StudentModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SubjectViewAdapter extends ArrayAdapter<SubjectModel> {
    Context mcon;
    String regno;
    public SubjectViewAdapter(@NonNull Context context, @NonNull List<SubjectModel> objects,String regno) {
        super(context, 0, objects);
        this.mcon=context;
        this.regno=regno;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.subject_view, parent, false);
        }

        SubjectModel studentModel=getItem(position);
        TextView tvSname=currentItemView.findViewById(R.id.tvSubname);
        TextView tvSreg=currentItemView.findViewById(R.id.tvSubcode);
        tvSname.setText(studentModel.getName());
        tvSreg.setText(studentModel.getCode());

        currentItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(), AddIAActivity.class);
                i.putExtra("code",studentModel.getCode());
                i.putExtra("regno",regno);
                i.putExtra("sem",studentModel.getSem());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mcon.startActivity(i);
            }
        });




        return currentItemView;
    }
}
