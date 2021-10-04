package com.project.landmanagementcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.landsalesapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
	FloatingActionButton fab;
	ActionBar actionBar;
	RecyclerView mRecyclerView;
	DatabaseHelper databaseHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		actionBar = getSupportActionBar();
		actionBar.setTitle("List of Lands");

		mRecyclerView = findViewById(R.id.recyclerView);
		databaseHelper = new DatabaseHelper(this);

		showRecord();

		fab=findViewById(R.id.addFabButton);

		//added to navigate to feedback page
		Button navtofeed = (Button) findViewById(R.id.feedButtonn);
		navtofeed.setOnClickListener(new View.OnClickListener(){
			public void onClick(View view){
				Intent intent = new Intent(MainActivity.this,Feedback.class);
				startActivity(intent);
			}
		});

		fab.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				//create a new activity
				Intent intent = new Intent(MainActivity.this, AddRecordActivity.class);
				intent.putExtra("editMode",false);
				startActivity(intent);
			}
		});
	}
	private void showRecord(){
		Adapter adapter = new Adapter(MainActivity.this, databaseHelper.getAllData(Constants.C_ADD_TIMESTAMP + " DESC"));
		//because last added record shows on top
		mRecyclerView.setAdapter(adapter);

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