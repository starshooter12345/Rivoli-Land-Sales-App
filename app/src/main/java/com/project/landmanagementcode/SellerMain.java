package com.project.landmanagementcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.landsalesapp.R;

import androidx.appcompat.app.AppCompatActivity;

public class SellerMain extends AppCompatActivity {

    EditText selusername, selpassword, selrepassword, selemail, selcn;
    Button btnselSignup, btnselSignin;
    SelDBHelper selDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sellerregister);

        selusername = (EditText)findViewById(R.id.selusername);
        selpassword = (EditText)findViewById(R.id.selpassword);
        selrepassword = (EditText)findViewById(R.id.selrepassword);
        selemail = (EditText)findViewById(R.id.selemail);
        selcn = (EditText)findViewById(R.id.selcn);

        btnselSignup = (Button) findViewById(R.id.btnselSignup);
        btnselSignin = (Button) findViewById(R.id.btnselSignin);

        selDB = new SelDBHelper(this);

        btnselSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = selusername.getText().toString();
                String pass = selpassword.getText().toString();
                String repass = selrepassword.getText().toString();
                String email = selemail.getText().toString();
                String cn = selcn.getText().toString();

                if(user.equals("") || pass.equals("") || repass.equals("") ||email.equals("") ||cn.equals(""))
                {
                    Toast.makeText(SellerMain.this, "Please fillout all the fields...!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (pass.equals(repass))
                    {
                        Boolean usercheckResult = selDB.checkusername(user);
                        if (usercheckResult == false)
                        {
                            Boolean regResult = selDB.insertData(user, pass, email, cn);
                            if (regResult == true)
                            {
                                Toast.makeText(SellerMain.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                Intent intent =  new Intent(getApplicationContext(),SellerLogin.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(SellerMain.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(SellerMain.this, "User already Exists. \n Please Sign In.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(SellerMain.this, "Passwords does not matched.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnselSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SellerLogin.class);
                startActivity(intent);
            }
        });
    }
}