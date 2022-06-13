package com.example.learningapp.Data.UserDataBase;

import com.example.learningapp.Data.Message_pack.ClassRoomMessage;
import com.example.learningapp.Data.Message_pack.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Student extends Person {
    /*Student类继承自Person，是一个具体类
    * Student 是单个学生对象
    * 变量:
    * student_code 学生编号
    * my_ClassRoom_Message 教室消息，使用HashMap来存放信息，KEY：教室索引，Value: ClassRoomMessage单个教室的信息
    * class_room_array 学生加入的教室在my_Data中my_class_room的索引*/
    private String student_code;
    private HashMap<Integer,ClassRoomMessage> my_ClassRoom_Message;
    private ArrayList<Integer> class_room_array;

    public Student() {
        super();
        class_room_array = new ArrayList<>();
        my_ClassRoom_Message = new HashMap<>();
    }

    public void add_Message_queue(int classroom_index){
        /* 添加一个消息列
        * 传入参数： 教室索引*/
        ClassRoomMessage new_msg = new ClassRoomMessage();
        my_ClassRoom_Message.put(classroom_index,new_msg);
    }

    public ArrayList<Message> getClassRoomMessages(int index) {
        /*获取某个教室的所有消息
        * 参数：
        * index：教室索引 */
        return Objects.requireNonNull(my_ClassRoom_Message.get(index)).getMessages();
    }

    public void create_Message(int classRoom_index, String title, String text) {
        /*创建消息，由外部调用，某个特定的教室添加消息
        * 参数：
        * classRoom_index 教室索引
        * title: 标题
        * text: 消息内容*/
        my_ClassRoom_Message.get(classRoom_index).addMessage(title, text);
    }

    public String getStudent_code() {
        /*返回学生编号*/
        return student_code;
    }

    public ArrayList<Integer> getClass_room_array() {
        /*返回学生已经加入的教室*/
        return class_room_array;
    }

    public void setStudent_code(String student_code) {
        /*设置学生编号*/
        this.student_code = student_code;
    }

    public boolean add_classRoom(int room_index){
        /*添加教室
        * 参数：room_index 表示在 my_Data 中 my_class_room 数组的索引*/
        for(int i = 0; i < this.class_room_array.size();i++){
            if(class_room_array.get(i) == room_index){
                return false;
            }
        }
        class_room_array.add(room_index);
        return true;
    }

    public boolean is_AtClassRoom(int room_index) {
        /*判断学生是否在某个教室
        * 传入参数：room_index
        * 当学生在教室则返回true
        * 学生不在教室返回false*/
        for (int i = 0; i < this.class_room_array.size(); i++) {
            if (class_room_array.get(i) == room_index) {
                return true;
            }
        }
        return false;
    }

    public ClassRoomMessage getClassRoomMessage(int classRoomIndex) {
        /*获取教室消息对象*/
        return my_ClassRoom_Message.get(classRoomIndex);
    }
}
