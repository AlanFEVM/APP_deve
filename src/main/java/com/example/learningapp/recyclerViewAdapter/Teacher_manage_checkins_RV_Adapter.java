package com.example.learningapp.recyclerViewAdapter;

import android.annotation.SuppressLint;
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

public class Teacher_manage_checkins_RV_Adapter extends RecyclerView.Adapter<Teacher_manage_checkins_RV_Adapter.my_VH> {
    Context mContext;
    ArrayList<CheckIn> checkIns;
    buttonListener buttonListener;

    public Teacher_manage_checkins_RV_Adapter(Context context, ArrayList<CheckIn> checkin, buttonListener btnListener) {
        this.mContext = context;
        this.checkIns = checkin;
        this.buttonListener = btnListener;
    }

    @NonNull
    @Override
    public my_VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.card_teacher_manage_checkin, parent, false);
        return new my_VH(v, buttonListener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull my_VH holder, int position) {
        holder.name.setText(checkIns.get(position).getName());
        holder.counts.setText(Integer.toString(checkIns.get(position).checkedStudent()));
        if (checkIns.get(position).isLocked()) {
            holder.status.setText("锁定");
            holder.lock.setText("解除锁定");
        } else {
            holder.status.setText("开放");
            holder.lock.setText("锁定签到");
        }
    }

    @Override
    public int getItemCount() {
        return checkIns.size();
    }

    protected static class my_VH extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, counts, status;
        Button view_unchekec_students, lock;
        buttonListener buttonListener;

        public my_VH(@NonNull View itemView, buttonListener btnListener) {
            super(itemView);
            buttonListener = btnListener;
            name = itemView.findViewById(R.id.teacher_checkIn_manage_card_name);
            counts = itemView.findViewById(R.id.teacher_checkIn_manage_card_count);
            status = itemView.findViewById(R.id.teacher_checkIn_manage_card_status);
            view_unchekec_students = itemView.findViewById(R.id.teacher_checkIn_manage_card_view_unchecked);
            lock = itemView.findViewById(R.id.teacher_checkIn_manage_card_lockCheckIn);
            view_unchekec_students.setOnClickListener(this);
            lock.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int teacher_checkIn_manage_card_view_unchecked = R.id.teacher_checkIn_manage_card_view_unchecked;
            int teacher_checkIn_manage_card_lockCheckIn = R.id.teacher_checkIn_manage_card_lockCheckIn;
            if (v.getId() == teacher_checkIn_manage_card_view_unchecked) {
                buttonListener.viewStudent(getAdapterPosition());
            }
            if (v.getId() == teacher_checkIn_manage_card_lockCheckIn) {
                buttonListener.lockup(getAdapterPosition());
            }
        }
    }

    public interface buttonListener {
        void lockup(int position);

        void viewStudent(int position);
    }
}
