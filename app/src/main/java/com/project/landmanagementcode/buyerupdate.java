package com.project.landmanagementcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.landsalesapp.R;

public class buyerupdate extends AppCompatActivity {
	
	EditText name, email, password, mobileNumber;
	Button update;
	
	buyerDBhelper buyDB;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.buyerupdate);
		
		String user = getIntent().getStringExtra("user");
		
		name = findViewById(R.id.upd_name);
		email = findViewById(R.id.upd_email);
		password = findViewById(R.id.unicnumber);
		mobileNumber = findViewById(R.id.editText2);
		
		update = findViewById(R.id.button2);
		
		buyDB = new buyerDBhelper(this);
		
		update.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String uName = name.getText().toString();
				String uEmail = email.getText().toString();
				String uPassword = password.getText().toString();
				String uMobileNumber = mobileNumber.getText().toString();
				
				buyDB.updateBuyerAcoount(uName, uEmail, uPassword, uMobileNumber);
				Toast.makeText(getApplicationContext(), "Data Updated Successfully. Please Sign in.", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(getApplicationContext(), home.class);
				intent.putExtra("user", user);
				startActivity(intent);
				finish();
			}
		});
		
	}
}