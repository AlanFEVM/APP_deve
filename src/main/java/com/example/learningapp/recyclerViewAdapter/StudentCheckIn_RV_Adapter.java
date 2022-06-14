package com.example.learningapp.recyclerViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learningapp.Data.ClassRoom.CheckIn;
import com.example.learningapp.R;

import java.util.ArrayList;

public class StudentCheckIn_RV_Adapter extends RecyclerView.Adapter<StudentCheckIn_RV_Adapter.ViewHolder> {
    Context context;
    ArrayList<CheckIn> myCheckIn;
    signButtonListener btnListener;

    public StudentCheckIn_RV_Adapter(Context context,ArrayList<CheckIn> checkIns,signButtonListener btnListener){
        this.context = context;
        this.myCheckIn = checkIns;
        this.btnListener = btnListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_student_checkin_rv,parent,false);
        return new ViewHolder(view,btnListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(myCheckIn.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return myCheckIn.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        Button checkin;
        TextView name;
        signButtonListener my_listener;
        public ViewHolder(@NonNull View itemView,signButtonListener my_listener) {
            super(itemView);
            this.my_listener = my_listener;
            checkin = itemView.findViewById(R.id.student_checkIn_btn);
            name = itemView.findViewById(R.id.student_checkIn_name);
            checkin.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            my_listener.checkIn(getAdapterPosition());
        }
    }
    public interface signButtonListener{
        void checkIn(int position);
    }
}
