package com.project.landmanagementcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.landsalesapp.R;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class Buyerview extends AppCompatActivity {
   // FloatingActionButton fab;
    ActionBar actionBar;
    RecyclerView tRecyclerView;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyerview);

        actionBar = getSupportActionBar();
        actionBar.setTitle("List of Lands");

        tRecyclerView = findViewById(R.id.srecyclerView);
        databaseHelper = new DatabaseHelper(this);

        showRecord();

        //added to navigate to feedback
        //added to navigate to feedback page
        Button navtofeed = (Button) findViewById(R.id.feedButtonn);
        navtofeed.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(Buyerview.this,Feedback.class);
                startActivity(intent);
            }
        });

       // fab=findViewById(R.id.addFabButton);

        //added to navigate to feedback page
      /*  Button navtofeed = (Button) findViewById(R.id.feedButtonn);
        navtofeed.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this,Feedback.class);
                startActivity(intent);
            }
        });*/

        /*fab.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //create a new activity
                Intent intent = new Intent(MainActivity.this, AddRecordActivity.class);
                intent.putExtra("editMode",false);
                startActivity(intent);
            }
        });*/
    }
    private void showRecord(){
        BuyerAdapter adapter = new BuyerAdapter(Buyerview.this, databaseHelper.getAllData(Constants.C_ADD_TIMESTAMP + " DESC"));
        //because last added record shows on top
        tRecyclerView.setAdapter(adapter);

    }
    protected void onResume(){
        super.onResume();
        showRecord();
    }
    //kills all start activities
  /*  public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == event.KEYCODE_BACK){
            moveTaskToBack(true);

        }
        return super.onKeyDown(keyCode,event);

    }*/
}