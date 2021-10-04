package com.project.landmanagementcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.landsalesapp.R;

import androidx.appcompat.app.AppCompatActivity;

public class Feedback extends AppCompatActivity {
    EditText mymail,subject,message;
    Button sendfeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        mymail=findViewById(R.id.feedemail);
        subject=findViewById(R.id.subjectfeed);
        message=findViewById(R.id.messagefeedback);
        sendfeed= findViewById(R.id.feedButton);

        sendfeed.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String Subjectfeed=subject.getText().toString();
                String messagefeed=message.getText().toString();
                String myMail = mymail.getText().toString();
                Intent send = new Intent(Intent.ACTION_SEND);
                send.putExtra(Intent.EXTRA_EMAIL,myMail);
                send.putExtra(Intent.EXTRA_SUBJECT,Subjectfeed);
                send.putExtra(Intent.EXTRA_TEXT,messagefeed);
                send.setType("message/rfc822");
                send.setPackage("com.google.android.gm");
                startActivity(send);

            }
        });

    }
}