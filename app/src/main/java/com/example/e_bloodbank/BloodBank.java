package com.example.e_bloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BloodBank extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_bank);

        Button btn1 = (Button) findViewById(R.id.btnOP);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BloodBank.this, UserFilter.class);
                intent.putExtra("bloodType", BloodType.O_P.value());
                startActivity(intent);
            }
        });

        Button btn2 = (Button) findViewById(R.id.btnON);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BloodBank.this, UserFilter.class);
                intent.putExtra("bloodType", BloodType.O_N.value());
                startActivity(intent);
            }
        });

        Button btn3 = (Button) findViewById(R.id.btnAP);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BloodBank.this, UserFilter.class);
                intent.putExtra("bloodType", BloodType.A_P.value());
                startActivity(intent);
            }
        });

        Button btn4 = (Button) findViewById(R.id.btnAN);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BloodBank.this, UserFilter.class);
                intent.putExtra("bloodType", BloodType.A_N.value());
                startActivity(intent);
            }
        });

        Button btn5 = (Button) findViewById(R.id.btnBP);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BloodBank.this, UserFilter.class);
                intent.putExtra("bloodType", BloodType.B_P.value());
                startActivity(intent);
            }
        });

        Button btn6 = (Button) findViewById(R.id.btnBN);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BloodBank.this, UserFilter.class);
                intent.putExtra("bloodType", BloodType.B_N.value());
                startActivity(intent);
            }
        });

        Button btn7 = (Button) findViewById(R.id.btnABP);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BloodBank.this, UserFilter.class);
                intent.putExtra("bloodType", BloodType.AB_P.value());
                startActivity(intent);
            }
        });

        Button btn8 = (Button) findViewById(R.id.btnABN);
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BloodBank.this, UserFilter.class);
                intent.putExtra("bloodType", BloodType.AB_N.value());
                startActivity(intent);
            }
        });

    }
}