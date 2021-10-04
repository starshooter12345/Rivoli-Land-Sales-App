package com.project.landmanagementcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.landsalesapp.R;

public class sellerbuyandrent extends AppCompatActivity {

    Button btnsellersell, btnsellrent, btnselacc, deleteSelAccount;
    SelDBHelper selDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sellerbuyandrent);

        String u = getIntent().getStringExtra("user");

        selDB = new SelDBHelper(this);


        btnsellersell = (Button) findViewById(R.id.btnsellersell);
        btnsellrent = (Button) findViewById(R.id.btnsellerrent);
        btnselacc = (Button) findViewById(R.id.btnselacc);
        deleteSelAccount = findViewById(R.id.btn_seldelete);

        deleteSelAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selDB.deleteSellerAccount(u);
                Intent intent = new Intent(getApplicationContext(),SellerLogin.class);
                startActivity(intent);
            }
        });


        btnsellersell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddRecordActivity.class);
                startActivity(intent);
            }
        });

        btnsellrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),rentview.class);
                startActivity(intent);
            }
        });

        btnselacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),selleraccount2.class);
                startActivity(intent);
            }
        });
    }
}