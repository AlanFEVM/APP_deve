package com.example.learningapp.recyclerViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learningapp.Data.UserDataBase.Student;
import com.example.learningapp.R;

import java.util.ArrayList;

public class UncheckedStudent_RV_Adapter extends RecyclerView.Adapter<UncheckedStudent_RV_Adapter.Vholder> {
    Context context;
    ArrayList<Student> students;

    public UncheckedStudent_RV_Adapter(Context context, ArrayList<Student> students) {
        this.context = context;
        this.students = students;
    }

    @NonNull
    @Override
    public Vholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.card_unchecked_student, parent, false);
        return new Vholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Vholder holder, int position) {
        holder.name.setText(students.get(position).getName());
        holder.code.setText(students.get(position).getStudent_code());
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    protected static class Vholder extends RecyclerView.ViewHolder {
        TextView name;
        TextView code;

        public Vholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.unchecked_student_name);
            code = itemView.findViewById(R.id.unchecked_student_code);
        }
    }
}
