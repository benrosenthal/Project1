package com.brprog.todolistapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String TODO_LIST = "TODO_LIST";
    public static final int ADD_ITEMS = 10;

    ListView mListView;
    ArrayList<ToDoList> mMasterList;
    ArrayAdapter<ToDoList> mArrayAdapter;
    Button mAddButton;
    EditText mAddToDoList;
    ImageView mPicIfNoLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBarSetup("Listless");


        mAddToDoList = (EditText) findViewById(R.id.addToDoList);
        mListView = (ListView) findViewById(R.id.listView);
        mAddButton = (Button) findViewById(R.id.addButton);
        mAddToDoList = (EditText) findViewById(R.id.addToDoList);
        mPicIfNoLists = (ImageView)findViewById(R.id.picIfNoLists);





        //Give mMasterList a value to avoid Null Pointer Exception
        if (mMasterList == null) {
            mMasterList = new ArrayList<ToDoList>();
        }



        mArrayAdapter = new ArrayAdapter<ToDoList>(MainActivity.this, android.R.layout.simple_list_item_1, mMasterList);
        mListView.setAdapter(mArrayAdapter);



        Log.d(TAG, "Views references from layout set");

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getText(mAddToDoList).length() > 0) {
                    addToDoListActivity();
                } else {
                    Toast.makeText(MainActivity.this, "Provide a name for your to-do list before pressing ADD", Toast.LENGTH_LONG).show();
                }
                mAddToDoList.getText().clear();
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ToDoTasksActivity.class);
                intent.putExtra(TODO_LIST, mMasterList.get(position));
                startActivityForResult(intent, 0);
            }
        });




    }

    //get String from edittext without call getText().toString repeadetly
    public static String getText(EditText editText) {
        return editText.getText().toString();
    }

    private void actionBarSetup(String title) {
        ActionBar ab = getSupportActionBar();
        ab.setTitle(title);
    }






    public void addToDoListActivity() {
        if (getText(mAddToDoList).length() > 0) {
            ToDoList newToDoList = new ToDoList(new ArrayList<String>(), getText(mAddToDoList));
            if (newToDoList.mTasks == null) {
                newToDoList.mTasks = new ArrayList<String>();
            }
            Intent intent = new Intent(MainActivity.this, ToDoTasksActivity.class);
            intent.putExtra(TODO_LIST, newToDoList);
            startActivityForResult(intent, 10);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Make sure the request was successful
        Toast.makeText(this,"Returned from result",Toast.LENGTH_SHORT).show();
        if (resultCode == RESULT_OK) {//&& extra contains anythings maybe put in try block {

            Log.d(TAG, "Request code valid.");
            ToDoList returnedToDoList = (ToDoList) data.getSerializableExtra("RETURN_TODO_LIST");
            Toast.makeText(this,"task list size "+returnedToDoList.getTasks().size(),Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Received ToDoList object.");
            Log.d(TAG, "Todo list with possible tasks added appended to mMasterList");
            Log.d(TAG, "Length of master list " + mMasterList.size());
            mMasterList.add(returnedToDoList);
            mArrayAdapter.notifyDataSetChanged();
            Log.d(TAG, "Length of returnedToDoList" + returnedToDoList.getTasks());
        } else {
            Log.d(TAG, "Something has gone wrong with the data tranfer between activties");
        }

    }

}

