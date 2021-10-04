package com.project.landmanagementcode;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.landsalesapp.R;


import androidx.appcompat.app.AppCompatActivity;

public class CalculateDiscount extends AppCompatActivity {

    TextView discountt;
    EditText agee, yearsregg;
    Button calcdisco;
    //int age,yearsreg;
    int age,yearsreg;
    float finddiscount;

    public int disc_calcc(int num1, int num2){
        return num1/num2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_discount);


        discountt=(TextView)findViewById(R.id.discoo);
        agee=(EditText)findViewById(R.id.discoage);
        yearsregg=(EditText)findViewById(R.id.discoyears);
        calcdisco=(Button)findViewById(R.id.discountcalculate);

        calcdisco.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                age=Integer.parseInt(agee.getText().toString());
                yearsreg=Integer.parseInt(yearsregg.getText().toString());
                finddiscount = disc_calcc(age,yearsreg);
                discountt.setText(String.valueOf(finddiscount));


            }
        });

    }


}