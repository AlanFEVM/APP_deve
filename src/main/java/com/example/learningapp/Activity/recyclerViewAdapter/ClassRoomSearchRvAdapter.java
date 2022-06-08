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

public class ClassRoomSearchRvAdapter extends RecyclerView.Adapter<ClassRoomSearchRvAdapter.VH> {
    Context context;
    ArrayList<Integer> class_room_index;
    class_room_button_click btn_listener;
    public ClassRoomSearchRvAdapter(Context context, ArrayList<Integer> class_room_index, class_room_button_click btn_listener) {
        this.context = context;
        this.class_room_index = class_room_index;
        this.btn_listener = btn_listener;
    }

    @NonNull
    @Override
    public ClassRoomSearchRvAdapter.VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_student_join_classroom, parent, false);
        return new VH(view,btn_listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassRoomSearchRvAdapter.VH holder, int position) {
        holder.name.setText(my_Data.my_class_room.get(class_room_index.get(position)).getCourse_name());
        holder.code.setText(my_Data.my_class_room.get(class_room_index.get(position)).getClass_code());
    }

    @Override
    public int getItemCount() {
        return class_room_index.size();
    }

    protected static class VH extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button choose;
        TextView name;
        TextView code;
        class_room_button_click class_room_button_click_listener;
        public VH(@NonNull View itemView,class_room_button_click class_room_button_click_listener) {
            super(itemView);
            choose = itemView.findViewById(R.id.class_room_card_btn);
            name = itemView.findViewById(R.id.class_room_card_name);
            code = itemView.findViewById(R.id.class_room_card_code);
            choose.setOnClickListener(this);
            this.class_room_button_click_listener = class_room_button_click_listener;
        }

        @Override
        public void onClick(View v) {
            class_room_button_click_listener.onClassBtnClick(getAdapterPosition());
        }
    }
    public interface class_room_button_click{
        void onClassBtnClick(int position);
    }
}
