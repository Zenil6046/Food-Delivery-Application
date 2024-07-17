package com.example.fooddel2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Sign extends AppCompatActivity {

    Button signup;
    EditText name,ph_no,pass,edt;
    @SuppressLint({ "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        signup = findViewById(R.id.btn);

        name = findViewById(R.id.sign_name);

        ph_no = findViewById(R.id.sign_ph_no);

        pass = findViewById(R.id.sign_password);
        edt = findViewById(R.id.edt);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(pass.getText().toString().equals(edt.getText().toString())){
                    DbHelper dbHelper = new DbHelper(getApplicationContext());
                    String Name = name.getText().toString();
                    String Ph_no=ph_no.getText().toString();
                    String Pass=pass.getText().toString();
                    dbHelper.addData(Ph_no,Name,Pass);

                    Intent intent = new Intent(Sign.this,Home.class);
                    startActivity(intent);
                }


            }
        });

    }
}