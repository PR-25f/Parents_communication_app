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

public class AttendanceAdapter extends ArrayAdapter<AttModel> {
    Context mcon;
    public AttendanceAdapter(@NonNull Context context, @NonNull List<AttModel> objects) {
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
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.att_view, parent, false);
        }

        AttModel attModel=getItem(position);
        TextView tvone=currentItemView.findViewById(R.id.tvAdate);
        TextView tvtwo=currentItemView.findViewById(R.id.tvAstatus);
        tvone.setText(attModel.getDate());
        tvtwo.setText(attModel.getStatus());


        return currentItemView;
    }
}
