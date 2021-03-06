package com.example.learningapp.recyclerViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learningapp.Data.my_Data;
import com.example.learningapp.R;

import java.util.ArrayList;

public class ViewStudent_RV_Adapter extends RecyclerView.Adapter<ViewStudent_RV_Adapter.MyVH> {
    private final ArrayList<Integer> my_Student_index;
    private final Context context;

    public ViewStudent_RV_Adapter(Context context, ArrayList<Integer> S_arr) {
        this.context = context;
        this.my_Student_index = S_arr;
    }

    @NonNull
    @Override
    public ViewStudent_RV_Adapter.MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_view_student, parent, false);
        return new MyVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewStudent_RV_Adapter.MyVH holder, int position) {
        int student_index = my_Student_index.get(position);
        holder.tv_code.setText(my_Data.user_data.getStudent(student_index).getStudent_code());
        holder.tv_gender.setText(my_Data.user_data.getStudent(student_index).getGender());
        holder.tv_age.setText(my_Data.user_data.getStudent(student_index).getAge());
        holder.tv_name.setText(my_Data.user_data.getStudent(student_index).getName());
    }

    @Override
    public int getItemCount() {
        return my_Student_index.size();
    }

    public static class MyVH extends RecyclerView.ViewHolder {
        TextView tv_name, tv_code, tv_gender, tv_age;

        public MyVH(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.view_student_card_name);
            tv_age = itemView.findViewById(R.id.view_student_card_age);
            tv_code = itemView.findViewById(R.id.view_student_card_code);
            tv_gender = itemView.findViewById(R.id.view_student_card_gender);
        }
    }
}
