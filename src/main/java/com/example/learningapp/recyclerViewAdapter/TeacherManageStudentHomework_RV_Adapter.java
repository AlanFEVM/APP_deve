package com.example.learningapp.recyclerViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learningapp.Data.ClassRoom.StudentHomework;
import com.example.learningapp.Data.my_Data;
import com.example.learningapp.R;

import java.util.ArrayList;

public class TeacherManageStudentHomework_RV_Adapter extends RecyclerView.Adapter<TeacherManageStudentHomework_RV_Adapter.Holder> {
    Context context;
    ArrayList<StudentHomework> myhomework;
    btnListener myButtonListener;
    int my_student_index;

    public TeacherManageStudentHomework_RV_Adapter(Context context, ArrayList<StudentHomework> homework, btnListener btnListener) {
        this.context = context;
        this.myhomework = homework;
        this.myButtonListener = btnListener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.card_teacher_viewstudenthomrwork_rv, parent, false);
        return new Holder(v, myButtonListener);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        my_student_index = myhomework.get(position).getStudent_index();
        holder.name.setText(my_Data.user_data.getStudent(my_student_index).getName());
        holder.code.setText(my_Data.user_data.getStudent(my_student_index).getStudent_code());

    }

    @Override
    public int getItemCount() {
        return myhomework.size();
    }

    protected static class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, code;
        Button view;
        btnListener buttonListener;

        public Holder(@NonNull View itemView, btnListener buttonListener) {
            super(itemView);
            name = itemView.findViewById(R.id.teacher_viewStudentHomework_card_name);
            code = itemView.findViewById(R.id.teacher_viewStudentHomework_card_code);
            view = itemView.findViewById(R.id.teacher_viewStudentHomework_card_button);
            this.buttonListener = buttonListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            buttonListener.onButtonClick(getAdapterPosition());
        }
    }

    public interface btnListener {
        void onButtonClick(int position);
    }
}
