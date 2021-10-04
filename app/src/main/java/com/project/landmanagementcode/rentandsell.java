package com.project.landmanagementcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.landsalesapp.R;

public class rentandsell extends AppCompatActivity {
	
	Button btnrentland, btnbuyland, btnaccount;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rentandsell);
		
		String user = getIntent().getStringExtra("user");
		
		btnbuyland = findViewById(R.id.btnbuyland);
		btnrentland = findViewById(R.id.btnrentland);
		btnaccount = findViewById(R.id.btnaccount);

        btnrentland.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),rentview.class);
                startActivity(intent);
            }
        });
		
		
		btnbuyland.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), Buyerview.class);
				startActivity(intent);
			}
		});
		
		btnaccount.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getApplicationContext(), buyeraccount.class);
				intent.putExtra("user", user);
				startActivity(intent);
			}
		});
		
	}
}