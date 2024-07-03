package com.example.knowme.Teacher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

public class StudentViewAdapter extends ArrayAdapter<StudentModel> {
    public StudentViewAdapter(@NonNull Context context, @NonNull List<StudentModel> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.student_view, parent, false);
        }

        StudentModel studentModel=getItem(position);
        TextView tvSname=currentItemView.findViewById(R.id.tvSname);
        TextView tvSreg=currentItemView.findViewById(R.id.tvSreg);
        tvSname.setText(studentModel.getName());
        tvSreg.setText(studentModel.getReg());

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference("Details").child(studentModel.getReg()).child("Attendance");
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        DatabaseReference ref=databaseReference.child(formattedDate);


        currentItemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ref.setValue("Absent");
                Toast.makeText(getContext(), "Absent Updated", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        currentItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.setValue("Present");
                Toast.makeText(getContext(), "Present Updated", Toast.LENGTH_SHORT).show();
            }
        });


        return currentItemView;
    }
}
