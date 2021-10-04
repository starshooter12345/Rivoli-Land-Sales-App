package com.project.landmanagementcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.landsalesapp.R;

import java.util.List;

public class buyeraccount extends AppCompatActivity {
	
	EditText name, email, nic, phone;
	Button deleteAccount, updateAccount, feedback;
	buyerDBhelper buyDB;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.buyeraccount);
		
		String user = getIntent().getStringExtra("user");
		
		buyDB = new buyerDBhelper(this);
		
		name = findViewById(R.id.uct_name);
		email = findViewById(R.id.uct_email);
		nic = findViewById(R.id.uct_nic);
		phone = findViewById(R.id.uct_phone);
		
		deleteAccount = findViewById(R.id.btn_delete);
		updateAccount = findViewById(R.id.btn_update);
		feedback = findViewById(R.id.btn_feedback);
		
		List<String> list = buyDB.getBuyerData(user);
		
		name.setText(list.get(0));
		email.setText(list.get(1));
		nic.setText(list.get(2));
		phone.setText(list.get(3));
		
		deleteAccount.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				buyDB.deleteBuyerAccount(user);
				Intent intent = new Intent(getApplicationContext(), buyerlogin.class);
				startActivity(intent);
			}
		});
		
		updateAccount.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getApplicationContext(), buyerupdate.class);
				intent.putExtra("user", user);
				startActivity(intent);
				finish();
			}
		});
		
		feedback.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(getApplicationContext(), "Give A Feedback for us.", Toast.LENGTH_SHORT).show();
			}
		});
		
	}
}