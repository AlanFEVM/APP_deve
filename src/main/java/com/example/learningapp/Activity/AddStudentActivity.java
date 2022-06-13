package com.example.learningapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learningapp.Data.UserDataBase.Student;
import com.example.learningapp.Data.UserDataBase.Teacher;
import com.example.learningapp.Data.my_Data;
import com.example.learningapp.R;
import com.example.learningapp.recyclerViewAdapter.SearchStudentDB_RV_Adapter;

import java.util.ArrayList;

public class AddStudentActivity extends AppCompatActivity implements SearchStudentDB_RV_Adapter.RV_btnListener {
    //------------variables--------------------
    Button search_butn,back,confirm = null;
    TextView name, code = null;
    EditText code_et = null;
    RecyclerView StudentDB_RecyclerView = null;
    RecyclerView.Adapter<SearchStudentDB_RV_Adapter.MyViewHolder> adapter = null;
    ArrayList<Integer> student_Array_index = null;
    Teacher my_T = null;
    int teacher_index = -1;
    int student_to_add_index = -1;
    int classroom_index = -1;
    //---------------listener-----------------
    @Override
    public void onCardButtonClick(int position) {
        student_to_add_index = student_Array_index.get(position);
        setText();
    }
    View.OnClickListener search_btnListener = v -> {
        searcher();
        setText();
    };
    View.OnClickListener back_btnListenr = v -> goback();
    View.OnClickListener confirm_Listener = v -> {
        add_student_to_classRoom();
    };
    //---------------methods--------------------
    public void searcher(){
        String code = code_et.getText().toString();
        if(my_Data.user_data.getStudentArray().size() == 0){
            Toast.makeText(this,"学生数据库为空",Toast.LENGTH_LONG).show();
            return;
        }
        Student result = my_Data.user_data.findStudentByCode(code);
        if(code.matches("")){
            Toast.makeText(this,"请输入学号",Toast.LENGTH_LONG).show();
            return;
        }

        if(result == null){
            Toast.makeText(this,"没有找到这个同学",Toast.LENGTH_LONG).show();
            return;
        }
        setStudent(result);
    }
    private void goback (){
        Intent back = new Intent(this,Teacher_ClassRoom_Management_Activity.class);
        back.putExtra("teacher_index",teacher_index);
        startActivity(back);
    }
    public void setStudent(Student info){
        if(my_Data.user_data.getStudentArray().size() != 0){
            student_to_add_index = my_Data.user_data.findStudentIndexByCode(info.getStudent_code());
        }
    }
    public void setText(){
        if(student_to_add_index != -1){
            name.setText(my_Data.user_data.getStudent(student_to_add_index).getName());
            code.setText(my_Data.user_data.getStudent(student_to_add_index).getStudent_code());
        }
    }
    //---------------recycle view things setup --------------------
    public void set_up_recycler(){
        student_Array_index = my_Data.user_data.getStudentIndexNotInClassRoom(my_T.getClassroom_index());
        adapter = new SearchStudentDB_RV_Adapter(this,student_Array_index,this);
        StudentDB_RecyclerView.setAdapter(adapter);
        StudentDB_RecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void findViews(){
        search_butn = findViewById(R.id.search_btn);
        code_et = findViewById(R.id.add_student_codeET);
        code = findViewById(R.id.add_student_searchCode);
        name = findViewById(R.id.add_student_searchName);
        StudentDB_RecyclerView = findViewById(R.id.add_student_StudentDB);
        back = findViewById(R.id.add_student_back);
        confirm = findViewById(R.id.add_student_confirm);
    }
    private void setListener(){
        search_butn.setOnClickListener(search_btnListener);
        back.setOnClickListener(back_btnListenr);
        confirm.setOnClickListener(confirm_Listener);
    }
    private void resetThings(){
        student_to_add_index = -1;
        name.setText("");
        code.setText("");
    }
    private void add_student_to_classRoom() {
        if(!(student_to_add_index ==-1)){
            if(!my_Data.user_data.getStudent(student_to_add_index).is_AtClassRoom(classroom_index)){
                my_Data.user_data.getStudent(student_to_add_index).add_classRoom(my_T.getClassroom_index());
                my_Data.my_class_room.get(classroom_index).addStudent_By_student_index(student_to_add_index);
                my_Data.user_data.getStudent(student_to_add_index).add_Message_queue(classroom_index);
                Toast.makeText(this, "添加成功,请前往教室查看", Toast.LENGTH_LONG).show();
                set_up_recycler();
                resetThings();
            }else{
                Toast.makeText(this, "学生已经进入教室，添加失败", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "请搜索或者从列表中添加学生", Toast.LENGTH_LONG).show();
        }
    }
    //------------Activity setup-----------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        Intent lastI = getIntent();
        teacher_index = lastI.getIntExtra("teacher_index",-1);
        my_T = my_Data.user_data.getTeacher(teacher_index);
        classroom_index = my_T.getClassroom_index();
        findViews();
        setListener();
        set_up_recycler();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        set_up_recycler();
    }
}