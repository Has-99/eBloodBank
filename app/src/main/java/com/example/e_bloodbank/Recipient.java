package com.example.e_bloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Recipient extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipient);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        Spinner spinBlood = (Spinner) findViewById(R.id.RecBlood);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Recipient.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.blood));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinBlood.setAdapter(myAdapter);

        Spinner spinProv = (Spinner) findViewById(R.id.RecProvince);
        ArrayAdapter<String> myAdapter3 = new ArrayAdapter<String>(Recipient.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.provinces));
        spinProv.setAdapter(myAdapter3);

        Spinner spinDis = (Spinner) findViewById(R.id.RecDistrict);

        spinProv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            ArrayAdapter<String> myAdapter4;

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (myAdapter3.getItem(position).equals("Western Province")) {
                    myAdapter4 = new ArrayAdapter<>(Recipient.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.western));
                } else if (myAdapter3.getItem(position).equals("Central Province")) {
                    myAdapter4 = new ArrayAdapter<>(Recipient.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.central));
                } else if (myAdapter3.getItem(position).equals("Southern Province")) {
                    myAdapter4 = new ArrayAdapter<>(Recipient.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.southern));
                } else if (myAdapter3.getItem(position).equals("Northern Province")) {
                    myAdapter4 = new ArrayAdapter<>(Recipient.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.nothern));
                } else if (myAdapter3.getItem(position).equals("North Western Province")) {
                    myAdapter4 = new ArrayAdapter<>(Recipient.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.northwestern));
                } else if (myAdapter3.getItem(position).equals("Eastern Province")) {
                    myAdapter4 = new ArrayAdapter<>(Recipient.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.eastern));
                } else if (myAdapter3.getItem(position).equals("North Central Province")) {
                    myAdapter4 = new ArrayAdapter<>(Recipient.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.northcentral));
                } else if (myAdapter3.getItem(position).equals("Uva Province")) {
                    myAdapter4 = new ArrayAdapter<>(Recipient.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.uva));
                } else if (myAdapter3.getItem(position).equals("Sabaragamuwa Province")) {
                    myAdapter4 = new ArrayAdapter<>(Recipient.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.sabaragamuwa));
                }
                spinDis.setAdapter(myAdapter4);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        EditText RecName = findViewById(R.id.RecName);
        EditText RecAge = findViewById(R.id.RecAge);

        Button btnREQ = (Button) findViewById(R.id.btnReq);
        btnREQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Recipient.this, HospitalFilter.class);

                String province = (String) spinProv.getSelectedItem();
                intent.putExtra("province", province.replace(" Province", ""));

                String district = (String) spinDis.getSelectedItem();
                intent.putExtra("district", district);

                startActivity(intent);
            }
        });

    }
}

