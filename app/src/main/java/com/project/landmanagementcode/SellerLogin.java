package com.project.landmanagementcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.landsalesapp.R;

public class SellerLogin extends AppCompatActivity {

    EditText selusernamelogin, selpasswordlogin;
    Button selbtnlogin;

    SelDBHelper selDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_login);

        selusernamelogin = (EditText) findViewById(R.id.selusernamelogin);
        selpasswordlogin = (EditText) findViewById(R.id.selpasswordlogin);
        selbtnlogin = (Button) findViewById(R.id.selbtnlogin);

        selDB = new SelDBHelper(this);

        selbtnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = selusernamelogin.getText().toString();
                String pass = selpasswordlogin.getText().toString();

                if (user.equals("") || pass.equals(""))
                {
                    Toast.makeText(SellerLogin.this, "Please Enter the credentials.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean result = selDB.checkusernamePassword(user,pass);
                    if (result == true)
                    {
                        Intent intent =new Intent(getApplicationContext(),sellerbuyandrent.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(SellerLogin.this, "Invalid Credentials.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}