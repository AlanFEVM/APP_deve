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

public class SearchStudentDB_RV_Adapter extends RecyclerView.Adapter<SearchStudentDB_RV_Adapter.MyViewHolder> {
    private final ArrayList<Integer> my_studentIndex;
    private final Context my_context;
    private final RV_btnListener btnListener;
    public SearchStudentDB_RV_Adapter(Context context, ArrayList<Integer> studentIndex, RV_btnListener btnListener) {
        this.my_context = context;
        this.my_studentIndex = studentIndex;
        this.btnListener = btnListener;
    }

    @NonNull
    @Override
    public SearchStudentDB_RV_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(my_context);
        View view = inflater.inflate(R.layout.card_add_student, parent, false);
        return new MyViewHolder(view,btnListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchStudentDB_RV_Adapter.MyViewHolder holder, int position) {
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
        RV_btnListener btnListener;
        public MyViewHolder(@NonNull View itemView, RV_btnListener btnListener) {
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
    public interface RV_btnListener {
        void onCardButtonClick(int position);
    }
}
