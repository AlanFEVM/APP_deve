package com.example.learningapp.recyclerViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learningapp.Data.ClassRoom.HomeworkList;
import com.example.learningapp.R;

import java.util.ArrayList;

public class ManageHomework_RV_Adapter extends RecyclerView.Adapter<ManageHomework_RV_Adapter.ViewHolder> {
    Context context;
    ArrayList<HomeworkList> homework;
    ViewHomework btnListener;

    public ManageHomework_RV_Adapter(Context context,ArrayList<HomeworkList> homework,ViewHomework btnListener){
        this.context = context;
        this.homework = homework;
        this.btnListener = btnListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.card_teacer_manage_homework,parent,false);
        return new ViewHolder(v,btnListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(homework.get(position).getTitle());
        holder.counts.setText(String.valueOf(homework.get(position).getAnswerCounts()));
    }

    @Override
    public int getItemCount() {
        return homework.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ViewHomework btnlistener;
        Button view;
        TextView counts;
        TextView title;
        public ViewHolder(@NonNull View itemView,ViewHomework btnlistener) {
            super(itemView);
            view = itemView.findViewById(R.id.manage_homework_card_student_viewHomework);
            counts = itemView.findViewById(R.id.manage_homework_card_student_counts);
            title = itemView.findViewById(R.id.manage_homework_card_title);
            this.btnlistener = btnlistener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            btnlistener.viewStudent(getAdapterPosition());
        }
    }
    public interface ViewHomework{
        void viewStudent(int position);
    }
}
