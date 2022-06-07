package com.example.learningapp.Activity.recyclerViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learningapp.Data.my_Data;
import com.example.learningapp.R;

import java.util.ArrayList;

public class SeacrchStudentDB_RV_Adapter extends RecyclerView.Adapter<SeacrchStudentDB_RV_Adapter.MyViewHolder> {
    private ArrayList<Integer> my_studentIndex;
    private Context my_context;
    private RVbtnListener btnListener;
    public SeacrchStudentDB_RV_Adapter(Context context, ArrayList<Integer> studentIndex, RVbtnListener btnListener) {
        this.my_context = context;
        this.my_studentIndex = studentIndex;
        this.btnListener = btnListener;
    }

    @NonNull
    @Override
    public SeacrchStudentDB_RV_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(my_context);
        View view = inflater.inflate(R.layout.student_card, parent, false);
        return new MyViewHolder(view,btnListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SeacrchStudentDB_RV_Adapter.MyViewHolder holder, int position) {
        holder.name.setText(my_Data.user_data.getStudent(my_studentIndex.get(position)).getName());
        holder.code.setText(my_Data.user_data.getStudent(my_studentIndex.get(position)).getStudent_code());
    }

    @Override
    public int getItemCount() {
        return my_studentIndex.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, code;
        Button choose;
        RVbtnListener btnListener;
        public MyViewHolder(@NonNull View itemView, RVbtnListener btnListener) {
            super(itemView);
            name = itemView.findViewById(R.id.student_card_name);
            code = itemView.findViewById(R.id.student_card_code);
            choose = itemView.findViewById(R.id.student_card_choose_btn);
            choose.setOnClickListener(this);
            this.btnListener = btnListener;
        }

        @Override
        public void onClick(View v) {
            btnListener.onCardButtonClick(getAdapterPosition());
        }
    }
    public interface RVbtnListener{
        void onCardButtonClick(int position);
    }
}
