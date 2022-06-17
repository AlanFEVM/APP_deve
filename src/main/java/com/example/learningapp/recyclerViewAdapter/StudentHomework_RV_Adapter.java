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

public class StudentHomework_RV_Adapter extends RecyclerView.Adapter<StudentHomework_RV_Adapter.Holder> {
    Context context;
    ArrayList<HomeworkList> my_homeworkList;
    cardButtonListener listener;

    public StudentHomework_RV_Adapter(Context context,ArrayList<HomeworkList> homeworkLists,cardButtonListener listener){
        this.context = context;
        this.my_homeworkList = homeworkLists;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_student_homework,parent,false);
        return new Holder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.homework_title.setText(my_homeworkList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return my_homeworkList.size();
    }

    protected static class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView homework_title;
        Button dowork,viewComment;
        cardButtonListener listener;
        public Holder(@NonNull View itemView,cardButtonListener listener) {
            super(itemView);
            homework_title = itemView.findViewById(R.id.student_homework_card_homework_name);
            dowork = itemView.findViewById(R.id.student_homework_card_homework_do);
            viewComment = itemView.findViewById(R.id.student_homework_card_homework_view);
            this.listener = listener;
            dowork.setOnClickListener(this);
            viewComment.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.student_homework_card_homework_do){
                listener.dowork(getAdapterPosition());
            }
            if (v.getId() == R.id.student_homework_card_homework_view){
                listener.viewComment(getAdapterPosition());
            }
        }
    }

    public interface cardButtonListener{
        void dowork(int position);
        void viewComment(int position);
    }
}
