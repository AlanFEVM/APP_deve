package com.example.learningapp.recyclerViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learningapp.Data.ClassRoom.CheckIn;
import com.example.learningapp.R;

import java.util.ArrayList;

public class StudentCheckIn_RV_Adapter extends RecyclerView.Adapter<StudentCheckIn_RV_Adapter.ViewHolder> {
    Context context;
    ArrayList<CheckIn> myCheckIn;

    public StudentCheckIn_RV_Adapter(Context context,ArrayList<CheckIn> checkIns){
        this.context = context;
        this.myCheckIn = checkIns;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_student_checkin_rv,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(myCheckIn.get(position).has_password()){
            holder.password.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return myCheckIn.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder{
        Button checkin;
        EditText password;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkin = itemView.findViewById(R.id.student_checkIn_btn);
            password = itemView.findViewById(R.id.student_checkIn_password);
        }
    }
}
