package com.example.knowme.Parent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.knowme.R;

import java.util.List;

public class ExamAdapter extends ArrayAdapter<ExamModel> {
    Context mcon;
    public ExamAdapter(@NonNull Context context, @NonNull List<ExamModel> objects) {
        super(context, 0, objects);
        this.mcon=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // convertView which is recyclable view
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.mark_view, parent, false);
        }

        ExamModel attModel=getItem(position);
        TextView tvone=currentItemView.findViewById(R.id.tvCcode);
        TextView tvtwo=currentItemView.findViewById(R.id.tvCname);
        TextView tvthree=currentItemView.findViewById(R.id.tvCMarks);
        TextView tvfour=currentItemView.findViewById(R.id.tvCsname);
        TextView tvfive=currentItemView.findViewById(R.id.tvCTotal);
        tvone.setText(attModel.getCode());
        tvtwo.setText(attModel.getName());
        tvthree.setText(attModel.getMarks());
        tvfour.setText(attModel.getCgpa());
        tvfive.setText(attModel.getTotal());


        return currentItemView;
    }
}
