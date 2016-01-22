package com.brprog.todolistapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

public class ToDoTasksActivity extends AppCompatActivity {


    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String RETURN_TODO_LIST = "RETURN_TODO_LIST";

    EditText mToDoListTitle;
    ListView mListView;
    ArrayAdapter mArrayAdapter;
    ImageView mBackImageView;
    Button mAddTaskButton;
    EditText mAddTaskEditText;
    ToDoList mCurrentToDoList;
    int mNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_tasks);

        mListView = (ListView) findViewById(R.id.listOfTasks);
        mToDoListTitle = (EditText) findViewById(R.id.toDoListTitle);
        mBackImageView = (ImageView) findViewById(R.id.backImageView);
        mAddTaskEditText = (EditText) findViewById(R.id.addTaskEditText);
        mAddTaskButton = (Button) findViewById(R.id.addTaskButton);

        mCurrentToDoList = (ToDoList) getIntent().getSerializableExtra("NEW_TODO_LIST");



        mToDoListTitle.setText(mCurrentToDoList.getListTitle());

        mArrayAdapter = new ArrayAdapter<String>(ToDoTasksActivity.this, android.R.layout.simple_list_item_1, mCurrentToDoList.getTasks());
        mListView.setAdapter(mArrayAdapter);


        mAddTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentToDoList.mTasks.isEmpty()) {
                    mCurrentToDoList.mTasks.add(mNum + ":  " + mAddTaskEditText.getText().toString());
                    mNum += 1;
                } else {
                    mNum = mCurrentToDoList.mTasks.size();
                    mCurrentToDoList.mTasks.add(mNum + ":  " + mAddTaskEditText.getText().toString());
                }
                mAddTaskEditText.getText().clear();
                mArrayAdapter.notifyDataSetChanged();
            }
        });

        mBackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, " We have" + mCurrentToDoList.getTasks());
                Intent intent = new Intent();
                intent.putExtra("RETURN_TODO_LIST", mCurrentToDoList);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void actionBarSetup(String title) {
        ActionBar ab = getSupportActionBar();
        ab.setTitle(title);
    }

}




