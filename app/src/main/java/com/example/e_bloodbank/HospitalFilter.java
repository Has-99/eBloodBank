package com.example.e_bloodbank;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

public class HospitalFilter extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_filter);

        db = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db.collection("hospitals")
            .whereEqualTo("province", getIntent().getStringExtra("province"))
            .whereEqualTo("district", getIntent().getStringExtra("district"))
            .get()
            .addOnSuccessListener(querySnapshot -> {
                recyclerView.setAdapter(new HospitalAdapter(querySnapshot.getDocuments()));
            });
    }
}