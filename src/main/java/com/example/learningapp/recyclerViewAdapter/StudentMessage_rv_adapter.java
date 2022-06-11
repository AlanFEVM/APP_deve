package com.example.learningapp.recyclerViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learningapp.Data.Message_pack.Message;
import com.example.learningapp.R;

import java.util.ArrayList;

public class StudentMessage_rv_adapter extends RecyclerView.Adapter<StudentMessage_rv_adapter.my_VH> {
    Context my_Context;
    ArrayList<Message> my_Messages;

    public StudentMessage_rv_adapter(Context context, ArrayList<Message> classRoomMessage) {
        this.my_Context = context;
        this.my_Messages = classRoomMessage;
    }

    @NonNull
    @Override
    public StudentMessage_rv_adapter.my_VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(my_Context);
        View view = inflater.inflate(R.layout.card_student_message_center, parent, false);
        return new my_VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentMessage_rv_adapter.my_VH holder, int position) {
        holder.text.setText(my_Messages.get(my_Messages.size()-position-1).getText());
        holder.title.setText(my_Messages.get(my_Messages.size()-position-1).getTitle());
        boolean read = my_Messages.get(my_Messages.size()-position-1).isRead();
        if(read){
            holder.read.setVisibility(View.VISIBLE);
            holder.unread.setVisibility(View.INVISIBLE);
        }else{
            holder.unread.setVisibility(View.VISIBLE);
            holder.read.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return my_Messages.size();
    }

    protected static class my_VH extends RecyclerView.ViewHolder {
        TextView title;
        TextView text;
        TextView read;
        TextView unread;

        public my_VH(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.message_card_title);
            text = itemView.findViewById(R.id.message_card_text);
            read = itemView.findViewById(R.id.message_card_read);
            unread = itemView.findViewById(R.id.message_card_unread);
        }
    }
}
