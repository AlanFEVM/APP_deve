package com.example.learningapp.recyclerViewAdapter;

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

public class ClassRoomInfo_RVAdapter extends RecyclerView.Adapter<ClassRoomInfo_RVAdapter.my_VH> {
    Context context;
    ArrayList<Integer> classroom_index;
    my_button_listener btn_listener;
    public ClassRoomInfo_RVAdapter(Context context, ArrayList<Integer> arr, my_button_listener listener){
        this.context = context;
        this.classroom_index = arr;
        this.btn_listener = listener;
    }

    @NonNull
    @Override
    public my_VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_student_view_classroom_info,parent,false);
        return new my_VH(view,btn_listener);
    }

    @Override
    public void onBindViewHolder(@NonNull my_VH holder, int position) {
        holder.name_tv.setText(my_Data.my_class_room.get(classroom_index.get(position)).getCourse_name());
        holder.code_tv.setText(my_Data.my_class_room.get(classroom_index.get(position)).getClass_code());
        holder.teacher_name_tv.setText(my_Data.my_class_room.get(classroom_index.get(position)).getTeacher_name());
    }

    @Override
    public int getItemCount() {
        return classroom_index.size();
    }

    public static class my_VH extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name_tv,code_tv,teacher_name_tv;
        Button enter;
        my_button_listener btn_listener;
        public my_VH(@NonNull View itemView,my_button_listener listener) {
            super(itemView);
            name_tv = itemView.findViewById(R.id.student_view_classRoom_card_className);
            code_tv = itemView.findViewById(R.id.student_view_classRoom_card_classcode);
            teacher_name_tv = itemView.findViewById(R.id.student_view_classRoom_card_ClassTeacher_name);
            enter =  itemView.findViewById(R.id.student_view_classRoom_card_enterRoom_btn);
            enter.setOnClickListener(this);
            btn_listener = listener;
        }
        @Override
        public void onClick(View v) {
            btn_listener.onButtonClick(getAdapterPosition());
        }
    }

    public interface my_button_listener{
        void onButtonClick(int position);
    }
}
