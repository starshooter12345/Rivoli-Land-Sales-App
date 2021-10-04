//package com.project.landmanagementcode;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import java.util.List;
//
//public class selleraccount extends AppCompatActivity {
//
//    EditText username, password, email, cn;
//    Button deleteSelAccount, updateSelAccount, selFeedback;
//    SelDBHelper selDB;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.selleraccount);
//
//        String user = getIntent().getStringExtra("user");
//
//        selDB = new SelDBHelper(this);
//
//        username = findViewById(R.id.acc_username);
//        password = findViewById(R.id.acc_password);
//        email = findViewById(R.id.acc_email);
//        cn = findViewById(R.id.acc_cn);
//
//        deleteSelAccount = findViewById(R.id.btn_seldelete);
//        updateSelAccount = findViewById(R.id.btn_selupdate);
//        selFeedback = findViewById(R.id.btn_selfeedback);
//
//        List<String> list = selDB.getSellerData(user);
//
//        username.setText(list.get(0));
//        password.setText(list.get(1));
//        email.setText(list.get(2));
//        cn.setText(list.get(3));
//
//        deleteSelAccount.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                selDB.deleteSellerAccount(user);
//                Intent intent = new Intent(getApplicationContext(),SellerLogin.class);
//                startActivity(intent);
//            }
//        });
//
//        updateSelAccount.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(),sellerupdate.class);
//                intent.putExtra("user", user);
//                startActivity(intent);
//                finish();
//            }
//        });
//
//        selFeedback.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(),"Provide a feedback for us", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}
