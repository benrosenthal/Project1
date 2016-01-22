package com.brprog.todolistapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by User_1_Benjamin_Rosenthal on 1/22/16.
 */
public class ToDoList implements Serializable {
    //private static final long serialVersionUID = 0L;

    String mListTitle;
    ArrayList<String> mTasks;

    public ToDoList(ArrayList<String> tasks, String listTitle) {
        tasks = mTasks;
        listTitle = mListTitle;
    }

    public String getListTitle() {
        return mListTitle;
    }

    @Override
    public String toString() {
        return mListTitle;
    }

    public void setListTitle(String listTitle) {
        mListTitle = listTitle;
    }

    public ArrayList<String> getTasks() {
        return mTasks;
    }

    public String getOneTask(int index) {
        return mTasks.get(index);
    }

    public void addTask(String task) {
        mTasks.add(task);
    }

    public void addTasks(ArrayList<String> tasks) {Collections.addAll(mTasks); };

    public void removeTaskByIndex(int index) {
        mTasks.remove(index);
    }

    public void removeTaskByName(String task) {
        mTasks.remove(mTasks.indexOf(task));
    }
}


//     public void deleteToDoList(ToDoList toDoList){
//         toDoList.
//     }
